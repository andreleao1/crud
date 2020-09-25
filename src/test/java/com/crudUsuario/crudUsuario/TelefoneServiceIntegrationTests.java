package com.crudUsuario.crudUsuario;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crudUsuario.crudUsuario.domain.model.Telefone;
import com.crudUsuario.crudUsuario.domain.service.TelefoneService;

@RunWith(SpringRunner.class)
@SpringBootTest
class TelefoneServiceIntegrationTests {

	@Autowired
	private TelefoneService telefoneService;

	@Test
	void deveInserirTelefoneComSucesso() {

		Telefone telefone = new Telefone();
		Telefone telefoneSalvo = null;

		telefone.setDdd("87");
		telefone.setNumero("987502141");

		telefoneSalvo = telefoneService.salvar(telefone);

		assertThat(telefoneSalvo).isNotNull();
		assertThat(telefoneSalvo.getId()).isNotNull();
	}

	@Test
	void deveListarTelefonescomSucesso() {

		List<Telefone> listaTelefones = null;

		listaTelefones = telefoneService.listar();

		assertThat(listaTelefones).isNotNull();
	}

	@Test
	void deveBuscarTelefoneComSucesso() {
		Telefone telefone;

		telefone = telefoneService.buscar(1L).get();

		assertThat(telefone).isNotNull();
	}

	@Test
	void deveAtualizarTelefoneComSucesso() {
		Telefone novoTelefone = new Telefone();
		Telefone telefoneAtualizado = null;
		novoTelefone.setNumero("99999999");

		telefoneAtualizado = telefoneService.atualizar(2L, novoTelefone);

		assertThat(telefoneAtualizado).isNotNull();
		assertThat(telefoneAtualizado.getNumero()).isEqualTo(novoTelefone.getNumero());
	}
}
