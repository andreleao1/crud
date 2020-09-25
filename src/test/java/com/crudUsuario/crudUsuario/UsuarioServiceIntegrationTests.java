package com.crudUsuario.crudUsuario;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crudUsuario.crudUsuario.domain.model.Usuario;
import com.crudUsuario.crudUsuario.domain.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsuarioServiceIntegrationTests {

	@Autowired
	private UsuarioService usuarioService;

	@Test
	void deveInserirUsuarioComSucesso() {

		Usuario usuario = new Usuario();

		usuario.setNome("João da Silva");

		usuarioService.salvar(usuario);

		assertThat(usuario).isNotNull();
		assertThat(usuario.getId()).isNotNull();
		assertThat(usuario.getTelefones()).isEmpty();

	}

	@Test
	void deveListarUsuarioscomSucesso() {

		List<Usuario> listaUsuarios = null;

		listaUsuarios = usuarioService.listar();

		assertThat(listaUsuarios).isNotNull();
	}

	@Test
	void deveBuscarUsuarioComSucesso() {
		Usuario usuario;

		usuario = usuarioService.buscar(1L).get();

		assertThat(usuario).isNotNull();
	}

	@Test
	void deveAtualizarUsuarioComSucesso() {
		Usuario novoUsuario = new Usuario();
		Usuario usuarioAtualizado = null;
		novoUsuario.setNome("Maria");

		usuarioAtualizado = usuarioService.atualizar(2L, novoUsuario);

		assertThat(usuarioAtualizado).isNotNull();
		assertThat(usuarioAtualizado.getNome()).isEqualTo(novoUsuario.getNome());
	}
}
