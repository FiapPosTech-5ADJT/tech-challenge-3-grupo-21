package br.com.fiap.restauranteapi.infraestructure.config.beans;

import br.com.fiap.restauranteapi.application.usecases.AvaliacaoUseCase;
import br.com.fiap.restauranteapi.domain.gateway.AvaliacaoGateway;
import br.com.fiap.restauranteapi.domain.gateway.ReservaGateway;
import br.com.fiap.restauranteapi.domain.service.AvaliacaoService;
import br.com.fiap.restauranteapi.infraestructure.gateway.AvaliacaoGatewayImpl;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.api.AvaliacaoDtoConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.AvaliacaoEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.AvaliacaoRepository;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.ReservaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvaliacaoConfig {
  @Bean
  AvaliacaoUseCase avaliacaoUseCase(AvaliacaoService avaliacaoService) {
    return new AvaliacaoUseCase(avaliacaoService);
  }

  @Bean
  AvaliacaoService avaliacaoService(ReservaGateway reservaGateway, AvaliacaoGateway avaliacaoGateway) {
    return new AvaliacaoService(reservaGateway, avaliacaoGateway);
  }

  @Bean
  AvaliacaoGateway avaliacaoGateway(
    ReservaRepository reservaRepository,
    AvaliacaoRepository avaliacaoRepository,
    AvaliacaoEntityConverter avaliacaoEntityConverter
  ) {
    return new AvaliacaoGatewayImpl(
      reservaRepository,
      avaliacaoRepository,
      avaliacaoEntityConverter
    );
  }

  @Bean
  AvaliacaoEntityConverter avaliacaoEntityConverter() {
    return new AvaliacaoEntityConverter();
  }

  @Bean
  AvaliacaoDtoConverter avaliacaoDtoConverter() {
    return new AvaliacaoDtoConverter();
  }
}
