package com.shinov;


import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;



@Data
@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NonNull
	private String name;
	private Date createdAt;
	@ManyToMany(targetEntity = Item.class)
	private List<Item> item;

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
	

}