package com.crudUsuario.crudUsuario.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudUsuario.crudUsuario.domain.model.Telefone;
import com.crudUsuario.crudUsuario.domain.repository.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefoneRepository;

	public Telefone salvar(Telefone telefone) {
		return telefoneRepository.save(telefone);
	}

	public List<Telefone> listar() {
		return telefoneRepository.findAll();
	}

	public Optional<Telefone> buscar(Long telefoneId) {
		return telefoneRepository.findById(telefoneId);
	}

	public Telefone atualizar(Long telefoneId, Telefone telefone) {
		Optional<Telefone> telefoneEncontrado = telefoneRepository.findById(telefoneId);

		if (telefoneEncontrado.isPresent()) {
			BeanUtils.copyProperties(telefone, telefoneEncontrado.get(), "id");
			telefoneRepository.save(telefoneEncontrado.get());
			return telefoneEncontrado.get();
		}

		return null;
	}

	public void remover(Long telefoneId) {
		telefoneRepository.deleteById(telefoneId);
	}
}
