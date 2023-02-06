package ca.bc.gov.api.oracle.legacy.service;

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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

  private final ClientPublicViewRepository clientPublicViewRepository;

  public Mono<ClientPublicViewDto> findByClientNumber(String clientNumber) {

    if (!StringUtils.isNumeric(clientNumber)) {
      return Mono.error(new InvalidClientNumberException());
    }

    return /*Mono.justOrEmpty(*/clientPublicViewRepository.findById(clientNumber)/*)*/
        .switchIfEmpty(Mono.error(new ClientNotFoundException()))
        .map(ClientMapper::mapEntityToDto);
  }

  public Mono<List<ClientPublicViewDto>> findAllNonIndividualClients(
      int page, int size, String sortBy) {

    return /*Flux.fromIterable(*/
            clientPublicViewRepository.findByClientTypeCodeNot(
                ClientPublicViewEntity.INDIVIDUAL,
                PageRequest.of(page, size, Sort.by(sortBy)))/*)*/
        .map(ClientMapper::mapEntityToDto)
        .collectList();
  }

  public Mono<List<ClientPublicViewDto>> searchByNames(
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
      return Mono.error(new NoSearchParameterFound());
    }

    ClientPublicViewEntity searchProbe = new ClientPublicViewEntity();

    if (StringUtils.isNotBlank(clientName)) {
      searchProbe = searchProbe.withClientName(clientName);
    }

    if (StringUtils.isNotBlank(clientFirstName)) {
      searchProbe = searchProbe.withLegalFirstName(clientFirstName);
    }

    if (StringUtils.isNotBlank(clientMiddleName)) {
      searchProbe = searchProbe.withLegalMiddleName(clientMiddleName);
    }

    if (!CollectionUtils.isEmpty(clientTypeCodes)) {
      //TODO Must investigate
    }

    ExampleMatcher searchMatcher = ExampleMatcher
        .matchingAll()
        .withMatcher("clientName",ExampleMatcher.GenericPropertyMatcher::ignoreCase)
        .withMatcher("legalFirstName",ExampleMatcher.GenericPropertyMatcher::ignoreCase)
        .withMatcher("legalMiddleName",ExampleMatcher.GenericPropertyMatcher::ignoreCase);

    return /*Flux.fromIterable(*/
            clientPublicViewRepository
                .findAll(Example.of(searchProbe, searchMatcher))
                //.findAll(booleanBuilder, PageRequest.of(page, size))/*)*/
        .map(ClientMapper::mapEntityToDto)
        .collectList();
  }
}
