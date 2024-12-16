package br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository;

import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.UsuarioJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioJPAEntity, Long> {
    boolean existsByCpf(String cpf);
}
