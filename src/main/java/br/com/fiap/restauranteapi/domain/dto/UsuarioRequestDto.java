package br.com.fiap.restauranteapi.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioRequestDto(
        @Schema(example = "33014076090")
        @CPF(message = "CPF inválido")
        String cpf,

        @Schema(example = "João da Silva")
        @NotBlank(message = "nome não pode ser branco")
        @Size(min = 3, message = "digite um nome valido")
        @Size(max = 100, message = "Limite de 100 Caracteres")
        String nome,

        @Schema(example = "11")
        @Pattern(regexp = "\\d{2}", message = "DDD inválido. Deve conter exatamente 2 dígitos.")
        String ddd,

        @Schema(example = "986417485")
        @Pattern(regexp = "^(?!\\d*(\\d)\\1{7,8})\\d{8,9}$", message = "Telefone inválido")
        String telefone,

        @Schema(example = "finder@restaurantfinder.com")
        @Email(message = "Email invalido")
        String email,

        @Schema(hidden = true)
        LocalDate dataCadastro
) {

}
