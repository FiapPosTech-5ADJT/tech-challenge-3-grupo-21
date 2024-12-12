package br.com.fiap.restauranteapi.application.usecases;

import br.com.fiap.restauranteapi.domain.entity.HorarioFuncionamento;
import br.com.fiap.restauranteapi.domain.entity.Localizacao;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;
import br.com.fiap.restauranteapi.domain.service.RestauranteService;

import java.time.Duration;

public class CadastroRestauranteUseCase {
    private static final int TEMPO_FUNCIONAMENTO_MINIMO = 3;

    private final RestauranteService restauranteService;

    public CadastroRestauranteUseCase(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    public Restaurante cadastrar(Restaurante restaurante) {
        validarDadosRestaurante(restaurante);
        validarDadosLocalizacao(restaurante.getLocalizacao());
        validarDadosFuncionamento(restaurante.getHorarioFuncionamento());

        return this.restauranteService.createRestaurante(restaurante);
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

        int tempoFuncionamento = Duration.between(
                funcionamento.getHorarioAbertura(),
                funcionamento.getHorarioFechamento()
        ).toHoursPart();

        String fimDaFraseComPluralCorreto = (TEMPO_FUNCIONAMENTO_MINIMO > 1 ? "s." : ".");

        if (tempoFuncionamento < TEMPO_FUNCIONAMENTO_MINIMO) {
            throw new IllegalArgumentException("Tempo de funcionamento mínimo deve ser de pelo menos "
                    + TEMPO_FUNCIONAMENTO_MINIMO + " hora" + fimDaFraseComPluralCorreto);
        }
    }
}
