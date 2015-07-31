package br.com.sapato.buscaendereco.dao;

import br.com.sapato.buscaendereco.domain.Endereco;

public class BuscaEnderecoDAO {

	public Endereco buscaEndecoPorCep(String cep) {
		return dadosMockados(cep);
	}

	// Dados mockados que simulam uma suposta consulta em uma base de dados
	public Endereco dadosMockados(String cep) {
		Endereco endereco = null;
		if ("04112111".equals(cep)) {
			endereco = new Endereco();
			endereco.setCidade("Sao Paulo");
			endereco.setEstado("SP");
			endereco.setLogradouro("Av. Lins de Vasconcelos");
		} else if ("14112000".equals(cep)) {
			endereco = new Endereco();
			endereco.setCidade("Sao Paulo");
			endereco.setEstado("SP");
			endereco.setLogradouro("Rua Pero Correa");
		} else if ("24112100".equals(cep)) {
			endereco = new Endereco();
			endereco.setCidade("Sao Paulo");
			endereco.setEstado("SP");
			endereco.setLogradouro("Av. Vergueiro");
		}
		return endereco;
	}

}