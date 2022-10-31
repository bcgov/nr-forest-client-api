package ca.bc.gov.api.core.vo;

import java.util.Date;


public class CodeDescrVO implements java.io.Serializable {

	private static final long serialVersionUID = 4072698419357930180L;
	
	public String code;
	public String description;
	public Date effectiveDate;
	public Date expiryDate;
	public Long order;

//---

	public CodeDescrVO() {} 
	
	public CodeDescrVO(String code) {
		super();
		this.code = code;
	}

	public CodeDescrVO(String code, 
					   String description, 
					   String description2, 
					   Date effectiveDate, 
					   Date expiryDate) {
		
		super();
		this.code = code;
		this.description = description;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
	}

	public CodeDescrVO(String code, 
			   		   String description, 
			   		   String description2) {

		super();
		this.code = code;
		this.description = description;
	}
		
	public CodeDescrVO(String code, String description, String description2, Long order) {
		super();
		this.code = code;
		this.description = description;
		this.order = order;
	}
	
	public CodeDescrVO(String code, String description, String description2, String descriptiveText, String descriptiveText2) {
		super();
		this.code = code;
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodeDescrVO other = (CodeDescrVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CodeDescrVO [code=" + code + ", description=" + description + ", effectiveDate=" + effectiveDate
				+ ", expiryDate=" + expiryDate + ", order=" + order + "]";
	}

}
