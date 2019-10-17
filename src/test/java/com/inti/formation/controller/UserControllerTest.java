package com.inti.formation.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inti.formation.TestTestApplication;
import com.inti.formation.Dao.UserDaoTest;
import com.inti.formation.Service.UserService;
import com.inti.formation.entity.User;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestTestApplication.class)
public class UserControllerTest {
	@Autowired
	// Variable permettant  d'exécuter des requêtes web
	WebApplicationContext webApplicationContext;
	/**
	 * Used to mock the Web Context
	 */
	protected MockMvc mvc;
	/**
	 * Used for the web service adressing. You need to initiae it in the subclasses
	 * constructors
	 */
	protected String uri;
	

	@InjectMocks
	private UserController userControllerToMock;
	
	@Mock
	private UserService userServiceToMock;

	@Before
	public void SetUp() {
		//Permet de créer une fausse image (un mock) du webApplicationContext
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MockitoAnnotations.initMocks(this);
	}
	
	@Autowired
	private UserService userService;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);
	
	//Controller
	public UserControllerTest () {
		super();
		this.uri = "/user";
	}
	
//	Method to be changed
//	@Test
//	public void getAllEntityList () {
//		// Contient les résultats d'une requête HTTP
//		MvcResult mvcResult;
//		try {
//			LOGGER.info("--------------------- Testing getAllEntity Method ----------------");
//			
//			LOGGER.info("--------------------- Constructing Utilisateur ----------------");
//			LOGGER.info("--------------------- Saving Utilisateur ----------------");
//			userService.addUser(new User(2, "dalii"));
//			
//			LOGGER.info("--------------------- Mocking Context WebService ----------------");
//			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + "/all").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//			
//			LOGGER.info("--------------------- Getting HTTP Status ----------------");
//			int status = mvcResult.getResponse().getStatus();
//			LOGGER.info("--------------------- Verifying HTTP Status ----------------");
//			assertEquals(200, status);
//			LOGGER.info("--------------------- Getting HTTP Status ----------------");
//			String content = mvcResult.getResponse().getContentAsString();
//			LOGGER.info("--------------------- Deserializing JSON Response ----------------");
//			User[] userList = this.mapFromJson(content, User[].class);
//			assertTrue(userList.length > 0);
//			
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	
// *********************************************************************************************************************
//	 Correction - Check the status
	@Test
	public void getAllEntityList_StatusChecking () {
		MvcResult mvcResult;
		try {
			LOGGER.info("--------------------- Testing getAllEntity Method ----------------");
			
			LOGGER.info("--------------------- Constructing Utilisateur ----------------");
			User user = new User(2, "dalii");
			LOGGER.info("--------------------- Mocking the saving step of Utilisateur ----------------");
			userServiceToMock = Mockito.mock(UserService.class);
			Mockito.when(userServiceToMock.addUser(user)).thenReturn(user);
			
			LOGGER.info("--------------------- Mocking Context WebService ----------------");
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + "/all").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			LOGGER.info("--------------------- Getting HTTP Status ----------------");
			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("--------------------- Verifying HTTP Status ----------------");
			assertEquals(200, status);
						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	 Correction - Check if the method was invoked
	@Test
	public void getAllEntityList_CheckInvokedMethod () {
		try {
			LOGGER.info("--------------------- Testing getAllEntity Method ----------------");
			
			LOGGER.info("--------------------- Constructing Utilisateur ----------------");
			User user = new User(2, "dalii");
			LOGGER.info("--------------------- Mocking the saving of Utilisateur ----------------");
			userControllerToMock.addNewUser(user);
			LOGGER.info("--------------------- Verifying the invocation of the method ----------------");
			Mockito.verify(userService).addUser(user);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
// *********************************************************************************************************************	
	
	@Test
	public void createEntity() {
		LOGGER.info("--------------------- Testing createEntity Method ----------------");
		LOGGER.info("--------------------- Constructing Utilisateur ----------------");
		User user = new User(50, "sala7");
		
		MvcResult mvcResult;
		try {
			LOGGER.info("--------------------- Serializing Utilisateur Object ----------------");
			String inputJson = this.mapToJson(user);
			LOGGER.info("--------------------- Mocking Context WebService and invoking the webservice ----------------");
			mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri + "/adduser")
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			LOGGER.info("--------------------- Getting HTTP Status ----------------");
			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("--------------------- Verifying HTTP Status ----------------");
			assertEquals(200, status);
			LOGGER.info("--------------------- Searching Utilisateur ----------------");
			User userFound = userService.getUserById(new Long(50));
			LOGGER.info("--------------------- Verifying Utilisateur ----------------");
			assertEquals(userFound.getUserName(), user.getUserName());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test public void updateEntity() {
		try {
			LOGGER.info("----------------- Testing updateEntity Method ------------------");
			
			LOGGER.info("----------------- Constructing Utilisateur ------------------");
			User oldUser = new User(2, "Lemon");
			LOGGER.info("----------------- Saving Utilisateur ------------------");
			userService.addUser(oldUser);
			LOGGER.info("----------------- Modifying Utilisateur ------------------");
			User newUser = new User(2, "Lemonade");
			LOGGER.info("----------------- Serializing Utilisateur Object ------------------");
			String inputJson = this.mapToJson(newUser);
			LOGGER.info("----------------- Mocking Context WebService and invoking the webservice ------------------");
			
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri + "/2")
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			
			LOGGER.info("----------------- Getting HHTP Status ------------------");
			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("----------------- Verifying HHTP Status ------------------");
			assertEquals(200, status);
			LOGGER.info("----------------- Searching for Utilisateur ------------------");
			User userFound = userService.getUserById(new Long(2));
			LOGGER.info("----------------- Verifying for Utilisateur ------------------");
			assertEquals(userFound.getUserName(), newUser.getUserName());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteEntity() {
		LOGGER.info("----------------- Testing deleteEntity Method ------------------");
		try {
		LOGGER.info("----------------- Constructing Utilisateur ------------------");
		LOGGER.info("----------------- Saving Utilisateur ------------------");
		userService.addUser(new User(2, "Lemon"));
		LOGGER.info("----------------- Mocking Context WebService and invoking the webservice ------------------");
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + "/2")).andReturn();
		
		LOGGER.info("----------------- Getting HHTP Status ------------------");
		int status = mvcResult.getResponse().getStatus();
		LOGGER.info("----------------- Verifying HHTP Status ------------------");
		assertEquals(200, status);
		LOGGER.info("----------------- Searching for Utilisateur ------------------");
		User userFound = userService.getUserById(new Long(2));
		LOGGER.info("----------------- Verifying for Utilisateur ------------------");
		assertEquals(userFound, null);
		
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * Serialize the given object into Json
	 * @param obj
	 * @return String
	 * @throws JsonProcessingException
	 */
	
	protected final String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
	
	/**
	 * Deserialize a given Json string into an object
	 * @param json
	 * @param clazz
	 * @return
	 * @throws IOException 
	 * @throws JsonParseException 
	 * @throws JsonMappingException

	 */
	
	protected final <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	
	

}
