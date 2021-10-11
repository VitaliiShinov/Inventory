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

	@Id
	@Column(name = "id")
	@Setter
	@Getter
	private String id;

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
