package com.myRestaurant.manager.Entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "users")
public class UserEntities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "dateofbirth")
	private String dateOfBirth;
	
	@Column(name = "phonenumber")
	private String phoneNumber;
	
	@Column(name = "gender")
	private boolean gender;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	@JsonManagedReference
	private RoleEntities roleEntities;
	
	@OneToMany(mappedBy = "user")
	private Set<InvoiceEntities> invoice;

	public RoleEntities getRoleEntities() {
		return roleEntities;
	}

	public void setRoleEntities(RoleEntities roleEntities) {
		this.roleEntities = roleEntities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<InvoiceEntities> getInvoice() {
		return invoice;
	}

	public void setInvoice(Set<InvoiceEntities> invoice) {
		this.invoice = invoice;
	}
}
