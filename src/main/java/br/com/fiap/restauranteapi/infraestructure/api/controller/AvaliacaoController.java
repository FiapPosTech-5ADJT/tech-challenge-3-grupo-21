package br.com.fiap.restauranteapi.infraestructure.api.controller;

import br.com.fiap.restauranteapi.application.usecases.AvaliacaoUseCase;
import br.com.fiap.restauranteapi.domain.dto.AvaliacaoRequestDto;
import br.com.fiap.restauranteapi.domain.dto.AvaliacaoResponseDto;
import br.com.fiap.restauranteapi.domain.entity.Avaliacao;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.api.AvaliacaoDtoConverter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
  private final AvaliacaoUseCase avaliacaoUseCase;
  private final AvaliacaoDtoConverter avaliacaoDtoConverter;

  public AvaliacaoController(AvaliacaoUseCase avaliacaoUseCase, AvaliacaoDtoConverter avaliacaoDtoConverter) {
    this.avaliacaoUseCase = avaliacaoUseCase;
    this.avaliacaoDtoConverter = avaliacaoDtoConverter;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<AvaliacaoResponseDto> save(@Valid @RequestBody AvaliacaoRequestDto avaliacaoRequestDto) {
    Avaliacao avaliacao = avaliacaoDtoConverter.toDomain(avaliacaoRequestDto);
    Avaliacao avaliacaoCadastrada = avaliacaoUseCase.cadastrar(avaliacao);
    AvaliacaoResponseDto avaliacaoCadastradaResponse = avaliacaoDtoConverter.toResponse(avaliacaoCadastrada);

    return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoCadastradaResponse);
  }

}
