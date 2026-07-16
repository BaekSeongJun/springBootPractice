package com.zeus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zeus.domain.Item2;
import com.zeus.service.Item2Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item2")
public class Item2Controller {
	@Autowired
	private Item2Service itemService;

	@Value("${upload.path}")
	private String uploadPath;

	@GetMapping("/insertForm")
	public String item2InsertForm(Item2 item, Model model) throws Exception {
		model.addAttribute("item", item);
		return "item2/insertForm";
	}

	@PostMapping("/insert")
	public String item2Insert(Item2 item, Model model) throws Exception {
		List<MultipartFile> files = item.getPictures();

		for(MultipartFile file : files) {
			log.info("원래파일명: " + file.getOriginalFilename());
			log.info("파일사이즈: " + file.getSize());
			log.info("파일타입: " + file.getContentType());
			String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
			if(item.getPictureUrl() == null) {
				item.setPictureUrl(createdFileName);
			}else{
				item.setPictureUrl2(createdFileName);
			}
		}

		boolean result = itemService.insert(item);
		if(result) {
			model.addAttribute("msg", "등록이 완료되♘습니다.");
		}else {
			model.addAttribute("msg", "등록이 실패되♘습니다.");
		}
		return "item2/success";
	}

	@GetMapping("/list")
	public String item2List(Model model) throws Exception {
		model.addAttribute("list", itemService.list());
		return "item2/list";
	}

	@GetMapping("/deleteForm")
	public String item2DeleteForm(Item2 item, Model model) throws Exception {
		item = itemService.select(item);
		model.addAttribute("item", item);
		return "item2/deleteForm";
	}

	@PostMapping("/delete")
	public String item2Delete(Item2 item, Model model) throws Exception {
		//1.삭제할 외부 저장소에 저장되어있는 테이블에서 가져온다.
		item = itemService.select(item);
		for(int i = 0; i < 2; i++){
			String createFileName = (i == 0) ? item.getPictureUrl() : item.getPictureUrl2();
			if(createFileName != null) {
				File file = new File(uploadPath + File.separator + createFileName);
				if(file.exists()) {
					file.delete();
				}
			}
		}
		boolean result = itemService.delete(item);
		if(result) {
			model.addAttribute("msg", "삭제가 완료되♘습니다.");
		}else {
			model.addAttribute("msg", "삭제에 실패되♘습니다.");
		}
		return "item2/success";
	}

	@GetMapping("/updateForm")
	public String item2UpdateForm(Item2 item, Model model) throws Exception {
		item = itemService.select(item);
		model.addAttribute("item", item);
		return "item2/updateForm";
	}

	@PostMapping("/update")
	public String item2Update(Item2 item, Model model) throws Exception {
		//1.사용자가 선택한 파일객체를 가져오고, 기존에 있는 중복되지 않는 이미지파일명을 가져온다.
		List<MultipartFile> files = item.getPictures();
		for(int i = 0; i < 2; i++) {
			String oldFileName = (i == 0) ? item.getPictureUrl() : item.getPictureUrl2();
			//2.사용자가 새로운 파일을 선택을 했는지 체크(기존의 파일을 스토리지에서 점검
			if (files.get(i) != null && files.get(i).getSize() > 0) {
				String createdFileName = uploadFile(files.get(i).getOriginalFilename(), files.get(i).getBytes());
				if ((i == 0)) {
					item.setPictureUrl(createdFileName);
				} else {
					item.setPictureUrl2(createdFileName);
				}
				//옛날 파일을 삭제
				if (oldFileName != null) {
					File oldFile = new File(uploadPath + File.separator + oldFileName);
					if (oldFile.exists()) {
						oldFile.delete();
					}
				}
			}
		}
		//업데이트

		boolean result = itemService.update(item);
		if(result) {
			model.addAttribute("msg", "수정이 완료되♘습니다.");
		}else {
			model.addAttribute("msg", "수정에 실패되♘습니다.");
		}
		return "item2/success";
	}

	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> item2Display(Item2 item, Model model, int no) throws Exception {
		//1.이미지 파일을 byte[]로 만들어서 사용자한테 전송. => File => byte[] => InputStream(1byte) Reader(2byte)
		//지금까지는 Board 객체 -> json 클라이언트 전송
		InputStream in = null;
		//2.ResponseEntity<byte[]> 변수 선언
		ResponseEntity<byte[]> entity = null;
		//3.외장하드에 있는 이미지 파일명을 가져온다.
		item = itemService.select(item);
		String createFileName = no == 1 ? item.getPictureUrl() : item.getPictureUrl2();
		try{
			//4.이미지 확장자 정보가 필요함
			int index = createFileName.lastIndexOf(".");
			String formatName = createFileName.substring(index + 1);
			MediaType mediaType = getMediaType(formatName);
			//5.HttpHeaders 생성
			HttpHeaders httpHeaders = new HttpHeaders();
			//6. 외부저장소에서 파일을 읽어온다.
			in = new FileInputStream(uploadPath + File.separator + createFileName);
			//7.mediaType null이 아니면, HttpHeader contentType 등록
			if(mediaType != null) {
				httpHeaders.setContentType(mediaType);
			}
			//8.ResponseEntity<byte[]> entity 객체 생성
			entity = new ResponseEntity<>(IOUtils.toByteArray(in),httpHeaders, HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}finally {
			if(in != null) {
				in.close();
			}
		}

		return entity;
	}



	private String uploadFile(String originalFileName, byte[] fileData) throws Exception {
		//중복되지 않는 아이디 생성
		//ex) 7c547e42-3079-4b58-bf91-33fc7d139cfc
		UUID uuid = UUID.randomUUID();
		//7c547e42-3079-4b58-bf91-33fc7d139cfc_홍길동.jpg
		String createdFileName = uuid.toString() + "_" + originalFileName;
		//uploadPath = "C:/upload/7c547e42-3079-4b58-bf91-33fc7d139cfc_홍길동.jpg"
		File target = new File(uploadPath, createdFileName);
		//fileData => "C:/upload/7c547e42-3079-4b58-bf91-33fc7d139cfc_홍길동.jpg" 복사 진행
		FileCopyUtils.copy(fileData, target);
		//7c547e42-3079-4b58-bf91-33fc7d139cfc_홍길동.jpg 리턴
		return createdFileName;
	}

	private MediaType getMediaType(String
		formatName){ if(formatName != null) {
		if(formatName.equals("JPG"))
		{ return
			MediaType.IMAGE_JPEG;
		}

		if(formatName.equals("GIF"))
		{ return MediaType.IMAGE_GIF;
		}

		if(formatName.equals("PNG"))
		{ return
			MediaType.IMAGE_PNG;
		}
	}
		return null;
	}
}
