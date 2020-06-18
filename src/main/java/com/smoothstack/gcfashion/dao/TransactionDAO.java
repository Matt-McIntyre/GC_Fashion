package com.smoothstack.gcfashion.dao;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.smoothstack.gcfashion.entity.Transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Long>{

    @Query("SELECT t FROM Transaction t WHERE t.userId = :userId")
    public List<Transaction> find(@Param("userId") Long userId);
}







