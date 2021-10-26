package com.shinov;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class Player {

	public Player() {
		this.setArt(Item.getEmptyItem());
	
		this.setWeapon(Item.getEmptyItem());
		this.setHelm(Item.getEmptyItem());
		this.setArmor(Item.getEmptyItem());
		inventory = new Inventory();
	}

	public void addToInventory(List<Item> items) {
		for (Item i : items)
			inventory.addItem(i);
	}

	@Getter
	@Setter
	private Inventory inventory;

	private int ID;
	@Getter
	@Setter

	private int attack;
	@Getter
	@Setter
	private int defence;

	@Setter
	@Getter

	private Item armor;
	@Setter
	@Getter

	private Item helm;
	@Setter
	@Getter

	private Item art;

	@Setter
	@Getter

	private Item weapon;

	public void updateStats() {
		attack = helm.getAttack() + weapon.getAttack() + armor.getAttack() + art.getAttack();
		defence = helm.getDefense() + weapon.getDefense() + armor.getDefense() + art.getDefense();
	}
}
