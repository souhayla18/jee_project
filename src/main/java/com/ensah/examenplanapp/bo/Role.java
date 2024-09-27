package com.ensah.examenplanapp.bo;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;


@Entity
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;
	@Column(unique = true)
	private String nomRole;
// cascade = CascadeType.ALL
	@OneToMany(mappedBy = "role" , targetEntity = Compte.class)
	private Set<Compte> comptes;

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getNomRole() {
		return nomRole;
	}

	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}

	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}



}