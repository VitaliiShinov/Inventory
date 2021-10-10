package com.shinov;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class Item {
	@Id
	@Column(name="id")
	private final String id;
	@Column(name="name")
	private final String name;
	@Column(name="type")
	private final Type type;
	
	@Column(name="attack")
	private final int attack;
	
	@Column(name="defence")
	private final int defense;
	
	public static enum Type{
		HELM, WEAPON, ARMOR, ARTIFACT
	}
}
