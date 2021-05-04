package com.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apirest.model.Evento;
import com.apirest.repository.EventoRepository;

import exception.ResourceNotFoundException;
import vo.EventoVo;

@Service
public class EventoService {
	
	private EventoRepository eventoRepository;

	@Autowired
	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	public EventoVo create(EventoVo eventoVo) {
		return EventoVo.create(eventoRepository.save(Evento.create(eventoVo)));
	}

	public Page<EventoVo> findAll(Pageable pageable) {
		var page = eventoRepository.findAll(pageable);
		return page.map(this::convertToEventoVo);
	}

	private EventoVo convertToEventoVo(Evento evento) {
		return EventoVo.create(evento);
	}

	public EventoVo findById(Integer id) {
		var entity = eventoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));
		return EventoVo.create(entity);
	}

	public EventoVo update(EventoVo eventoVo) {
		final Optional<Evento> optional = eventoRepository.findById(eventoVo.getId());

		if (!optional.isPresent()) {
			new ResourceNotFoundException("Evento não encontrado");
		}

		return EventoVo.create(eventoRepository.save(Evento.create(eventoVo)));
	}

	public void delete(Integer id) {
		var entity = eventoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Edicao não encontrado"));
		eventoRepository.delete(entity);
	}

}
