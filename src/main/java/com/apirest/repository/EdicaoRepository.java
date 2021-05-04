package com.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirest.model.Edicao;

@Repository
public interface EdicaoRepository extends JpaRepository<Edicao, Integer> {

}
