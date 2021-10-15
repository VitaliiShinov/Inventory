package com.shinov;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Data
@Entity
public class Inventory {
	public Inventory() {
	 items = new ArrayList<Item>();
	}

	@Id
	private int id;

	@ElementCollection(targetClass = Integer.class)
	private List<Item> items;

	public void addItem(Item item) {
	
		items.add(item);
	}

}