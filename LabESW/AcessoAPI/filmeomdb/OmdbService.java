package br.com.locadora.filmeomdb;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OmdbService {
	@Value ("${omdb.api.url}")
	private String omdbApiUrl;

	@Value ("${omdb.api.key}")
	private String apiKey;

	private final RestTemplate restTemplate;

	public OmdbService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public  String buscarFilmePorTitulo(String titulo) {
		URI baseUri = URI.create(this.omdbApiUrl);
		String url = UriComponentsBuilder.fromUri(baseUri)
				.queryParam("apikey", this.apiKey)
				.queryParam("s", titulo)
				.toUriString();

		return restTemplate.getForObject(url, String.class); // Retorna JSON como String
	}

	public  String buscarFilmePorId(String id) {
		URI baseUri = URI.create(this.omdbApiUrl);
		String url = UriComponentsBuilder.fromUri(baseUri)
				.queryParam("apikey", this.apiKey)
				.queryParam("i", id)
				.toUriString();
		return restTemplate.getForObject(url, String.class); // Retorna JSON como String
	}
}
