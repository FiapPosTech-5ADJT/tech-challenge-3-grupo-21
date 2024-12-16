package br.com.fiap.restauranteapi.application.usecases;

import br.com.fiap.restauranteapi.domain.entity.Usuario;
import br.com.fiap.restauranteapi.domain.service.UsuarioService;

public class CadastroUsuarioUseCase {
    private final UsuarioService usuarioService;

    public CadastroUsuarioUseCase(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario cadastrar(Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario);
    }
}
