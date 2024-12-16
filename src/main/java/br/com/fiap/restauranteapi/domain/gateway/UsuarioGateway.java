package br.com.fiap.restauranteapi.domain.gateway;

import br.com.fiap.restauranteapi.domain.entity.Usuario;

public interface UsuarioGateway {
    Usuario cadastrar(Usuario restaurante);

    Boolean verificarSeExistePeloCpf(String cpf);

    Boolean verificarSeExistePeloId(Long id);

    Usuario buscarPeloId(Long id);
}
