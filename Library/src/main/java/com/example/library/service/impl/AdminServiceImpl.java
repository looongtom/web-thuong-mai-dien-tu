package com.example.library.service.impl;

import com.example.library.dto.AdminDTO;
import com.example.library.model.Admin;
import com.example.library.repository.AdminRepository;
import com.example.library.repository.RoleRepository;
import com.example.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin findByUserName(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(admin.getLastName());
        admin.setUsername(admin.getUsername());
        admin.setPassword(admin.getPassword());
        admin.setRoles(Arrays.asList(roleRepository.findByName("Admin")));
        return adminRepository.save(admin);
    }
}
