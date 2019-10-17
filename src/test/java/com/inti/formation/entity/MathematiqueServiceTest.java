package com.inti.formation.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inti.formation.entity.MathematiqueService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MathematiqueServiceTest {
	
	@Test
	public void additionTest() {
		MathematiqueService mathServ = new MathematiqueService();
		long a = 5;
		long b = 6;
		
		long result = mathServ.addition(a, b);
		assertEquals(result, 11);
	}

}
