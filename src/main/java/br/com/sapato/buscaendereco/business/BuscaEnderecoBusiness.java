package br.com.sapato.buscaendereco.business;

import br.com.sapato.buscaendereco.dao.BuscaEnderecoDAO;
import br.com.sapato.buscaendereco.domain.Endereco;

public class BuscaEnderecoBusiness {
	private BuscaEnderecoDAO dao;
	
	public BuscaEnderecoBusiness(BuscaEnderecoDAO dao){
		this.dao = dao;
	}
	
	public Endereco buscaEndecoPorCep(String cep) {
		Endereco endereco = dao.buscaEndecoPorCep(cep);
		return endereco;
	}

	public Endereco buscaValidaEndecoPorCep(String cep) {
		Endereco endereco = null;
		// O CEP possui 8 d�gitos de 0 a 7
		int digCep = 7;

		// Primeira tentativa de busca do cep no dao
		endereco = buscaEndecoPorCep(cep);

		// Loop para tentar possibilidades de CEP incluindo "0" � direita
		while (digCep > 0) {
			if (endereco == null) {
				// caso n�o encontre � verificado se o �ltimo d�gito � zero.
				if (!"0".equals(cep.substring(digCep - 1, digCep))) {
					// Se n�o for zero, substitui por zero
					cep = RPad(cep.substring(0, digCep), 8, '0');
					// tenta buscar novamente com o novo cep
					endereco = buscaEndecoPorCep(cep);
				}
			} else {
				break;
			}
			digCep--;
		}
		return endereco;
	}

	// Metodo utilizado para preencher os zeros � direita do CEP
	public String RPad(String str, Integer length, char car) {
		return str
				+ String.format("%" + (length - str.length()) + "s", "")
						.replace(" ", String.valueOf(car));

	}
}