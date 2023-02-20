package com.example.lab3pp.services;

import com.example.lab3pp.models.Recipe;
import com.example.lab3pp.models.Role;
import com.example.lab3pp.repositories.CategoryRepository;
import com.example.lab3pp.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepo;

    public List<Role> findAll() {return roleRepo.findAll();}

    public void saveAll(List<Role> roleList) {
        roleRepo.saveAll(roleList);
    }
}
