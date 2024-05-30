package com.ensah.examenplanapp.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "idAdmin")
public class CadreAdministrateur extends Personne implements Serializable {



	@OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
	private List<surveillance> admin_surveillance;
	@Column(unique = true)
	private String gradeAdmin;

	public String getGradeAdmin() {
		return gradeAdmin;
	}

	public void setGradeAdmin(String gradeAdmin) {
		this.gradeAdmin = gradeAdmin;
	}
	public void setAdmin_surveillance(List<surveillance> admin_surveillance) {
		this.admin_surveillance = admin_surveillance;
	}
	public List<surveillance> getAdmin_surveillance() {
		return admin_surveillance;
	}


}

