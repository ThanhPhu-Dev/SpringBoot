package cf.dinhthanhphu.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "createdby")
	@CreatedBy
	private String createdBy;
	
	@Column(name = "createddate")
	@CreatedDate
	private Date createddate;
	
	@Column(name = "modifieddate")
	@LastModifiedDate
	private Date modifiedDate;
	
	@Column(name = "modifiedby")
	@LastModifiedBy
	private String modifiedBy;

	
}
