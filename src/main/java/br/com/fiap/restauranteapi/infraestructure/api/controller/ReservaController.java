package br.com.fiap.restauranteapi.infraestructure.api.controller;

import br.com.fiap.restauranteapi.application.usecases.AtualizaStatusReservaUseCase;
import br.com.fiap.restauranteapi.application.usecases.CadastroReservaUseCase;
import br.com.fiap.restauranteapi.domain.dto.AtualizaStatusReservaRequestDto;
import br.com.fiap.restauranteapi.domain.dto.ReservaRequestDto;
import br.com.fiap.restauranteapi.domain.dto.ReservaResponsetDto;
import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.api.ReservaDtoConverter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    private final CadastroReservaUseCase cadastroReservaUseCase;
    private final AtualizaStatusReservaUseCase atualizaStatusReservaUseCase;
    private final ReservaDtoConverter reservaDtoConverter;

    public ReservaController(
            CadastroReservaUseCase cadastroReservaUseCase,
            AtualizaStatusReservaUseCase atualizaStatusReservaUseCase,
            ReservaDtoConverter reservaDtoConverter
    ) {
        this.cadastroReservaUseCase = cadastroReservaUseCase;
        this.atualizaStatusReservaUseCase = atualizaStatusReservaUseCase;
        this.reservaDtoConverter = reservaDtoConverter;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReservaResponsetDto> save(@Valid @RequestBody ReservaRequestDto reservaRequestDto) {
        Reserva reserva = reservaDtoConverter.toDomain(reservaRequestDto);
        Reserva reservaCadastrada = cadastroReservaUseCase.cadastrar(reserva);
        ReservaResponsetDto reservaCadastradaResponse = reservaDtoConverter.toResponse(reservaCadastrada);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCadastradaResponse);
    }

    @PatchMapping("/{idReserva}/status")
    @Transactional
    public ResponseEntity<ReservaResponsetDto> updateStatus(
            @PathVariable Long idReserva,
            @RequestBody AtualizaStatusReservaRequestDto atualizaStatusReservaRequestDto
    ) {
        atualizaStatusReservaUseCase.atualizar(idReserva, atualizaStatusReservaRequestDto.status());

        return ResponseEntity.noContent().build();
    }
}
