package br.com.fiap.restauranteapi.domain.service;

import br.com.fiap.restauranteapi.domain.entity.Usuario;
import br.com.fiap.restauranteapi.domain.gateway.UsuarioGateway;

public class UsuarioService {
    private final UsuarioGateway usuarioGateway;

    public UsuarioService(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        validarSeUsuarioJaExiste(usuario);
        return usuarioGateway.cadastrar(usuario);
    }

    private void validarSeUsuarioJaExiste(Usuario usuario) {
        if (usuarioGateway.verificarSeExistePeloCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("Usuário com CPF " + usuario.getCpf() + " já existe.");
        }
    }
}
