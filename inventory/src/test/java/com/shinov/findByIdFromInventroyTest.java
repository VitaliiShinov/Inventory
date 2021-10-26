package com.shinov;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import DAL.InventoryDAO;

class findByIdFromInventroyTest {

	@Test
	void test() {
		InventoryDAO dao = new InventoryDAO();
		List<Item> items = dao.findAll();
		for (Item item : items)
			System.out.println(item.getId());
		Item item = dao.findById(60);
		System.out.println(item.getType());
	}
	
	

}
