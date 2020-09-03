package com.maa.buscaproximidade.repository;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.maa.buscaproximidade.model.Loja;


public interface LojaRepository extends MongoRepository<Loja, String> {

	List<Loja> findByLocationNear(Point point, Distance distance);

}
