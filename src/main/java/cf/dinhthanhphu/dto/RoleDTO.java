package cf.dinhthanhphu.dto;

import java.util.ArrayList;
import java.util.List;

import cf.dinhthanhphu.entity.UserEntity;
import lombok.Data;

@Data
public class RoleDTO extends BaseDTO{

	private String name;
	
	private String code;

	private List<UserEntity> users = new ArrayList<UserEntity>();
	
	
	
}
