package br.com.lanchonete.smashpoint.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosProdutoAPI(@JsonAlias("id") int id,
                              @JsonAlias("title") String title,
                              @JsonAlias("price") double price) {
}
