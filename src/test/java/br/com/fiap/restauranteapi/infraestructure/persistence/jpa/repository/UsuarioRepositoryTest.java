package br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository;

import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.entity.Usuario;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.UsuarioEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.RestauranteJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.UsuarioJPAEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    UsuarioEntityConverter usuarioEntityConverter = new UsuarioEntityConverter();

    AutoCloseable openMocks;
    @BeforeEach
    void setUp(){
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCriarUsuario() {
        Usuario usuario = criarUsuario();
        UsuarioJPAEntity usuarioJpa = toJpa(usuario);
        when(usuarioRepository.save(any(UsuarioJPAEntity.class))).thenReturn(usuarioJpa);
        UsuarioJPAEntity usuarioSalvo = usuarioRepository.save(usuarioJpa);
        assertThat(usuarioSalvo).isNotNull()
                .isInstanceOf(UsuarioJPAEntity.class);

        assertThat(usuarioSalvo.getId()).isSameAs(usuario.getId());

        verify(usuarioRepository, times(1)).save(any(UsuarioJPAEntity.class));
    }

    private Usuario criarUsuario() {
        return new Usuario(1L, "32469518024", "João da Silva", "19", "998765432", "joaodasilva@gmail.com", LocalDate.now());
    }

    @Test
    void devePermitirBuscarUsuario() {
        Usuario usuario = criarUsuario();
        UsuarioJPAEntity usuarioJpa = toJpa(usuario);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioJpa));

        Optional<UsuarioJPAEntity> usuarioEncontrado = usuarioRepository.findById(1L);

        assertThat(usuarioEncontrado).isPresent();
        assertThat(usuarioEncontrado.get().getId()).isEqualTo(usuarioJpa.getId());

        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void devePermitirBuscarTodosUsuarios() {
        UsuarioJPAEntity usuario1 = toJpa(criarUsuario());
        UsuarioJPAEntity usuario2 = toJpa(criarUsuario());
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario1, usuario2));

        List<UsuarioJPAEntity> usuarios = usuarioRepository.findAll();

        assertThat(usuarios).isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(usuario1, usuario2);

        verify(usuarioRepository, times(1)).findAll();
    }

    private UsuarioJPAEntity toJpa(Usuario usuario) {
        return usuarioEntityConverter.toEntity(usuario);
    }
}