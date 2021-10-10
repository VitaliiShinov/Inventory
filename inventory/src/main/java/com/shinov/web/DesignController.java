package com.shinov.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinov.Inventory;
import com.shinov.Item;
import com.shinov.Item.Type;

import DAL.DAO;
import DAL.ItemRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignController {
	@GetMapping
	public String showDesignForm(Model model) {

		List<Item> items = Arrays.asList(ItemRepository.findAll());
		Type[] types = Item.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		model.addAttribute("design", new Inventory("My inventory"));
		Item item = DAO.getById("234");

		model.addAttribute("item", item);
		System.out.println(item.getName());
		return "design";
	}



	private List<Item> filterByType(List<Item> ingredients, Type type) {

		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());

	}
}
