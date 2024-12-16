package br.com.fiap.restauranteapi.infraestructure.gateway;

import br.com.fiap.restauranteapi.domain.entity.Usuario;
import br.com.fiap.restauranteapi.domain.gateway.UsuarioGateway;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.UsuarioEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.UsuarioJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.UsuarioRepository;

public class UsuarioGatewayImpl implements UsuarioGateway {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityConverter usuarioEntityConverter;

    public UsuarioGatewayImpl(
            UsuarioRepository usuarioRepository,
            UsuarioEntityConverter usuarioEntityConverter
    ) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioEntityConverter = usuarioEntityConverter;
    }

      @Override
      public Usuario cadastrar(Usuario usuario) {
          UsuarioJPAEntity usuarioEntity = this.usuarioEntityConverter.toEntity(usuario);
          UsuarioJPAEntity usuarioEntitySalvo = this.usuarioRepository.save(usuarioEntity);
          return this.usuarioEntityConverter.toDomainObj(usuarioEntitySalvo);
      }

    @Override
    public Boolean verificarSeExistePeloCpf(String cpf) {
        return this.usuarioRepository.existsByCpf(cpf);
    }

    @Override
    public Boolean verificarSeExistePeloId(Long id) {
        return this.usuarioRepository.existsById(id);
    }

    @Override
    public Usuario buscarPeloId(Long id) {
      UsuarioJPAEntity usuarioEncontrado = this.usuarioRepository.getReferenceById(id);
        return this.usuarioEntityConverter.toDomainObj(usuarioEncontrado);
    }
}
