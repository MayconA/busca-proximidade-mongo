package com.maa.buscaproximidade.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maa.buscaproximidade.model.Loja;
import com.maa.buscaproximidade.service.LojaService;

@RestController
@RequestMapping("/lojas")
public class LojaController {

	@Autowired
	private LojaService service;

	@GetMapping
	public ResponseEntity<List<Loja>> getAllLojas() {

		List<Loja> lojas = service.findAll();

		return ResponseEntity.ok(lojas);
	}

	@GetMapping("/proximas")
	public ResponseEntity<List<Loja>> getLojasProximas(@RequestParam(name = "lat", required = false) String latitude,
			@RequestParam(name = "long", required = false) String longitude,
			@RequestParam(name = "ender", required = false) String endereco, @RequestParam("raio") double raio) {

		List<Loja> lojas = null;

		if ((latitude == null) || (longitude == null)) {
			lojas = service.lojasProximasDoEndereco(endereco, raio);

		} else {
			lojas = service.lojasProximasDaCoordenadas(longitude, latitude, raio);
		}

		return ResponseEntity.ok(lojas);

	}

	@PostMapping
	public ResponseEntity<Loja> addLoja(@RequestBody Loja loja) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.addLoja(loja));

	}

}
