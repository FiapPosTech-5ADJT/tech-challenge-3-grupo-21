package br.com.fiap.restauranteapi.infraestructure.gateway;


import br.com.fiap.restauranteapi.domain.entity.ReservaDetalhada;
import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.domain.gateway.ReservaGateway;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.ReservaEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.ReservaJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.ReservaRepository;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.RestauranteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReservaGatewayImpl implements ReservaGateway {
  private final ReservaRepository reservaRepository;
  private final ReservaEntityConverter reservaEntityConverter;

  //private final UsuarioRepository usuarioRepository;
  private final RestauranteRepository restauranteRepository;

  public ReservaGatewayImpl(
    ReservaRepository reservaRepository,
    ReservaEntityConverter reservaEntityConverter,
    //UsuarioRepository usuarioRepository,
    RestauranteRepository restauranteRepository
  ) {
    this.reservaRepository = reservaRepository;
    this.reservaEntityConverter = reservaEntityConverter;
    //this.usuarioRepository = usuarioRepository;
    this.restauranteRepository = restauranteRepository;
  }

  @Override
  public List<Reserva> obterTodasAsReservasOcupadasNoHorarioAgendado(
    Long idRestaurante,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim
  ) {
    List<ReservaJPAEntity> reservasOcupadas = reservaRepository.findAllOccupiedReservationBetweenDates(
      idRestaurante,
      dataHoraInicio,
      dataHoraFim
    );

    return reservasOcupadas.stream().map(reservaEntityConverter::toDomainObj).toList();
  }

  @Override
  public Reserva cadastrar(Reserva reserva) {
    ReservaJPAEntity reservaEntity = this.reservaEntityConverter.toEntity(reserva);
    //reservaEntity.setUsuarioEntity(usuarioRepository.getReferenceById(reserva.getIdUsuario()));
    reservaEntity.setRestauranteEntity(restauranteRepository.getReferenceById(reserva.getIdRestaurante()));

    ReservaJPAEntity reservaEntityCadastrado = this.reservaRepository.save(reservaEntity);

    return this.reservaEntityConverter.toDomainObj(reservaEntityCadastrado);
  }

  @Override
  public List<Reserva> obterReservasDoUsuarioNoHorarioAgendado(
    Long idUsuario,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim
  ) {
    List<ReservaJPAEntity> reservasOcupadas = this.reservaRepository.findAllByIdUsuarioBetweenDates(
      idUsuario,
      dataHoraInicio,
      dataHoraFim
    );

    return reservasOcupadas.stream().map(reservaEntityConverter::toDomainObj).toList();
  }

  @Override
  public Reserva buscarPeloId(Long id) {
    Optional<ReservaJPAEntity> reserva = this.reservaRepository.findById(id);

    if (reserva.isPresent()) {
      return reservaEntityConverter.toDomainObj(reserva.get());
    }

    return null;
  }

  @Override
  public List<ReservaDetalhada> buscarDetalhesPeloIdDoRestaurante(Long idRestaurante) {
    Optional<List<ReservaJPAEntity>> reservas = this.reservaRepository.findAllByRestauranteEntityId(idRestaurante);

    if (reservas.isPresent()) {
      return reservas.get().stream().map(reservaEntityConverter::toDetailedDomainObj).toList();
    }

    return null;
  }

  public List<ReservaDetalhada> buscarDetalhesPeloIdDoRestaurante(
    Long idRestaurante,
    int pagina,
    int numeroItensPorPagina,
    String ordenarPor,
    boolean ordemCrescente
  ) {
    Sort sort = Sort.by(ordenarPor);
    Pageable paging = PageRequest.of(
      pagina,
      numeroItensPorPagina,
      ordemCrescente ? sort.ascending() : sort.descending()
    );

    Optional<List<ReservaJPAEntity>> reservas = this.reservaRepository.findAllByRestauranteEntityId(
      idRestaurante,
      paging
    );

    if (reservas.isPresent()) {
      return reservas.get().stream().map(reservaEntityConverter::toDetailedDomainObj).toList();
    }

    return null;
  }

  @Override
  public Boolean verificarSeExiste(Long id) {
    return reservaRepository.existsById(id);
  }

  @Override
  public Reserva alterarStatus(Long idReserva, StatusReserva novoStatus) {
    ReservaJPAEntity reservaEntity = this.reservaRepository.getReferenceById(idReserva);
    reservaEntity.setStatus(novoStatus);

    ReservaJPAEntity reservaAlterada = this.reservaRepository.save(reservaEntity);
    return reservaEntityConverter.toDomainObj(reservaAlterada);
  }
}
