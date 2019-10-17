package com.inti.formation.Service;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inti.formation.TestTestApplication;
import com.inti.formation.Dao.UserDao;
import com.inti.formation.Dao.UserDaoTest;
import com.inti.formation.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestTestApplication.class)
public class UserServiceTest {
//	 //1st method
//	@Autowired
//	@Mock
//	@InjectMocks
//	private UserService userServiceToMock;
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private UserDao userDao;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);
	
//	//2nd method
	@InjectMocks
	private UserService userServiceToMock;
	@Mock 
	private UserDao userDao;
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
//	//1st method
//	@Test
//	public void givenUsers_getHalfOfNumber() {
//		LOGGER.info("----------------Testing givenUtilisateurs_getHalfOf_Number Method ------------------");
//		LOGGER.info("----------------Constructing Utilisateurs ------------------");
//		//userServiceToMock = Mockito.mock(UserService.class); Use can use this instead of using annotation
//		LOGGER.info("----------------Mocking getAll() method of IUtilisateurService ------------------");
//		Mockito.when(userServiceToMock.getAllUsers()).thenReturn(
//				(Arrays.asList(new User(10,"dalii"), new User(1,"dalii"), new User(2,"dalii"), new User(18,"dalii") )));
//		LOGGER.info("---------------- Method Mocked ------------------");
//		LOGGER.info("---------------- Verifying results ------------------");
//		assertEquals(2, userService.getUserNbrHalf(userServiceToMock.getAllUsers()));
//	}
	
//	//2nd method
	@Test
	public void should_store_when_save_is_called() {
		LOGGER.info("------------------- Executing should_store_when_save_" 
	+ "is_called test of UserServiceImpl Test -------------------");
		User myUser = new User();
		userServiceToMock.addUser(myUser);
		Mockito.verify(userDao).save(myUser);
	}

}
