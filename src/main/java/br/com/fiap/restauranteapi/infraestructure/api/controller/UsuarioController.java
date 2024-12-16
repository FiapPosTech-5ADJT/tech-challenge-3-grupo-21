package br.com.fiap.restauranteapi.infraestructure.api.controller;

import br.com.fiap.restauranteapi.application.usecases.CadastroUsuarioUseCase;
import br.com.fiap.restauranteapi.domain.dto.UsuarioRequestDto;
import br.com.fiap.restauranteapi.domain.dto.UsuarioResponseDto;
import br.com.fiap.restauranteapi.domain.entity.Usuario;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.api.UsuarioDtoConverter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final CadastroUsuarioUseCase cadastroUsuarioUseCase;
    private final UsuarioDtoConverter usuarioDtoConverter;

    public UsuarioController(
            CadastroUsuarioUseCase cadastroUsuarioUseCase,
            UsuarioDtoConverter usuarioDtoConverter
    ) {
        this.cadastroUsuarioUseCase = cadastroUsuarioUseCase;
        this.usuarioDtoConverter = usuarioDtoConverter;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<UsuarioResponseDto> save(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioDtoConverter.toDomain(usuarioRequestDto);
        Usuario usuarioCadastrado = cadastroUsuarioUseCase.cadastrar(usuario);
        UsuarioResponseDto usuarioCadastradoResponse = usuarioDtoConverter.toResponse(usuarioCadastrado);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastradoResponse);
    }
}
