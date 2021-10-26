package com.shinov;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Item {
	private static Item emptyItem = new Item(0);
	
	private static int amount = 1;
	
	public Item() {
		this.id = amount++;
	}
	
	public static Item getEmptyItem() {
		return emptyItem;
	}
	
	private Item(int id) {
		this.name = "No Item";
		this.attack = 0;
		this.defense = 0;
		this.id = 0;
	}
	
	@Id
	@Column(name = "id")
	@Setter
	@Getter
	private int id;

	@Column(name = "name")
	@Setter
	@Getter
	private String name;

	@Column(name = "type")
	@Setter
	@Getter
	private Type type;

	@Column(name = "attack")
	@Setter
	@Getter
	private int attack;

	@Column(name = "defence")
	@Setter
	@Getter
	private int defense;

	public static enum Type {
		HELM, WEAPON, ARMOR, ARTIFACT
	}
}
