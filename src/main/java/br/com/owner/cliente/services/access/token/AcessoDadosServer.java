package br.com.owner.cliente.services.access.token;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable 
@ToString
public class AcessoDadosServer {

	@Getter 
	@Setter 
	@Column(name = "token_dados_server")
	private String accessToken;
	
}
