package ca.bc.gov.api.oracle.legacy.service;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ClientPublicViewEntity;
import ca.bc.gov.api.oracle.legacy.entity.QClientPublicViewEntity;
import ca.bc.gov.api.oracle.legacy.exception.ClientNotFoundException;
import ca.bc.gov.api.oracle.legacy.exception.InvalidClientNumberException;
import ca.bc.gov.api.oracle.legacy.exception.NoSearchParameterFound;
import ca.bc.gov.api.oracle.legacy.repository.ClientPublicViewRepository;
import ca.bc.gov.api.oracle.legacy.util.ClientMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.hibernate.HibernateQuery;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

  private final ClientPublicViewRepository clientPublicViewRepository;

  public ClientPublicViewDto findByClientNumber(String clientNumber) {

    if (!StringUtils.isNumeric(clientNumber)) {
      throw new InvalidClientNumberException();
    }

    Optional<ClientPublicViewEntity> optionalClient =
        clientPublicViewRepository.findById(clientNumber);

    if (optionalClient.isEmpty()) {
      throw new ClientNotFoundException();
    }

    return ClientMapper.mapEntityToDto(optionalClient.get());
  }

  public List<ClientPublicViewDto> findAllNonIndividualClients(
      int page, int size, String sortBy) {

    List<ClientPublicViewEntity> entities = clientPublicViewRepository.findByClientTypeCodeNot(
        ClientPublicViewEntity.INDIVIDUAL,
        PageRequest.of(page, size, Sort.by(sortBy)));

    return entities
        .stream()
        .map(ClientMapper::mapEntityToDto)
        .toList();
  }

  public List<ClientPublicViewDto> searchByNames(
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
      throw new NoSearchParameterFound();
    }

    QClientPublicViewEntity clientEntity = QClientPublicViewEntity.clientPublicViewEntity;

    BooleanBuilder booleanBuilder = new BooleanBuilder();

    if (StringUtils.isNotBlank(clientName)) {
      booleanBuilder.and(clientEntity.clientName.containsIgnoreCase(clientName));
    }

    if (StringUtils.isNotBlank(clientFirstName)) {
      booleanBuilder.and(clientEntity.legalFirstName.containsIgnoreCase(clientFirstName));
    }

    if (StringUtils.isNotBlank(clientMiddleName)) {
      booleanBuilder.and(clientEntity.legalMiddleName.containsIgnoreCase(clientMiddleName));
    }

    if (!CollectionUtils.isEmpty(clientTypeCodes)) {
      booleanBuilder.and(clientEntity.clientTypeCode.in(clientTypeCodes));
    }
    
    return clientPublicViewRepository.findAll(booleanBuilder, PageRequest.of(page, size))
        .stream()
        .map(ClientMapper::mapEntityToDto)
        .toList();
  }
}
