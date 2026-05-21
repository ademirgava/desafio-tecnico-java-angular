package br.com.desafio.accenture.application.dto;

import java.util.List;

public record ListaResponseDTO<T>(List<T> itens) {

}
