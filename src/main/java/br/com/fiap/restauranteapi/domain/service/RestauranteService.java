package br.com.fiap.restauranteapi.domain.service;

import br.com.fiap.restauranteapi.domain.dto.LocalizacaoDto;
import br.com.fiap.restauranteapi.domain.entity.HorarioFuncionamento;
import br.com.fiap.restauranteapi.domain.entity.Localizacao;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;

import java.util.List;

public class RestauranteService {

    private final RestauranteGateway restauranteGateway;

    public RestauranteService(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public Restaurante createRestaurante(Restaurante restaurante) {
        if(restaurante == null) {
            throw new IllegalArgumentException("Restaurante não pode ser nulo");
        }
        validarDadosRestaurante(restaurante);
        validarDadosLocalizacao(restaurante.getLocalizacao());
        validarDadosFuncionamento(restaurante.getHorarioFuncionamento());
        return restauranteGateway.create(restaurante);
    }

    public List<Restaurante> searchRestaurantesByLocation(LocalizacaoDto localizacaoDTO) {
        if(localizacaoDTO == null) {
            throw new IllegalArgumentException("Localização não pode ser nula ou vazia");
        }
        return restauranteGateway.findByLocation(localizacaoDTO);
    }

    private static void validarDadosRestaurante(Restaurante restaurante) {
        if (restaurante.getNome().trim().isEmpty() || restaurante.getNome().trim().isBlank()) {
            throw new IllegalArgumentException("Nome do restaurante deve ser informado.");
        }
    }

    private static void validarDadosLocalizacao(Localizacao localizacao) {
        if (localizacao.getNumero() != null && Integer.parseInt(localizacao.getNumero()) < 0) {
            throw new IllegalArgumentException("O número da localização, quando informado, deve ser positivo.");
        }
    }

    private static void validarDadosFuncionamento(HorarioFuncionamento funcionamento) {
        if (funcionamento.getHorarioAbertura().isAfter(funcionamento.getHorarioFechamento())) {
            throw new IllegalArgumentException("Horário de abertura deve ser antes do horário de encerramento.");
        }
    }
}
