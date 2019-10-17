package com.inti.formation.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.formation.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	public User findById(long id);

}
