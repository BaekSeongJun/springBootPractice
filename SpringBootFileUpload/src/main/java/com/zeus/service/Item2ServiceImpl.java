package com.zeus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeus.domain.Item2;
import com.zeus.mapper.Item2Mapper;

@Service
public class Item2ServiceImpl implements Item2Service{

	@Autowired
	private Item2Mapper itemMapper;

	@Override
	@Transactional
	public boolean insert(Item2 item) throws Exception {
		if(item == null || item.getItemName().isBlank()) {
			return false;
		}
		int count = itemMapper.insert(item);
		return count > 0;
	}

	@Override
	@Transactional(readOnly = true)
	public Item2 select(Item2 item) throws Exception {
		return itemMapper.select(item);
	}

	@Override
	@Transactional
	public boolean update(Item2 item) throws Exception {
		int count = itemMapper.update(item);
		return count > 0;
	}

	@Override
	@Transactional
	public boolean delete(Item2 item) throws Exception {
		int count = itemMapper.delete(item);
		return count > 0;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Item2> list() throws Exception {
		return itemMapper.list();
	}

	@Override
	@Transactional(readOnly = true)
	public String getPicture(Item2 item) throws Exception {
		return itemMapper.getPicture(item);
	}
	@Override
	@Transactional(readOnly = true)
	public String getPicture2(Item2 item) throws Exception {
		return itemMapper.getPicture2(item);
	}
}
