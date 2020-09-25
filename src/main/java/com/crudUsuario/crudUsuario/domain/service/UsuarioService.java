package com.crudUsuario.crudUsuario.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudUsuario.crudUsuario.domain.model.Usuario;
import com.crudUsuario.crudUsuario.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> buscar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId);
	}

	public Usuario atualizar(Long usuarioId, Usuario usuario) {
		Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(usuarioId);

		if (usuarioEncontrado.isPresent()) {
			BeanUtils.copyProperties(usuario, usuarioEncontrado.get(), "id");
			usuarioRepository.save(usuarioEncontrado.get());
			return usuarioEncontrado.get();
		}

		return null;
	}

	public void remover(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}
}
