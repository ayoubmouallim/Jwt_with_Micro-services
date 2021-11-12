package com.example.authservice.services;

import com.example.authservice.entities.AppRole;
import com.example.authservice.entities.AppUser;
import com.example.authservice.repositories.AppRoleRepository;
import com.example.authservice.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{


    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(AppUser appUser) {

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);

    }

    @Override
    public AppRole addNewRole(AppRole appRole) {

        return  appRoleRepository.save(appRole);

    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findByRoleName(roleName);
        if(user!=null && role!=null)
        {
            user.getRoles().add(role);
            appUserRepository.save(user);
        }


    }

    @Override
    public AppUser loadUserByUsername(String username) {

        AppUser user = appUserRepository.findByUsername(username);

        return user;
    }

    @Override
    public List<AppUser> listUsers() {

        return appUserRepository.findAll();
    }
}
