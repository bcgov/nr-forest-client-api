package ca.bc.gov.api.oracle.legacy.service;

import ca.bc.gov.api.oracle.legacy.dto.ClientLocationDto;
import ca.bc.gov.api.oracle.legacy.dto.YesNoEnum;
import ca.bc.gov.api.oracle.legacy.exception.ClientNotFoundException;
import ca.bc.gov.api.oracle.legacy.repository.ClientLocationRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientLocationService {

  private final ClientLocationRepository repository;

  /**
   * Counts the number of locations associated with a given client.
   *
   * @param clientNumber the client number for which to count locations
   * @return a {@link Mono} emitting the count of locations
   */
  public Mono<Long> countClientLocations(String clientNumber) {
    return repository.countByClientNumber(clientNumber);
  }

  /**
   * Retrieves a paginated list of client locations.
   *
   * @param clientNumber the client number for which to retrieve locations
   * @param page the page number (0-based) to retrieve
   * @param size the number of items per page
   * @return a {@link Flux} emitting a list of {@link ClientLocationDto} objects
   */
  public Flux<ClientLocationDto> listClientLocations(
      String clientNumber,
      Integer page,
      Integer size
  ) {
    return
        repository
            .findByClientNumber(clientNumber, PageRequest.of(page, size))
            .map(entity -> new ClientLocationDto(
                    entity.getClientNumber(),
                    entity.getLocationCode(),
                    entity.getLocationName(),
                    entity.getCompanyCode(),
                    entity.getAddress1(),
                    entity.getAddress2(),
                    entity.getAddress3(),
                    entity.getCity(),
                    entity.getProvince(),
                    entity.getPostalCode(),
                    entity.getCountry(),
                    entity.getBusinessPhone(),
                    entity.getHomePhone(),
                    entity.getCellPhone(),
                    entity.getFaxNumber(),
                    entity.getEmail(),
                    YesNoEnum.fromValue(entity.getExpired()),
                    YesNoEnum.fromValue(entity.getTrusted()),
                    Optional.ofNullable(entity.getReturnedMailDate()).map(
                        LocalDateTime::toLocalDate).orElse(null),
                    entity.getComment()
                )
            );
  }

  /**
   * Retrieves the details of a specific client location based on client number and location code.
   *
   * @param clientNumber the client number associated with the location
   * @param locationNumber the location code for the specific location
   * @return a {@link Mono} emitting a {@link ClientLocationDto} object containing the location details,
   *         or an error if the location is not found
   */
  public Mono<ClientLocationDto> getClientLocationDetails(String clientNumber,
      String locationNumber) {
    return repository
        .findByClientNumberAndLocationCode(clientNumber, locationNumber)
        .map(entity -> new ClientLocationDto(
                entity.getClientNumber(),
                entity.getLocationCode(),
                entity.getLocationName(),
                entity.getCompanyCode(),
                entity.getAddress1(),
                entity.getAddress2(),
                entity.getAddress3(),
                entity.getCity(),
                entity.getProvince(),
                entity.getPostalCode(),
                entity.getCountry(),
                entity.getBusinessPhone(),
                entity.getHomePhone(),
                entity.getCellPhone(),
                entity.getFaxNumber(),
                entity.getEmail(),
                YesNoEnum.fromValue(entity.getExpired()),
                YesNoEnum.fromValue(entity.getTrusted()),
                Optional.ofNullable(entity.getReturnedMailDate()).map(
                    LocalDateTime::toLocalDate).orElse(null),
                entity.getComment()
            )
        )
        .switchIfEmpty(Mono.error(new ClientNotFoundException("No client location found")));
  }
}
