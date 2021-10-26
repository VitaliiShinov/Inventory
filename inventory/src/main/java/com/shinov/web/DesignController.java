package com.shinov.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shinov.Inventory;
import com.shinov.Item;
import com.shinov.Item.Type;
import com.shinov.Player;
import com.shinov.parser.Parser;

import DAL.InventoryDAO;
import DAL.InventoryRepository;
import DAL.ItemsDAO;
import DAL.PresetsDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller

public class DesignController {

	private Player player;

	@GetMapping(value = "/design")
	public String showDesignForm(Model model) {

		player = new Player();
		player.addToInventory(new InventoryDAO().findAll());

		model.addAttribute("player", player);

		return "design";
	}

	@GetMapping(value = "/send")
	public String updateGear(Model model, @ModelAttribute(value = "choose") int s) {

		InventoryDAO dao = new InventoryDAO();
		Item item = dao.findById(s);

		changeGear(item);

		player.updateStats();
		model.addAttribute("player", player);
		System.out.println();
		return "design";
	}

	// What the heck? No clue
	private List<Item> filterByType(List<Item> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());

	}

	@GetMapping("/gen")
	private String generateInventory(Model model) {

		player = new Player();

		ItemsDAO dao = new ItemsDAO();
		dao.deleteAll();
		dao.saveAll(Parser.getListOfItems());

		InventoryDAO dao2 = new InventoryDAO();
		dao2.deleteAll();

		List<Item> items = dao.getByType(Type.ARMOR);
		player.getInventory().addItem(getRandomItem(items));
		player.getInventory().addItem(getRandomItem(items));

		items = dao.getByType(Type.ARTIFACT);
		player.getInventory().addItem(getRandomItem(items));
		player.getInventory().addItem(getRandomItem(items));
		player.getInventory().addItem(getRandomItem(items));
		player.getInventory().addItem(getRandomItem(items));

		items = dao.getByType(Type.HELM);
		player.getInventory().addItem(getRandomItem(items));
		player.getInventory().addItem(getRandomItem(items));

		items = dao.getByType(Type.WEAPON);
		player.getInventory().addItem(getRandomItem(items));
		player.getInventory().addItem(getRandomItem(items));

		dao2.saveAll(player.getInventory().getItems());

		items = dao2.findAll();

		player.updateStats();
		model.addAttribute("player", player);

		return "design";
	}

	@GetMapping("/save")
	private String save(Model model, HttpServletRequest request) {
		
		
		
		String preset = request.getParameter("preset");
		int id = Integer.parseInt(preset.substring(preset.length() - 1));

		PresetsDAO dao = new PresetsDAO();

		if (request.getParameter("button").equals("save"))
			dao.update(player.getItems(), id);
		if (request.getParameter("button").equals("load")) {
			List<Item> items = dao.findByID(id);
			for (Item item : items)
				changeGear(item);
			
		}
		
		player.updateStats();
		model.addAttribute("player", player);

		return "design";
	}



	private Item getRandomItem(List<Item> items) {
		return items.get(new Random().nextInt(items.size()));

	}

	private void changeGear(Item item) {
		switch (item.getType()) {
		case ARMOR:
			player.setArmor(item);
			break;
		case ARTIFACT:
			player.setArt(item);
			break;
		case WEAPON:
			player.setWeapon(item);
			break;
		case HELM:
			player.setHelm(item);
			break;
		}
	}

}
