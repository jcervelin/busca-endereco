package br.com.sapato.buscaendereco.business;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.sapato.buscaendereco.dao.BuscaEnderecoDAO;
import br.com.sapato.buscaendereco.domain.Endereco;

public class BuscaEnderecoBusinessTest {
	private BuscaEnderecoBusiness business;
	private Endereco endereco;
	private Endereco enderecoComTresZerosFinal;
	private Endereco enderecoDoisZerosFinal;
	private Endereco enderecoNulo;
	private String cep = "04112111";
	private String cep2 = "14112111";
	private String cep3 = "14112110";
	private String cep4 = "14112100";
	private String cep5 = "24112111";
	private String cep6 = "24112110";
	private String cep7 = "24112100";
	private String cep8 = "24112000";
	
	@Mock
	private BuscaEnderecoDAO dao;
	
   @Before
   public void init(){
		// Dados para serem comparados com os trazidos pelo servico
		endereco = new Endereco();
		endereco.setCidade("Sao Paulo");
		endereco.setEstado("SP");
		endereco.setLogradouro("Av. Lins de Vasconcelos");
		enderecoComTresZerosFinal = new Endereco();
		enderecoComTresZerosFinal.setCidade("Sao Paulo");
		enderecoComTresZerosFinal.setEstado("SP");
		enderecoComTresZerosFinal.setLogradouro("Av. Vergueiro");
		enderecoDoisZerosFinal = new Endereco();
		enderecoDoisZerosFinal.setCidade("Sao Paulo");
		enderecoDoisZerosFinal.setEstado("SP");
		enderecoDoisZerosFinal.setLogradouro("Rua Pero Correa");
		enderecoNulo = null;

		//mocka métodos do DAO para evitar que o teste execute queries no banco
        MockitoAnnotations.initMocks(this);
        business = spy(new BuscaEnderecoBusiness(dao));
        doReturn(endereco).when(dao).buscaEndecoPorCep(cep);
        doReturn(enderecoNulo).when(dao).buscaEndecoPorCep(cep2);
        doReturn(enderecoNulo).when(dao).buscaEndecoPorCep(cep3);
        doReturn(enderecoDoisZerosFinal).when(dao).buscaEndecoPorCep(cep4);
        doReturn(enderecoNulo).when(dao).buscaEndecoPorCep(cep5);
        doReturn(enderecoNulo).when(dao).buscaEndecoPorCep(cep6);
        doReturn(enderecoNulo).when(dao).buscaEndecoPorCep(cep7);
        doReturn(enderecoComTresZerosFinal).when(dao).buscaEndecoPorCep(cep8);
   }

	// Busca por um cep que deverá ter dois de seus últimos dígitos substituídos
	// por zero
	@Test
	public void buscaEndecoPorCepComDoisZerosNoFinal() {
		Endereco end2 = business.buscaValidaEndecoPorCep(cep2);
		Assert.assertEquals(enderecoDoisZerosFinal, end2);
	}
	
	// Testa busca por um cep correto
	@Test
	public void buscaEndecoPorCep() {
		Endereco end2 = business.buscaValidaEndecoPorCep(cep);
		Assert.assertEquals(endereco, end2);
	}

	// Busca por um cep que deverá ter três de seus últimos dígitos substituídos
	// por zero
	@Test
	public void buscaEndecoPorCepComTresZerosNoFinal() {
		Endereco end2 = business.buscaValidaEndecoPorCep(cep5);
		Assert.assertEquals(enderecoComTresZerosFinal, end2);
	}
}