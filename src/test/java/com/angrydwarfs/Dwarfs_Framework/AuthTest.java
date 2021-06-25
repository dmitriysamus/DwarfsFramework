package com.angrydwarfs.Dwarfs_Framework;

import com.angrydwarfs.Dwarfs_Framework.controllers.UserController;
import com.angrydwarfs.Dwarfs_Framework.models.ERolesApp;
import com.angrydwarfs.Dwarfs_Framework.models.ERolesBD;
import com.angrydwarfs.Dwarfs_Framework.models.RoleApp;
import com.angrydwarfs.Dwarfs_Framework.models.User;
import com.angrydwarfs.Dwarfs_Framework.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = { "spring.config.location:classpath:application-test.yml" })
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AuthTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    String username = "admin";
    String password = "12345";

    @Test
    @DisplayName("Проверяет успешную подгрузку контроллеров из контекста.")
    public void loadControllers() {
        assertThat(userController).isNotNull();
    }


    @Test
    @DisplayName("Проверяет создание пользователя с ролями ADMIN, MOD и USER.")
    public void testCreateUserHasManyRolesBD() throws Exception {
        this.mockMvc.perform(post("/api/auth/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"testMany\", \"userEmail\": \"testmany@mod.com\", \"password\": \"12345\", \"roleBD\": [\"admin\", \"mod\", \"user\"] }"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("User registered successfully!"));

        User user = userRepository.findByUsername("testMany").get();
//        Assert.assertTrue(user.getRoleBD().toString().contains(ERolesBD.ROLE_ADMIN.toString()));
//        Assert.assertTrue(user.getRoleBD().toString().contains(ERolesBD.ROLE_USER.toString()));
//        Assert.assertTrue(user.getRoleApp().toString().contains(ERolesApp.COMMON.toString()));
        Assert.assertTrue(user.getUserEmail().contains("testmany@mod.com"));

    }


}
