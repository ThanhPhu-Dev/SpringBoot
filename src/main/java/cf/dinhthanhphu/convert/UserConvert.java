package cf.dinhthanhphu.convert;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import cf.dinhthanhphu.dto.CustomUserDetails;
import cf.dinhthanhphu.entity.RoleEntity;
import cf.dinhthanhphu.entity.UserEntity;

@Component
public class UserConvert {
	
	public CustomUserDetails toDTO(UserEntity entity) {
		CustomUserDetails result = new CustomUserDetails();
		BeanUtils.copyProperties(entity, result);
		result.setRoles(entity.getRoles().stream().map(s -> s.getName()).collect(Collectors.toList()));
		return result;
	}
	
	public UserEntity toEntity(CustomUserDetails dto) {
		UserEntity result = new UserEntity();
		BeanUtils.copyProperties(dto, result);
		return result;
	}
	
	public UserEntity toEntity(CustomUserDetails dto, UserEntity entity) {
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

}
