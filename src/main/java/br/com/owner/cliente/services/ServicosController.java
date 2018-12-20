package br.com.owner.cliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.owner.cliente.consumers.RequesAPIsService;

@RestController
@RequestMapping("/api/cliente")
public class ServicosController {

	@Autowired
	private RequesAPIsService requesAPIs;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> nomes() {
		
		String[] nomes = requesAPIs.callApiDados();

		if (nomes.length > 0) {
			return new ResponseEntity<>(nomes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}