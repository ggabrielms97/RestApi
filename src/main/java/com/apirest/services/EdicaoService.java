package com.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apirest.model.Edicao;
import com.apirest.repository.EdicaoRepository;

import exception.ResourceNotFoundException;
import vo.EdicaoVo;

@Service
public class EdicaoService {

	private EdicaoRepository edicaoRepository;

	 
	public EdicaoService() {
	}

	@Autowired
	public EdicaoService(EdicaoRepository edicaoRepository) {
		this.edicaoRepository = edicaoRepository;
	}

	public EdicaoVo create(EdicaoVo edicaoVo) {
		return EdicaoVo.create(edicaoRepository.save(Edicao.create(edicaoVo)));
	}

	public Page<EdicaoVo> findAll(Pageable pageable) {
		var page = edicaoRepository.findAll(pageable);
		return page.map(this::convertToEdicaoVo);
	}

	private EdicaoVo convertToEdicaoVo(Edicao edicao) {
		return EdicaoVo.create(edicao);
	}

	public EdicaoVo findById(Integer id) {
		var entity = edicaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Edicão não encontrado"));
		return EdicaoVo.create(entity);
	}

	public EdicaoVo update(EdicaoVo edicaoVo) {
		final Optional<Edicao> optional = edicaoRepository.findById(edicaoVo.getId());

		if (!optional.isPresent()) {
			new ResourceNotFoundException("Edicao não encontrada");
		}

		return EdicaoVo.create(edicaoRepository.save(Edicao.create(edicaoVo)));
	}

	public void delete(Integer id) {
		var entity = edicaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Edicao não encontrado"));
		edicaoRepository.delete(entity);
	}

}

