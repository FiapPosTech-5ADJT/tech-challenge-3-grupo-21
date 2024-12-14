package br.com.fiap.restauranteapi.infraestructure.config.beans;

import br.com.fiap.restauranteapi.application.usecases.AtualizaStatusReservaUseCase;
import br.com.fiap.restauranteapi.application.usecases.CadastroReservaUseCase;
import br.com.fiap.restauranteapi.domain.gateway.ReservaGateway;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;
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
  CadastroReservaUseCase reservaUseCase(
    //UsuarioGateway usuarioGateway,
    RestauranteGateway restauranteGateway,
    ReservaGateway reservaGateway
  ) {
    return new CadastroReservaUseCase(/*usuarioGateway,*/ restauranteGateway, reservaGateway);
  }

  @Bean
  AtualizaStatusReservaUseCase atualizaStatusReservaUseCase(ReservaGateway reservaGateway) {
    return new AtualizaStatusReservaUseCase(reservaGateway);
  }

  @Bean
  ReservaGateway reservaGateway(
    ReservaRepository reservaRepository,
    ReservaEntityConverter reservaEntityConverter,
    //UsuarioRepository usuarioRepository,
    RestauranteRepository restauranteRepository
  ) {
    return new ReservaGatewayImpl(
      reservaRepository,
      reservaEntityConverter,
      //usuarioRepository,
      restauranteRepository
    );
  }

  @Bean
  ReservaEntityConverter reservaEntityConverter(/*UsuarioEntityConverter usuarioEntityConverter*/) {
    return new ReservaEntityConverter(/*usuarioEntityConverter*/);
  }

  @Bean
  ReservaDtoConverter reservaDtoConverter() {
    return new ReservaDtoConverter();
  }
}
