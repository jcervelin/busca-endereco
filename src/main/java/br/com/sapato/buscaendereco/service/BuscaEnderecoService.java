package br.com.sapato.buscaendereco.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.sapato.buscaendereco.business.BuscaEnderecoBusiness;
import br.com.sapato.buscaendereco.dao.BuscaEnderecoDAO;
import br.com.sapato.buscaendereco.domain.Endereco;

@Path("/cep")
public class BuscaEnderecoService {
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg(@PathParam("param") String cep) {

		BuscaEnderecoBusiness business = new BuscaEnderecoBusiness(new BuscaEnderecoDAO());

		// Busca cep. Caso nenhum seja encontrado será retornada mensagem CPF
		// Invalido.
		Endereco end = business.buscaValidaEndecoPorCep(cep);
		if (end == null) {
			// Retorna mensagem de CEP Invalido
			return Response.status(200).entity("CEP Invalido").build();
		}
		// Retorna objeto Endereco no formato json
		return Response.status(200).entity(end).build();

	}
}
