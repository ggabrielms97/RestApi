package com.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirest.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
