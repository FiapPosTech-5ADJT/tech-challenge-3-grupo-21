package br.com.fiap.restauranteapi.infraestructure.config.beans;

import br.com.fiap.restauranteapi.application.usecases.CadastroUsuarioUseCase;
import br.com.fiap.restauranteapi.domain.gateway.UsuarioGateway;
import br.com.fiap.restauranteapi.domain.service.UsuarioService;
import br.com.fiap.restauranteapi.infraestructure.gateway.UsuarioGatewayImpl;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.api.UsuarioDtoConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.UsuarioEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {
    @Bean
    CadastroUsuarioUseCase cadastroUsuarioUseCase(UsuarioService usuarioService) {
        return new CadastroUsuarioUseCase(usuarioService);
    }

    @Bean
    UsuarioService usuarioService(UsuarioGateway usuarioGateway) {
        return new UsuarioService(usuarioGateway);
    }

    @Bean
    UsuarioGateway usuarioGateway(
            UsuarioRepository usuarioRepository,
            UsuarioEntityConverter usuarioEntityConverter
    ) {
        return new UsuarioGatewayImpl(usuarioRepository, usuarioEntityConverter);
    }

    @Bean
    UsuarioEntityConverter usuarioEntityConverter() {
        return new UsuarioEntityConverter();
    }

    @Bean
    UsuarioDtoConverter usuarioDtoConverter() {
        return new UsuarioDtoConverter();
    }
}
