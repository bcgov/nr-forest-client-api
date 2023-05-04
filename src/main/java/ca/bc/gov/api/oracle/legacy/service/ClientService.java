package ca.bc.gov.api.oracle.legacy.service;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

import ca.bc.gov.api.oracle.legacy.ApplicationConstants;
import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.dto.ClientStatusCodeEnum;
import ca.bc.gov.api.oracle.legacy.dto.ClientTypeCodeEnum;
import ca.bc.gov.api.oracle.legacy.dto.ClientViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import ca.bc.gov.api.oracle.legacy.exception.ClientNotFoundException;
import ca.bc.gov.api.oracle.legacy.exception.InvalidClientNumberException;
import ca.bc.gov.api.oracle.legacy.exception.NoSearchParameterFound;
import ca.bc.gov.api.oracle.legacy.repository.ForestClientRepository;
import ca.bc.gov.api.oracle.legacy.util.ClientMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

  private final ForestClientRepository forestClientRepository;
  private final R2dbcEntityTemplate template;

  public Mono<ClientPublicViewDto> findByClientNumber(String clientNumber) {

    log.info("Searching for client with number {}", clientNumber);

    if (!StringUtils.isNumeric(clientNumber)) {
      return Mono.error(new InvalidClientNumberException());
    }

    return forestClientRepository
        .findById(clientNumber)
        .doOnNext(entity -> log.info("Found client with number {} as {}", clientNumber, entity))
        .switchIfEmpty(Mono.error(new ClientNotFoundException()))
        .map(ClientMapper::mapEntityToDto);
  }

  public Flux<ClientViewDto> findByClientNumberOrName(
      int page, int size, String clientNumberOrName) {

    log.info("Searching for client with number or name {}", clientNumberOrName);

    return forestClientRepository
        .findByClientNumberContainingOrClientNameContaining(
            clientNumberOrName,
            clientNumberOrName,
            PageRequest.of(page, size))
        .switchIfEmpty(Mono.error(new ClientNotFoundException()))
        .map(ClientMapper::mapEntityToClientViewDto)
        .map(clientViewDto -> {
          clientViewDto.setClientTypeCodeDescription(
              ClientTypeCodeEnum.valueOf(clientViewDto.getClientTypeCode()).getDescription());

          return clientViewDto.withClientStatusCodeDescription(
              ClientStatusCodeEnum.valueOf(clientViewDto.getClientStatusCode()).getDescription());
        });
  }

  public Flux<ClientPublicViewDto> findAllNonIndividualClients(
      int page, int size, String sortBy) {

    log.info("Searching all non individual clients on page {} with size {} sorting by {}", page,
        size, sortBy);

    return forestClientRepository
        .findByClientTypeCodeNot(
            ApplicationConstants.INDIVIDUAL,
            PageRequest.of(page, size, Sort.by(sortBy))
        )
        .map(ClientMapper::mapEntityToDto)
        .doOnNext(dto -> log.info("Found entry {}", dto));
  }


  public Flux<ClientPublicViewDto> searchByNames(
      String clientName,
      String clientFirstName,
      String clientMiddleName,
      List<String> clientTypeCodes,
      int page,
      int size) {

    if (StringUtils.isBlank(clientName)
        && StringUtils.isBlank(clientFirstName)
        && StringUtils.isBlank(clientMiddleName)
        && CollectionUtils.isEmpty(clientTypeCodes)) {
      return Flux.error(new NoSearchParameterFound());
    }

    Criteria queryCriteria = Criteria.empty();
    if (StringUtils.isNotBlank(clientName)) {
      queryCriteria = queryCriteria
          .and(where("clientName").is(clientName).ignoreCase(true));
    }

    if (StringUtils.isNotBlank(clientFirstName)) {
      queryCriteria = queryCriteria
          .and(where("legalFirstName").is(clientFirstName).ignoreCase(true));
    }

    if (StringUtils.isNotBlank(clientMiddleName)) {
      queryCriteria = queryCriteria
          .and(where("legalMiddleName").is(clientMiddleName).ignoreCase(true));
    }

    if (!CollectionUtils.isEmpty(clientTypeCodes)) {
      queryCriteria = queryCriteria
          .and(where("clientTypeCode").in(clientTypeCodes));
    }

    return
        template
            .select(
                query(queryCriteria)
                    .limit(size)
                    .offset((long) page * size),
                ForestClientEntity.class
            )
            .map(ClientMapper::mapEntityToDto)
            .doOnNext(dto -> log.info("Found entry {}", dto));
  }

  public Flux<ClientPublicViewDto> searchByAcronym(String acronym) {

    log.info("Searching for client by acronym {}", acronym);

    if (StringUtils.isBlank(acronym)) {
      return Flux.error(new NoSearchParameterFound("acronym"));
    }

    return forestClientRepository
        .findByClientAcronym(acronym)
        .doOnNext(entity -> log.info("Found entity with acronym {} with number {}", acronym,
            entity.getClientNumber()))
        .switchIfEmpty(
            Mono.error(new ClientNotFoundException("No client found with the acronym " + acronym))
        )
        .map(ClientMapper::mapEntityToDto);
  }

}
