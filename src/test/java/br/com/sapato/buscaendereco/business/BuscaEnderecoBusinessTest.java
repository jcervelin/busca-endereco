package br.com.sapato.buscaendereco.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.sapato.buscaendereco.domain.Endereco;

public class BuscaEnderecoBusinessTest {
	private Endereco endereco;
	private Endereco enderecoComTresZerosFinal;
	private Endereco enderecoDoisZerosFinal;

	// Dados para serem comparados com os trazidos pelo servico
	@Before
	public void populaEnderecos() {
		endereco = new Endereco();
		endereco.setCidade("Sao Paulo");
		endereco.setEstado("SP");
		endereco.setLogradouro("Av. Lins de Vasconcelos");
		enderecoComTresZerosFinal = new Endereco();
		enderecoComTresZerosFinal.setCidade("Sao Paulo");
		enderecoComTresZerosFinal.setEstado("SP");
		enderecoComTresZerosFinal.setLogradouro("Rua Pero Correa");
		enderecoDoisZerosFinal = new Endereco();
		enderecoDoisZerosFinal.setCidade("Sao Paulo");
		enderecoDoisZerosFinal.setEstado("SP");
		enderecoDoisZerosFinal.setLogradouro("Av. Vergueiro");
	}

	// Testa busca por um cep correto
	@Test
	public void buscaEndecoPorCep() {
		String cep = "04112111";
		BuscaEnderecoBusiness business = new BuscaEnderecoBusiness();
		Endereco end2 = business.buscaValidaEndecoPorCep(cep);
		Assert.assertEquals(endereco, end2);
	}

	// Busca por um cep que deverá ter três de seus últimos dígitos substituídos
	// por zero
	@Test
	public void buscaEndecoPorCepComTresZerosNoFinal() {
		String cep = "14112111";
		BuscaEnderecoBusiness business = new BuscaEnderecoBusiness();
		Endereco end2 = business.buscaValidaEndecoPorCep(cep);
		Assert.assertEquals(enderecoComTresZerosFinal, end2);
	}

	// Busca por um cep que deverá ter dois de seus últimos dígitos substituídos
	// por zero
	@Test
	public void buscaEndecoPorCepComDoisZerosNoFinal() {
		String cep = "24112111";

		BuscaEnderecoBusiness business = new BuscaEnderecoBusiness();
		Endereco end2 = business.buscaValidaEndecoPorCep(cep);

		Assert.assertEquals(enderecoDoisZerosFinal, end2);
	}
}