package com.myRestaurant.manager.Entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "roles")
public class RoleEntities {
	@Id
	@Column(name = "role_id")
	private int role_id;
	
	@Column(name = "role_name")
	private String role_name;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "roleEntities")
	@JsonManagedReference
	Set<UserEntities> listuser;

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserEntities> getListuser() {
		return listuser;
	}

	public void setListuser(Set<UserEntities> listuser) {
		this.listuser = listuser;
	}
}