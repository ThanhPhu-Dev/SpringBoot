package cf.dinhthanhphu.dto;

import java.util.ArrayList;
import java.util.List;

import cf.dinhthanhphu.entity.RoleEntity;

public class UserDTO {

	private String fullName;
	
	private String password;
	
	private Integer status;
	
	private List<RoleEntity> roles = new ArrayList<RoleEntity>();

	
}
