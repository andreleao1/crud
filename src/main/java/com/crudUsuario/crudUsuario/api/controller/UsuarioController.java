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

import com.crudUsuario.crudUsuario.domain.model.Usuario;
import com.crudUsuario.crudUsuario.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> listar() {
		return usuarioService.listar();
	}

	@GetMapping("{usuarioId}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long usuarioId) {
		Optional<Usuario> usuarioEncontrado = usuarioService.buscar(usuarioId);

		if (usuarioEncontrado.isPresent()) {
			return ResponseEntity.ok(usuarioEncontrado.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}

	@PutMapping("/{usuarioId}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long usuarioId, @RequestBody Usuario usuario) {
		Usuario usuarioAtualizado = usuarioService.atualizar(usuarioId, usuario);

		if (usuarioAtualizado != null) {
			return ResponseEntity.ok(usuarioAtualizado);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{usuarioId}")
	public ResponseEntity<Usuario> remover(@PathVariable Long usuarioId) {
		Optional<Usuario> usuarioEncontrado = usuarioService.buscar(usuarioId);

		if (usuarioEncontrado.isPresent()) {
			usuarioService.remover(usuarioId);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
}
