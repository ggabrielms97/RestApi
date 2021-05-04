package com.apirest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.services.EventoService;

import vo.EventoVo;

@RestController
@RequestMapping("/evento")
public class EventoController {
	

	private final EventoService eventoService;
	private final PagedResourcesAssembler<EventoVo> assembler;
	
	@Autowired
	public EventoController(EventoService eventoService, PagedResourcesAssembler<EventoVo> assembler) {
		this.eventoService = eventoService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public EventoVo findById(@PathVariable("id")  Integer id) {
		EventoVo eventoVo = eventoService.findById(id);
		eventoVo.add(linkTo(methodOn(EventoController.class).findById(id)).withSelfRel());
		return eventoVo;
	}
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"nome"));
		
		Page<EventoVo> eventos = eventoService.findAll(pageable);
		eventos.stream().forEach(p -> p.add(linkTo(methodOn(EventoController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<EventoVo>> pagedModel = assembler.toModel(eventos);
		
		return new ResponseEntity<>(pagedModel,HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
			     consumes = {"application/json","application/xml","application/x-yaml"})
	public EventoVo create(@RequestBody EventoVo eventoVo) {
		EventoVo vo = eventoService.create(eventoVo);
		vo.add(linkTo(methodOn(EdicaoController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
		     consumes = {"application/jso'","application/xml","application/x-yaml"})
	public EventoVo update(@RequestBody EventoVo eventoVo) {
		EventoVo vo = eventoService.update(eventoVo);
		vo.add(linkTo(methodOn(EventoController.class).findById(eventoVo.getId())).withSelfRel());
		return vo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		eventoService.delete(id);
		return ResponseEntity.ok().build();
	}

}
