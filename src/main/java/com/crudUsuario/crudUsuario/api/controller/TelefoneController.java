package com.crudUsuario.crudUsuario.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crudUsuario.crudUsuario.domain.model.Telefone;
import com.crudUsuario.crudUsuario.domain.service.TelefoneService;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

	@Autowired
	private TelefoneService telefoneService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Telefone> listar() {
		return telefoneService.listar();
	}

	@GetMapping("{telefoneId}")
	public ResponseEntity<Telefone> buscar(@PathVariable Long telefoneId) {
		Optional<Telefone> telefoneEncontrado = telefoneService.buscar(telefoneId);

		if (telefoneEncontrado.isPresent()) {
			return ResponseEntity.ok(telefoneEncontrado.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Telefone salvar(@RequestBody Telefone telefone) {
		return telefoneService.salvar(telefone);
	}

	@PutMapping("/{telefoneId}")
	public ResponseEntity<Telefone> atualizar(@PathVariable Long telefoneId, @RequestBody Telefone telefone) {
		Telefone telefoneAtualizado = telefoneService.atualizar(telefoneId, telefone);

		if (telefoneAtualizado != null) {
			return ResponseEntity.ok(telefoneAtualizado);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{telefoneId}")
	public ResponseEntity<Telefone> remover(@PathVariable Long telefoneId) {
		Optional<Telefone> telefoneEncontrado = telefoneService.buscar(telefoneId);

		if (telefoneEncontrado.isPresent()) {
			telefoneService.remover(telefoneId);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
}
