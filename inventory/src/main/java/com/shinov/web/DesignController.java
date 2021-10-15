package com.shinov.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinov.Inventory;
import com.shinov.Item;
import com.shinov.Item.Type;
import com.shinov.parser.Parser;

import DAL.InventoryDAO;
import DAL.InventoryRepository;
import DAL.ItemsDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/design")
public class DesignController {
	private Inventory inventory = new Inventory();
	@GetMapping 
	public String showDesignForm(Model model, @ModelAttribute(value = "button") String s) {
		
		
		doGet(s, model);
	
		ItemsDAO itemsDAO = new ItemsDAO();
		
		List<Item> items = itemsDAO.findAll();
		
//		Type[] types = Item.Type.values();
//		

//		model.addAttribute("design", new Inventory());
		
		
		return "design";
	}

	private void doGet(String s, Model model) {
		
		if (s.equals("Generate inventory"))
			generateInventory(model);
	}

	// What the heck? No clue
	private List<Item> filterByType(List<Item> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());

	}

	private String generateInventory(Model model) {
		
		inventory = new Inventory();
		
		
		ItemsDAO dao = new ItemsDAO();
		dao.deleteAll();
		dao.saveAll(Parser.getListOfItems());
		
		InventoryDAO dao2 = new InventoryDAO();
		dao2.deleteAll();
		
		List<Item> items = dao.getByType(Type.ARMOR);
		inventory.addItem(getRandomItem(items));
		inventory.addItem(getRandomItem(items));
		
		items = dao.getByType(Type.ARTIFACT);
		inventory.addItem(getRandomItem(items));
		inventory.addItem(getRandomItem(items));
		inventory.addItem(getRandomItem(items));
		inventory.addItem(getRandomItem(items));
		
		items = dao.getByType(Type.HELM);
		inventory.addItem(getRandomItem(items));
		inventory.addItem(getRandomItem(items));
		
		items = dao.getByType(Type.WEAPON);
		inventory.addItem(getRandomItem(items));
		inventory.addItem(getRandomItem(items));
		
		dao2.saveAll(inventory.getItems());
		
		items = dao2.findAll();
		
		model.addAttribute("items", items); 
		return "design";
	}

	private Item getRandomItem(List<Item> items) {
		return items.get(new Random().nextInt(items.size()));
		
	}

}
