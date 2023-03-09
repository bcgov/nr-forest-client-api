package ca.bc.gov.api.oracle.legacy.service;

import ca.bc.gov.api.oracle.legacy.dto.ClientLocationDto;
import ca.bc.gov.api.oracle.legacy.dto.YesNoEnum;
import ca.bc.gov.api.oracle.legacy.repository.ClientLocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientLocationService {

  private final ClientLocationRepository repository;

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
                    entity.getReturnedMailDate(),
                    entity.getComment()
                )
            );
  }
}
