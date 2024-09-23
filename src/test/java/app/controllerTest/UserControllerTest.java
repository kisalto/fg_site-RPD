package app.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import app.controller.UserController;
import app.entity.User;
import app.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private User user;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);

		user = new User();
		user.setId(1L);
		user.setApelido("Teste_da_Silva");
		user.setEmail("testedasilva@gmail.com");
		user.setIsMod(false);
		user.setIsVet(false);
		user.setSenha("123456");

		when(userService.findByApelido("Teste_da_Silva")).thenReturn(null);
		when(userService.save(any(User.class))).thenReturn("Usuário cadastrado com sucesso!!!");
	}

	@Test
	@DisplayName("Teste Create usuario")
	void SaveTest() throws Exception {
		Mockito.when(userService.save(Mockito.any(User.class))).thenReturn("Usuário cadastrado com sucesso!!!");

		mockMvc.perform(post("/api/rdp/user/save").contentType(MediaType.APPLICATION_JSON).content(
				"{ \"apelido\": \"Teste_da_Silva\", \"email\": \"testedasilva@gmail.com\", \"isMod\": false, \"isVet\": false, \"senha\": \"123456\" }"))
				.andExpect(status().isOk()).andExpect(content().string("Usuário cadastrado com sucesso!!!"));
	}

	@Test
	@DisplayName("Teste Update usuario")
	void UpdateTest() throws Exception {
		Mockito.when(userService.update(Mockito.any(User.class), Mockito.anyLong()))
				.thenReturn("Usuário atualizado com sucesso!!!");

		mockMvc.perform(put("/api/rdp/user/update/1").contentType(MediaType.APPLICATION_JSON).content(
				"{ \"apelido\": \"Teste_da_Silva\", \"email\": \"updatetestedasilva@gmail.com\", \"isMod\": false, \"isVet\": false, \"senha\": \"123456\" }"))
				.andExpect(status().isOk()).andExpect(content().string("Usuário atualizado com sucesso!!!"));
	}

	@Test
	@DisplayName("Teste FindById usuario")
	void FindByIdTest() throws Exception {
		Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(user);

		mockMvc.perform(get("/api/rdp/user/findById/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(
						"{\"id\":1,\"apelido\":\"Teste_da_Silva\",\"email\":\"testedasilva@gmail.com\",\"dc_id\":null,\"senha\":\"123456\",\"data_reg\":null,\"isMod\":false,\"isVet\":false,\"event\":null,\"guides\":null}"));
	}

	@Test
	@DisplayName("Teste FindAll usuario")
	void FindAllTest() throws Exception {
		java.util.List<User> lista = new ArrayList<>();
		lista.add(user);
		Mockito.when(userService.findAll()).thenReturn(lista);
		mockMvc.perform(get("/api/rdp/user/findAll").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(
						"[{" + "\"id\":1," + "\"apelido\":\"Teste_da_Silva\"," + "\"email\":\"testedasilva@gmail.com\","
								+ "\"isMod\":false," + "\"isVet\":false," + "\"senha\":\"123456\"}]"));
	}
	
	@Test
	@DisplayName("Teste FindByApelidoContains")
	void FindByApelidoContainsTest() throws Exception {
	    Mockito.when(userService.findByApelidoContains("Teste")).thenReturn(user);

	    mockMvc.perform(get("/api/rdp/user/findByApelidoContains?apelido=Teste")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().json("{\"id\":1,\"apelido\":\"Teste_da_Silva\",\"email\":\"testedasilva@gmail.com\",\"isMod\":false,\"isVet\":false,\"senha\":\"123456\"}"));
	}


	@Test
	@DisplayName("Teste FindByEmail")
	void FindByEmailTest() throws Exception {
	    Mockito.when(userService.findByEmail("testedasilva@gmail.com")).thenReturn(user);

	    mockMvc.perform(get("/api/rdp/user/findByEmail?email=testedasilva@gmail.com")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().json("{\"id\":1,\"apelido\":\"Teste_da_Silva\",\"email\":\"testedasilva@gmail.com\",\"isMod\":false,\"isVet\":false,\"senha\":\"123456\"}"));
	}


}
