package br.com.fiap.restauranteapi.infraestructure.persistence.restaurante.repository;

import br.com.fiap.restauranteapi.domain.DTO.LocalizacaoDTO;
import br.com.fiap.restauranteapi.infraestructure.persistence.restaurante.entity.RestauranteJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteJpa, Long> {

    @Query("SELECT r FROM RestauranteJpa r WHERE " +
            "(:nome IS NULL OR r.nome LIKE %:nome%) AND " +
            "(:endereco IS NULL OR r.logradouro LIKE %:endereco% OR r.bairro LIKE %:endereco% OR r.cidade LIKE %:endereco% OR r.estado LIKE %:endereco% OR r.pais LIKE %:endereco%) AND " +
            "(:tipoCozinha IS NULL OR r.tipoRestaurante LIKE %:tipoCozinha%) AND " +
            "(:cidade IS NULL OR r.cidade LIKE %:cidade%) AND " +
            "(:estado IS NULL OR r.estado LIKE %:estado%) AND " +
            "(:cep IS NULL OR r.cep LIKE %:cep%)")
    public List<RestauranteJpa> findByLocation(String nome, String tipoCozinha, String endereco, String cidade, String estado, String cep);
}
