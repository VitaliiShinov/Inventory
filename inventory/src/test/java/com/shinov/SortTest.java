package com.shinov;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.shinov.Item.Type;
import com.shinov.parser.*;

import DAL.ItemsDAO;

class SortTest {
	private List<Item> filterByType(List<Item> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());

	}

	@Test
	void test() {

		ItemsDAO dao = new ItemsDAO();
		List<Item> items = dao.getByType(Type.ARMOR);
		
		for(Item i:items)
			System.out.println(i.getName());
	}

	@Test
	void test2() {

		ItemsDAO dao = new ItemsDAO();
		List<Item> items = dao.getByType(Type.ARMOR);
		Inventory inventory = new Inventory();
		
		inventory.addItem(getRandomItem(items));
	}

	private Item getRandomItem(List<Item> items) {
		items.get(new Random().nextInt(items.size()));
		return null;
	}
}
