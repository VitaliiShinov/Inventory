package com.shinov;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DAL.ItemsDAO;

class DeleteAllTest {

	@Test
	void test() {
		ItemsDAO dao = new ItemsDAO();
		dao.deleteAll();
	}

}
