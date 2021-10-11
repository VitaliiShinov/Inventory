package com.shinov;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import DAL.ItemsDAO;

public class DataBaseTest {

	@Test
	public void findAllTest() {
		ItemsDAO dao = new ItemsDAO();
		List<Item> items = dao.findAll();
		for (Item i : items)
			System.out.println(i.getName());
	}

	@Test
	public void findByID() {
		ItemsDAO dao = new ItemsDAO();
		Item item = dao.findByID("234");
		System.out.println(item.getName());
	}

}
