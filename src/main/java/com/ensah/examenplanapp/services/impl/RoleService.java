package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Role;
import com.ensah.examenplanapp.dao.IRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    private final IRepositoryDao roleRepository;

    @Autowired
    public RoleService(IRepositoryDao roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role role) {
        if (roleRepository.existsById(id)) {
            role.setIdRole(id);
            return roleRepository.save(role);
        } else {
            return null;
        }
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public Role getRoleByNomRole(String nomRole) {
        return roleRepository.findByNomRole(nomRole);
    }
}
