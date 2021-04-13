package cf.dinhthanhphu.dto;

import java.util.ArrayList;
import java.util.List;

import cf.dinhthanhphu.entity.RoleEntity;

public class UserDTO {

	private String fullName;
	
	private String password;
	
	private Integer status;
	
	private List<RoleEntity> roles = new ArrayList<RoleEntity>();

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
}
