package ca.bc.gov.api.oracle.legacy.entity;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "FOREST_CLIENT", schema = "THE")
public class ForestClientEntity extends ForestClientBaseEntity {

	@Column("CLIENT_ID_TYPE_CODE")
	private String clientIdTypeCode;

	@Column("CLIENT_IDENTIFICATION ")
	private String clientIdentification;

	@Column("REGISTRY_COMPANY_TYPE_CODE")
	private String registryCompanyTypeCode;

	@Column("CORP_REGN_NMBR")
	private String corpRegnNmbr;

	@Column("CLIENT_ACRONYM")
	private String clientAcronym;

	@Column("WCB_FIRM_NUMBER")
	private String wcbFirmNumber;

	@Column("OCG_SUPPLIER_NMBR")
	private String ocgSupplierNmbr;

	@Column("CLIENT_COMMENT")
	private String clientComment;

	@Transient
	public String getName() {
		if (Objects.equals(this.clientTypeCode, "I")) {
			return Stream.of(this.legalFirstName, this.legalMiddleName, this.clientName).filter(Objects::nonNull)
					.collect(Collectors.joining(" "));
		} else {
			return this.clientName;
		}
	}

}
