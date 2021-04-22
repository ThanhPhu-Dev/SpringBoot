package cf.dinhthanhphu.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "createddate")
	private String createddate;
	
	@Column(name = "modifieddate")
	private Date modifiedDate;
	
	@Column(name = "modifiedby")
	private Date modifiedBy;

	
}
