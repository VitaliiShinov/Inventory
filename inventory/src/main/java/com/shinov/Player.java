package com.shinov;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Player {
	@Id	
	@Column(name="id")
	private int ID;
	@Column(name="attack")
	private int attack;
	@Column(name="defense")
	private int defense;

	
//	public void updateStats(){
//		attack = helm.getAttack() + weapon.getAttack() + armor.getAttack() + artifact.getAttack();
//		defense = helm.getDefense() + weapon.getDefense() + armor.getDefense() + artifact.getDefense();
//	}
}
