package br.com.fiap.restauranteapi.infraestructure.api.controller;

import br.com.fiap.restauranteapi.application.usecases.BuscarRestauranteUseCase;
import br.com.fiap.restauranteapi.application.usecases.CadastroRestauranteUseCase;
import br.com.fiap.restauranteapi.domain.dto.LocalizacaoDto;
import br.com.fiap.restauranteapi.domain.dto.RestauranteRequestDto;
import br.com.fiap.restauranteapi.domain.dto.RestauranteResponsetDto;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.api.RestauranteDtoConverter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
@AllArgsConstructor
public class RestauranteController {

    private final CadastroRestauranteUseCase cadastroRestauranteUseCase;
    private final BuscarRestauranteUseCase buscaRestauranteUseCase;
    private final RestauranteDtoConverter restauranteDtoConverter;

    @PostMapping
    @Transactional
    public ResponseEntity<RestauranteResponsetDto> criarRestaurante(@RequestBody RestauranteRequestDto restauranteRequestDto){
        Restaurante restauranteEntity = restauranteDtoConverter.toDomain(restauranteRequestDto);
        Restaurante restauranteCadastrado = cadastroRestauranteUseCase.cadastrar(restauranteEntity);
        return ResponseEntity.ok(restauranteDtoConverter.toResponse(restauranteCadastrado));
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<RestauranteResponsetDto>> buscarRestaurante(@RequestBody LocalizacaoDto localizacaoRequestDto){
        List<Restaurante> restaurante = buscaRestauranteUseCase.buscarRestaurantePorLocalizacao(localizacaoRequestDto);
        List<RestauranteResponsetDto> restauranteResponsetDtos = restaurante.stream().map(restauranteDtoConverter::toResponse).toList();
        return ResponseEntity.ok(restauranteResponsetDtos);
    }

}
