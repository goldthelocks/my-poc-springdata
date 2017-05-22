/**
 * 
 */
package com.poc.springdatajpa;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poc.springdatajpa.model.user.User;
import com.poc.springdatajpa.service.user.UserService;

/**
 * @author eotayde
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	public void test() {
		Integer num = 5;
		short num1 = 1;
		
		System.out.println(num.longValue());
		System.out.println(Long.valueOf(num1));
	}

	public void findAll() {
		save();

		List<User> list = userService.findWithOrder();

		for (User u : list) {
			System.out.println(u.toString());
		}

		Assert.assertEquals(3, list.size());
	}

	@Test
	public void save() {
		List<User> list = new ArrayList<User>();

		User user1 = new User("eraine", "ganda");
		User user2 = new User("jay", "pogi");
		User user3 = new User("akai", "cute");

		list.add(user1);
		list.add(user2);
		list.add(user3);

		for (User u : list) {
			userService.save(u);
		}
	}
}
