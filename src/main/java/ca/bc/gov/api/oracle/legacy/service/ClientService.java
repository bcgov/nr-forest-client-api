package ca.bc.gov.api.oracle.legacy.service;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ClientPublicViewEntity;
import ca.bc.gov.api.oracle.legacy.entity.QClientPublicViewEntity;
import ca.bc.gov.api.oracle.legacy.exceptions.ClientNotFoundException;
import ca.bc.gov.api.oracle.legacy.exceptions.InvalidClientNumberException;
import ca.bc.gov.api.oracle.legacy.exceptions.NoSearchParameterFound;
import ca.bc.gov.api.oracle.legacy.repository.ClientPublicViewRepository;
import ca.bc.gov.api.oracle.legacy.util.ClientMapper;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
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

    return clientPublicViewRepository.findByClientTypeCodeNot(
            ClientPublicViewEntity.INDIVIDUAL,
            PageRequest.of(page, size, Sort.by(sortBy)))
        .stream()
        .map(ClientMapper::mapEntityToDto)
        .collect(Collectors.toList());
  }

  public List<ClientPublicViewDto> searchByNames(
      String clientName, String clientFirstName, String clientMiddleName,
      List<String> clientTypeCodes, int page, int size) {

    if (StringUtils.isBlank(clientName)
        && StringUtils.isBlank(clientFirstName)
        && StringUtils.isBlank(clientMiddleName)
        && CollectionUtils.isEmpty(clientTypeCodes)) {
      throw new NoSearchParameterFound();
    }

    QClientPublicViewEntity clientEntity = QClientPublicViewEntity.clientPublicViewEntity;

    BooleanExpression queryExpression = Expressions.asBoolean(true);

    if (StringUtils.isNotBlank(clientName)) {
      queryExpression =
          queryExpression.and(clientEntity.clientName.likeIgnoreCase(clientName));
    }

    if (StringUtils.isNotBlank(clientFirstName)) {
      queryExpression =
          queryExpression.and(clientEntity.legalFirstName.likeIgnoreCase(clientFirstName));
    }

    if (StringUtils.isNotBlank(clientMiddleName)) {
      queryExpression =
          queryExpression.and(clientEntity.legalMiddleName.likeIgnoreCase(clientMiddleName));
    }

    if (!CollectionUtils.isEmpty(clientTypeCodes)) {
      queryExpression =
          queryExpression.and(
              clientEntity.clientTypeCode.in(getClientTypeCodeQueryString(clientTypeCodes)));
    }

    return clientPublicViewRepository.findAll(queryExpression, PageRequest.of(page, size))
        .stream()
        .map(ClientMapper::mapEntityToDto)
        .collect(Collectors.toList());
  }

  private String getClientTypeCodeQueryString(List<String> clientTypeCodes) {
    StringBuilder stringBuilder = new StringBuilder();

    for (String code : clientTypeCodes) {
      if (stringBuilder.isEmpty()) {
        stringBuilder.append("'");
      } else {
        stringBuilder.append(", '");
      }
      stringBuilder.append(code);
      stringBuilder.append("'");
    }

    return stringBuilder.toString();
  }
}
