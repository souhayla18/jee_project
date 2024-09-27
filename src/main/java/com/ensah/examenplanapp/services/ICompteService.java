package com.ensah.examenplanapp.services;

import com.ensah.examenplanapp.bo.Compte;
import com.ensah.examenplanapp.bo.Role;


import java.util.List;


public interface ICompteService {
	
	public List<Role> getAllRoles();
	
	public List<Compte> getAllAccounts();
	
	public Compte getAccountByUserName(String login);
	
	public String createUser(Long idRole, Long idPerson);
	

}
