package com.example.authservice.controller;

import com.example.authservice.entities.AppRole;
import com.example.authservice.entities.AppUser;
import com.example.authservice.services.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;



    @GetMapping("/users")
    public List<AppUser>  listUsers()
    {
        return accountService.listUsers();
    }

    @PostMapping("/users")
    public AppUser addUser(@RequestBody AppUser appUser)
    {
        return accountService.addNewUser(appUser);
    }
    @PostMapping("/roles")
    public AppRole addRole(@RequestBody AppRole appRole)
    {
        return accountService.addNewRole(appRole);
    }

    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody RolleUserForm rolleUserForm)
    {
         accountService.addRoleToUser(rolleUserForm.getUsername(),rolleUserForm.getRoleName());
    }







}

@Data
class RolleUserForm{
    String username;
    String roleName;

}
