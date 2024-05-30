package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Compte;
import com.ensah.examenplanapp.bo.Role;
import com.ensah.examenplanapp.bo.Personne;
import com.ensah.examenplanapp.dao.ICompteRepository;
import com.ensah.examenplanapp.dao.IPersonneRepository;
import com.ensah.examenplanapp.dao.IRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CompteService {

    private final ICompteRepository compteRepository;
    private final IPersonneRepository personneRepositoryDao;
    private final IRepositoryDao roleRepositoryDao;

    @Autowired
    public CompteService(ICompteRepository compteRepository, IPersonneRepository personneRepositoryDao, IRepositoryDao roleRepositoryDao) {
        this.compteRepository = compteRepository;
        this.personneRepositoryDao = personneRepositoryDao;
        this.roleRepositoryDao = roleRepositoryDao;
    }

    public Compte saveCompte(Compte compte, Long idPersonne, Long idRole) {
        // Vérifier si la personne avec l'ID spécifié existe
        Optional<Personne> personneOptional = personneRepositoryDao.findById(idPersonne);
        if (personneOptional.isEmpty()) {
            // Gérer le cas où la personne n'existe pas
            throw new IllegalArgumentException("Personne avec l'ID spécifié n'existe pas.");
        }

        // Vérifier si le rôle avec l'ID spécifié existe
        Optional<Role> roleOptional = roleRepositoryDao.findById(idRole);
        if (roleOptional.isEmpty()) {
            // Gérer le cas où le rôle n'existe pas
            throw new IllegalArgumentException("Rôle avec l'ID spécifié n'existe pas.");
        }

        // Associer la personne et le rôle au compte
        compte.setProprietaire(personneOptional.get());
        compte.setRole(roleOptional.get());

        // Sauvegarder le compte dans la base de données
        return compteRepository.save(compte);
    }
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Optional<Compte> getCompteById(Long id) {
        return compteRepository.findById(id);
    }
    public Compte updateCompte(Long id, Compte compte) {
        if (compteRepository.existsById(id)) {
            compte.setIdCompte(id);
            return compteRepository.save(compte);
        } else {
            return null;
        }
    }

    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }
    public Compte getCompteByLogin(String username) {
        return compteRepository.getCompteByLogin(username);


}







    }

