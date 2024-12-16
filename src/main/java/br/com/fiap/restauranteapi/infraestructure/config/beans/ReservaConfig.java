package br.com.fiap.restauranteapi.infraestructure.config.beans;

import br.com.fiap.restauranteapi.application.usecases.AtualizaStatusReservaUseCase;
import br.com.fiap.restauranteapi.application.usecases.CadastroReservaUseCase;
import br.com.fiap.restauranteapi.domain.gateway.ReservaGateway;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;
import br.com.fiap.restauranteapi.domain.gateway.UsuarioGateway;
import br.com.fiap.restauranteapi.domain.service.ReservaService;
import br.com.fiap.restauranteapi.infraestructure.gateway.ReservaGatewayImpl;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.api.ReservaDtoConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.ReservaEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.ReservaRepository;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.RestauranteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservaConfig {
  @Bean
  CadastroReservaUseCase reservaUseCase(ReservaService reservaService) {
    return new CadastroReservaUseCase(reservaService);
  }

  @Bean
  AtualizaStatusReservaUseCase atualizaStatusReservaUseCase(ReservaService reservaService) {
    return new AtualizaStatusReservaUseCase(reservaService);
  }

  @Bean
  ReservaService reservaService(RestauranteGateway restauranteGateway, ReservaGateway reservaGateway, UsuarioGateway usuarioGateway) {
    return new ReservaService(usuarioGateway, restauranteGateway, reservaGateway);
  }

  @Bean
  ReservaGateway reservaGateway(
    ReservaRepository reservaRepository,
    ReservaEntityConverter reservaEntityConverter,
    RestauranteRepository restauranteRepository
  ) {
    return new ReservaGatewayImpl(
      reservaRepository,
      reservaEntityConverter,
      restauranteRepository
    );
  }

  @Bean
  ReservaEntityConverter reservaEntityConverter() {
    return new ReservaEntityConverter();
  }

  @Bean
  ReservaDtoConverter reservaDtoConverter() {
    return new ReservaDtoConverter();
  }
}
