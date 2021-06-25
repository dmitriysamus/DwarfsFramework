package com.angrydwarfs.Dwarfs_Framework.controllers;

import com.angrydwarfs.Dwarfs_Framework.models.*;
import com.angrydwarfs.Dwarfs_Framework.payload.request.SignupRequest;
import com.angrydwarfs.Dwarfs_Framework.payload.response.MessageResponse;
import com.angrydwarfs.Dwarfs_Framework.repository.RoleAppRepository;
import com.angrydwarfs.Dwarfs_Framework.repository.RoleBDRepository;
import com.angrydwarfs.Dwarfs_Framework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.*;

//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Контроллер доступа.
 * @version 0.001
 * @author dmitriysamus
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RoleAppRepository roleAppRepository;

    @Autowired
    private RoleBDRepository roleBDRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder encoder;
    @PostMapping("/registration")
    public ResponseEntity <?> registerUser(@RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByUsername(
                signupRequest.getUsername()
        )) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByUserEmail(
                signupRequest.getUserEmail()
        )) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User (
                signupRequest.getUsername(),
                signupRequest.getUserEmail(),
                signupRequest.getPassword()
//                encoder.encode(signupRequest.getPassword())
        );

        Set<String> strRoles = signupRequest.getRoleBd();
        Set<RoleBD> rolesBD = new HashSet<>();

        if (strRoles == null) {
            RoleBD roleBD = roleBDRepository.findByRoleName(ERolesBD.ROLE_USER)
                    .orElseThrow(()-> new RuntimeException("Error: Role is not found."));
            rolesBD.add(roleBD);
        } else {
            strRoles.forEach(role -> {
                switch (role) {

                    case "admin":
                        RoleBD roleAdmin = roleBDRepository.findByRoleName(ERolesBD.ROLE_ADMIN)
                                .orElseThrow(()-> new RuntimeException("Error: Role is not found."));;
                        rolesBD.add(roleAdmin);
                        break;

                    case "mod":
                        RoleBD roleMod = roleBDRepository.findByRoleName(ERolesBD.ROLE_MODERATOR)
                                .orElseThrow(()-> new RuntimeException("Error: Role is not found."));;
                        rolesBD.add(roleMod);
                        break;

                    default:
                        RoleBD roleUser = roleBDRepository.findByRoleName(ERolesBD.ROLE_USER)
                                .orElseThrow(()-> new RuntimeException("Error: Role is not found."));
                        rolesBD.add(roleUser);

                }
            });
        }
        user.setRoleBD(rolesBD);
        Set <RoleApp> rolesApp = new HashSet<>();
        rolesApp.add(roleAppRepository.findByRoleName(ERolesApp.COMMON)
                .orElseThrow(()-> new RuntimeException("Error: Role is not found.")));

        user.setCreationDate(LocalDateTime.now());
        user.setLastVisitedDate(null);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



}
