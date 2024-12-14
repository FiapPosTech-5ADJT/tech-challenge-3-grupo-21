package br.com.fiap.restauranteapi.infraestructure.config.beans;

import br.com.fiap.restauranteapi.application.usecases.BuscarRestauranteUseCase;
import br.com.fiap.restauranteapi.application.usecases.CadastroRestauranteUseCase;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;
import br.com.fiap.restauranteapi.domain.service.RestauranteService;
import br.com.fiap.restauranteapi.infraestructure.gateway.RestauranteGatewayImpl;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.api.RestauranteDtoConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.RestauranteEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.RestauranteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {
    @Bean
    public CadastroRestauranteUseCase cadastroRestauranteUseCase(RestauranteService restauranteService) {
        return new CadastroRestauranteUseCase(restauranteService);
    }

    @Bean
    public BuscarRestauranteUseCase buscaRestauranteUseCase(RestauranteService restauranteService) {
        return new BuscarRestauranteUseCase(restauranteService);
    }

    @Bean
    public RestauranteGateway restauranteGateway(RestauranteRepository restauranteRepository, RestauranteEntityConverter restauranteEntityConverter) {
        return new RestauranteGatewayImpl(restauranteRepository, restauranteEntityConverter);
    }

    @Bean
    public RestauranteEntityConverter restauranteEntityConverter() {
        return new RestauranteEntityConverter();
    }

    @Bean
    public RestauranteService restauranteService(RestauranteGateway restauranteGateway) {
        return new RestauranteService(restauranteGateway);
    }

    @Bean
    public RestauranteDtoConverter restauranteDtoConverter() {
        return new RestauranteDtoConverter();
    }
}
