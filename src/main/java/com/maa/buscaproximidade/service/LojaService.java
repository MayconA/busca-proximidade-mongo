package com.maa.buscaproximidade.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.google.maps.errors.ApiException;
import com.maa.buscaproximidade.model.Loja;
import com.maa.buscaproximidade.repository.LojaRepository;

@Service
public class LojaService {

	@Autowired
	private LojaRepository repo;

	@Autowired
	private GeolocalizacaoService geolocalizacaoService;

	public List<Loja> findAll() {

		return repo.findAll();
	}

	public List<Loja> lojasProximasDaCoordenadas(String longitude, String latitude, double raio) {

		Point point = new Point(Double.valueOf(longitude), Double.valueOf(latitude));

		Distance distancia = new Distance(raio, Metrics.KILOMETERS);

		return repo.findByLocationNear(point, distancia);
	}

	public List<Loja> lojasProximasDoEndereco(String endereco, double raio) {

		List<Double> coordenadas = null;

		try {
			coordenadas = geolocalizacaoService.obterCoordenadasDo(endereco);
		} catch (ApiException | InterruptedException | IOException e) {

			e.printStackTrace();
		}

		Point point = new Point(coordenadas.get(0), coordenadas.get(1));

		Distance distancia = new Distance(raio, Metrics.KILOMETERS);

		return repo.findByLocationNear(point, distancia);
	}

	public Loja addLoja(Loja loja) {
		return repo.save(loja);

	}
}
