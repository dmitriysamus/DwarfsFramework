package com.angrydwarfs.Dwarfs_Framework;

import com.angrydwarfs.Dwarfs_Framework.controllers.UserController;
import com.angrydwarfs.Dwarfs_Framework.models.*;
import com.angrydwarfs.Dwarfs_Framework.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = { "spring.config.location:classpath:application-test.yml" })
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserController userController;

	String username = "Tester2";

	@Test
	@DisplayName("Проверяет успешную подгрузку контроллеров из контекста.")
	public void loadControllers() {
		assertThat(userRepository).isNotNull();
		assertThat(userController).isNotNull();
	}

	@Test
	@DisplayName("Проверяет создание нового пользователя")
	public void createNewUser() {
		User user = userRepository.findByUsername("Tester").get();
		Set<RoleApp> userRoleApp = new HashSet<>();
		RoleApp roleApp = new RoleApp(ERolesApp.GOLD);
		userRoleApp.add(roleApp);
		user.setRoleApp (userRoleApp);
		user.setCreationDate(LocalDateTime.now());
//		user.setLastVisitedDate(null);
//
		assertEquals("Tester", user.getUsername());
		assertEquals("tester@test.com", user.getUserEmail());
		assertEquals(true, userRoleApp.toString().contains(roleApp.toString()));
		assertEquals(LocalDateTime.now().getYear(), user.getCreationDate().getYear());
		assertEquals(LocalDateTime.now().getMonth(), user.getCreationDate().getMonth());
		assertEquals(LocalDateTime.now().getDayOfMonth(), user.getCreationDate().getDayOfMonth());
		assertEquals(LocalDateTime.now().getYear(), user.getLastVisitedDate().getYear());
//		assertEquals(null, user.getLastVisitedDate());
	}

	@Test
	@DisplayName("Проверяет создание RolesBD для пользователя.")
	public void createUserSubRole() {
		User user = userRepository.findByUsername(username).get();
		Set<RoleBD> roleBD = new HashSet<>();
		RoleBD role_1 = new RoleBD(ERolesBD.ROLE_ADMIN);
		role_1.setId(1);
		RoleBD role_2 = new RoleBD(ERolesBD.ROLE_MODERATOR);
		role_2.setId(2);
		RoleBD role_3 = new RoleBD(ERolesBD.ROLE_USER);
		role_3.setId(3);
		roleBD.add(role_1);
		roleBD.add(role_2);
		roleBD.add(role_3);
		user.setRoleBD(roleBD);
		Iterator <RoleBD> iter = roleBD.iterator();
		System.out.println("TESTTESTTESTTESTTESTTESTTEST");
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		Assert.assertTrue(user.getRoleBD().toString().contains("ROLE_ADMIN"));
		Assert.assertTrue(user.getRoleBD().toString().contains("ROLE_MODERATOR"));
		Assert.assertTrue(user.getRoleBD().toString().contains("ROLE_USER"));
	}


	@Test
	@DisplayName("Проверяет отработку метода test.")
	public void testTest() throws Exception{

		this.mockMvc.perform(get("/api/auth/users/test"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("message").value("WIN!"));
	}


}
