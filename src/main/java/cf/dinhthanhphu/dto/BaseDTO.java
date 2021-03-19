package cf.dinhthanhphu.dto;

import java.sql.Date;

public class BaseDTO {

	private Long id; 
	private String createdBy;
	private String createddate;
	private Date modifiedDate;
	private Date modifiedBy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Date getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Date modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
