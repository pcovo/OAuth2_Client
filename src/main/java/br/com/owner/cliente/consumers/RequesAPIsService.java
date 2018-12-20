package br.com.owner.cliente.consumers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.owner.cliente.services.access.token.OAuth2Token;
import br.com.owner.cliente.services.access.token.PasswordTokenService;

@Service
public class RequesAPIsService {

	@Autowired
	PasswordTokenService PasswordTokenService;
	
	public String[] callApiDados() {

		/** authorizedGrantTypes("password") */
		RestTemplate restTemplate = new RestTemplate(); 
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		OAuth2Token token  = PasswordTokenService.getToken("email01@email.com", "12301");
		
		/** Recuperar Dados Protegidos */
		headers.add("Authorization", "Bearer " + token.getAccessToken()); 
		String endpoint = "http://localhost:8000/api/v2/dados"; 
		RequestEntity<Object> request = new RequestEntity<Object>( headers, HttpMethod.GET, URI.create(endpoint) );


		try { 
			ResponseEntity<String[]> resposta = restTemplate.exchange(request, String[].class); 

			if (resposta.getStatusCode().is2xxSuccessful()) { 
				return resposta.getBody(); 
			} else { 
				throw new RuntimeException("sem sucesso"); 
			} 
		} catch (HttpClientErrorException e) { 
			throw new HttpClientErrorException(null, "não foi possível obter os dados"); 
		}
	}
}