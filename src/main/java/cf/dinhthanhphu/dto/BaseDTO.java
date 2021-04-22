package cf.dinhthanhphu.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BaseDTO {

	private Long id; 
	private String createdBy;
	private String createddate;
	private Date modifiedDate;
	private Date modifiedBy;
}
