package ca.bc.gov.api.oracle.legacy.service;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ClientPublicViewEntity;
import ca.bc.gov.api.oracle.legacy.exception.ClientNotFoundException;
import ca.bc.gov.api.oracle.legacy.exception.InvalidClientNumberException;
import ca.bc.gov.api.oracle.legacy.exception.NoSearchParameterFound;
import ca.bc.gov.api.oracle.legacy.repository.ClientPublicViewRepository;
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

  private final ClientPublicViewRepository clientPublicViewRepository;
  private final R2dbcEntityTemplate template;

  public Mono<ClientPublicViewDto> findByClientNumber(String clientNumber) {

    if (!StringUtils.isNumeric(clientNumber)) {
      return Mono.error(new InvalidClientNumberException());
    }

    return clientPublicViewRepository
        .findById(clientNumber)
        .switchIfEmpty(Mono.error(new ClientNotFoundException()))
        .map(ClientMapper::mapEntityToDto);
  }

  public Mono<List<ClientPublicViewDto>> findAllNonIndividualClients(
      int page, int size, String sortBy) {

    return clientPublicViewRepository
        .findByClientTypeCodeNot(
            ClientPublicViewEntity.INDIVIDUAL,
            PageRequest.of(page, size, Sort.by(sortBy))
        )
        .map(ClientMapper::mapEntityToDto)
        .collectList();
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
                ClientPublicViewEntity.class
            )
            .map(ClientMapper::mapEntityToDto);
  }
}
