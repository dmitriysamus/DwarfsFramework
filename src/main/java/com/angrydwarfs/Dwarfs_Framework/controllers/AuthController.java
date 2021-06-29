package com.angrydwarfs.Dwarfs_Framework.controllers;

import com.angrydwarfs.Dwarfs_Framework.models.*;
import com.angrydwarfs.Dwarfs_Framework.payload.request.LoginRequest;
import com.angrydwarfs.Dwarfs_Framework.payload.request.SignupRequest;
import com.angrydwarfs.Dwarfs_Framework.payload.response.MessageResponse;
import com.angrydwarfs.Dwarfs_Framework.repository.RoleAppRepository;
import com.angrydwarfs.Dwarfs_Framework.repository.RoleBDRepository;
import com.angrydwarfs.Dwarfs_Framework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * @method registerUser - при http POST запросе по адресу .../api/auth/registration
     * @param signupRequest - входные данные по текущему аутентифицированному пользователю
     * возвращает данные
     * @return {@code ResponseEntity.ok - User registered successfully!} - ок при успешной регистрации.
     * @return {@code ResponseEntity.badRequest - Error: Role is not found.} - ошибка при указании неправильной роли.
     * @return {@code ResponseEntity.badRequest - Error: Username is already taken!} - ошибка при дублировании username при регистрации.
     * @return {@code ResponseEntity.badRequest - Error: Email is already in use!} - ошибка при дублировании email при регистрации.
     * @see ResponseEntity
     * @see SignupRequest
     */

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

        Set<String> strRoles = signupRequest.getRoleBD();
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

        user.setRoleApp(rolesApp);
        user.setCreationDate(LocalDateTime.now());
        user.setLastVisitedDate(null);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    /**
     * @method authenticateUser - при http post запросе по адресу .../api/auth/login
     * @param loginRequest - запрос на доступ с параметрами user login+password.
     * возвращает
     * @return {@code ResponseEntity ответ}
     * @see LoginRequest
     */

    @PostMapping("/login")
    public ResponseEntity <?> authenticateUser (@RequestBody LoginRequest loginRequest) {

        Jw



        return ResponseEntity.ok(new MessageResponse("User logged"));

    }





//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        JwtResponse jwtResponse = tokenUtils.makeAuth(loginRequest.getUserName(),  loginRequest.getPassword());
//        tokenUtils.makeToken(loginRequest.getUserName(), jwtResponse.getToken());
//        User user = userRepository.findByUserName(loginRequest.getUserName()).get();
//        user.setLastVisitedDate(LocalDateTime.now());
//        userRepository.save(user);
//        return ResponseEntity.ok(jwtResponse);
//    }
//
//    /**
//     * @method logoutUser - при http post запросе по адресу .../api/auth/logout
//     * @param request - запрос на выход с параметрами user login+password + токен jwt.
//     * возвращает
//     * @return {@code ResponseEntity ответ}
//     * @see LoginRequest
//     */
//    @GetMapping("/logout")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
//        String headerAuth = request.getHeader("Authorization");
//        String jwt = headerAuth.substring(7, headerAuth.length());
//
//        Token unActiveToken = tokenRepository.findByToken(jwt);
//        unActiveToken.setActive(false);
//        tokenRepository.save(unActiveToken);
//
//        return ResponseEntity
//                .badRequest()
//                .body(new MessageResponse("You are logout."));
//    }


}
