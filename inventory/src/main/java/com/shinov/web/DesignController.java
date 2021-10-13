package com.shinov.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shinov.Inventory;
import com.shinov.Item;
import com.shinov.Item.Type;
import com.shinov.parser.Parser;

import DAL.ItemRepository;
import DAL.ItemsDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignController {
	@GetMapping
	public String showDesignForm(Model model) {
		ItemsDAO itemsDAO = new ItemsDAO();
		
		List<Item> items = itemsDAO.findAll();
		
		Type[] types = Item.Type.values();
		
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(items, type));
		}
		model.addAttribute("design", new Inventory());
		return "design";
	}


	// What the heck? No clue
	private List<Item> filterByType(List<Item> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());

	}
	
	@GetMapping("/gen")
	public String generateInventory() {
		System.out.println(123);
		ItemsDAO dao = new ItemsDAO();
		dao.deleteAll();
		dao.saveAll(Parser.getListOfItems());
		return "design";
	}
//	

}
