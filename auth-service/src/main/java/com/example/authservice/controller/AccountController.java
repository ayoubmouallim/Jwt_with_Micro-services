package com.example.authservice.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authservice.entities.AppRole;
import com.example.authservice.entities.AppUser;
import com.example.authservice.services.AccountService;
import com.example.authservice.utils.JwtUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;



    @GetMapping("/users")
    @PostAuthorize("hasAuthority('USER')")
    public List<AppUser>  listUsers()
    {
        return accountService.listUsers();
    }

    @PostMapping("/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser addUser(@RequestBody AppUser appUser)
    {
        return accountService.addNewUser(appUser);
    }
    @PostMapping("/roles")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppRole addRole(@RequestBody AppRole appRole)
    {
        return accountService.addNewRole(appRole);
    }

    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody RolleUserForm rolleUserForm)
    {
         accountService.addRoleToUser(rolleUserForm.getUsername(),rolleUserForm.getRoleName());
    }

@GetMapping("/refreshToken")
    public Map<String,String> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String token = request.getHeader(JwtUtils.AUTH_HEAD);
        if (token != null && token.startsWith(JwtUtils.PREFIXE)) {
            try {


                String jwtRefreshToken = token.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwtRefreshToken);
                String username = decodedJWT.getSubject();
                AppUser appUser = accountService.loadUserByUsername(username);

                String jwtAccessToken = JWT.create().withSubject(appUser.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis()+JwtUtils.EXPIRE_ACCESS_TOKEN))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles",appUser.getRoles().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
                    .sign(algorithm);


                Map<String, String> accessToken = new HashMap<>();
                accessToken.put("Access_Token", jwtAccessToken);
                accessToken.put("Refresh_Token", jwtRefreshToken);
                return accessToken;

            }catch (TokenExpiredException e){
               // response.setHeader("Error-Message",e.getMessage());
                //response.sendError(HttpServletResponse.SC_FORBIDDEN);
               throw e;
            }
        }
        throw new RuntimeException("Bad Refresh Token");

    }


    @GetMapping("/profile")
    public AppUser profile(Principal principal)
    {
    return accountService.loadUserByUsername(principal.getName());
    }



}

@Data
class RolleUserForm{
    String username;
    String roleName;

}
