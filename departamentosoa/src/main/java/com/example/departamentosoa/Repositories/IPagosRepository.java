package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.PagosModel;

@Repository
public interface IPagosRepository extends JpaRepository<PagosModel, Long> {
    
}
