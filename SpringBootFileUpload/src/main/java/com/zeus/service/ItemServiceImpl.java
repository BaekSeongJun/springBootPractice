package com.zeus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeus.domain.Item;
import com.zeus.mapper.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemMapper itemMapper;

	@Override
	@Transactional
	public boolean insert(Item item) throws Exception {
		if(item == null || item.getItemName().isBlank() || item.getPicture() == null) {
			return false;
		}
		int count = itemMapper.insert(item);
		return count > 0;
	}

	@Override
	@Transactional(readOnly = true)
	public Item select(Item item) throws Exception {
		return itemMapper.select(item);
	}

	@Override
	@Transactional
	public boolean update(Item item) throws Exception {
		int count = itemMapper.update(item);
		return count > 0;
	}

	@Override
	@Transactional
	public boolean delete(Item item) throws Exception {
		int count = itemMapper.delete(item);
		return count > 0;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Item> list() throws Exception {
		return itemMapper.list();
	}

	@Override
	@Transactional(readOnly = true)
	public String getPicture(Item item) throws Exception {
		return itemMapper.getPicture(item);
	}
}
