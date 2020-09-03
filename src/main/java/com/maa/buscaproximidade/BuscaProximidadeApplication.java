package com.maa.buscaproximidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuscaProximidadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuscaProximidadeApplication.class, args);
	}

}


/*
 *  1- long Lat
 * 	001- -22.402614  -47.565547
	050 -22.415780 -47.562486
	casa -22.395843 -47.581336

	2-testar coordenada
	http://localhost:8080/lojas/proximas/?long=-22.395843&lat=-47.581336&raio=2
	
	teste endereço
	http://localhost:8080/lojas/proximas/?ender=R.%20Dezoito,%203267%20-%20Parque%20Universitario,%20Rio%20Claro%20-%20SP,%2013504-478&raio=2
	
	R. Dezoito, 3267 - Parque Universitario, Rio Claro - SP, 13504-478
	
	
	
	3-how to
	criar index na coleção 
	db.locations.createIndex( { location : "2dsphere" } )
	
	a coleção precisa ter o seguinte formato, principalmente a parte das coordendas
	
	db.lojas.insertOne(
{
  	"codLoja": "002",
  	"apelido": "Araras",
  	"location": { "type": "Point", "coordinates": [-22.351621, -47.386110] }
}
	);

	
	posso criar um 
	
 * 
 * */
