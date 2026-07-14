package com.zeus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeus.domain.Member;
import com.zeus.domain.MemberAuth;
import com.zeus.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	@Transactional
	public boolean insert(Member member) throws Exception{
		if(member == null || member.getUserId().isBlank() || member.getUserPw().isBlank()) {
			return false;
		}
		log.info("memberServiceImpl member : {}", member);
		int count = memberMapper.insertMember(member);
		log.info("memberServiceImpl member : {}", member);
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_USER");
		int count2 = memberMapper.insertAuth(memberAuth);
		return count > 0 && count2 > 0;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Member> list() throws Exception {
		return memberMapper.list();
	}

	@Override
	@Transactional
	public boolean update(Member member) throws Exception {
		if(member == null || member.getUserName().isBlank()){
			return false;
		}
		int count = memberMapper.updateMember(member);
		int count2 = 0;
		memberMapper.deleteAuth(member);
		List<MemberAuth> authList = member.getAuthList();
		for(MemberAuth auth : authList) {
			if(auth.getAuth() == null || auth.getAuth().isBlank()){
				continue;
			}
			auth.setUserNo(member.getUserNo());
			count2 = memberMapper.insertAuth(auth);
		}
		return count > 0 && count2 >= 0;
	}

	@Override
	@Transactional
	public boolean delete(Member member) throws Exception {
		if(member == null || member.getUserNo() <= 0){
			return false;
		}
		int count = memberMapper.deleteMember(member);
		return count > 0;
	}

	@Override
	public Member select(Member member) throws Exception {
		if(member == null || member.getUserNo() <= 0){
			return null;
		}
		return memberMapper.selectMember(member);
	}
}
