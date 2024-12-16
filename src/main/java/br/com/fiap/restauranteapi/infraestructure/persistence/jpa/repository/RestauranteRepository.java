package br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository;

import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.RestauranteJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteJPAEntity, Long> {

    @Query("SELECT r FROM RestauranteJPAEntity r WHERE " +
            "(:nome IS NULL OR UPPER(r.nome) LIKE CONCAT('%', UPPER(:nome), '%')) AND " +
            "(:endereco IS NULL OR (UPPER(r.logradouro) LIKE CONCAT('%', UPPER(:endereco), '%') OR UPPER(r.bairro) LIKE CONCAT('%', UPPER(:endereco), '%') OR UPPER(r.cidade) LIKE CONCAT('%', UPPER(:endereco), '%') OR UPPER(r.estado) LIKE CONCAT('%', UPPER(:endereco), '%') OR UPPER(r.pais) LIKE CONCAT('%', UPPER(:endereco), '%'))) AND " +
            "(:tipoCozinha IS NULL OR UPPER(r.tipoRestaurante) LIKE CONCAT('%', UPPER(:tipoCozinha), '%')) AND " +
            "(:cidade IS NULL OR UPPER(r.cidade) LIKE CONCAT('%', UPPER(:cidade), '%')) AND " +
            "(:estado IS NULL OR UPPER(r.estado) LIKE CONCAT('%', UPPER(:estado), '%')) AND " +
            "(:cep IS NULL OR UPPER(r.cep) LIKE CONCAT('%', UPPER(:cep), '%'))")
    public List<RestauranteJPAEntity> findByLocation(String nome, String tipoCozinha, String endereco, String cidade, String estado, String cep);
}
