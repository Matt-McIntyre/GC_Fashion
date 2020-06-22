package com.smoothstack.gcfashion.dao;

import org.springframework.stereotype.Repository;

import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserDAO extends JpaRepository<User, Long>{
	List<User> findByUserId(Long userId);
	
}
