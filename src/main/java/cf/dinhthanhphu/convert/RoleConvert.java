package cf.dinhthanhphu.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import cf.dinhthanhphu.dto.RoleDTO;
import cf.dinhthanhphu.entity.RoleEntity;

@Component
public class RoleConvert {

	public RoleDTO toDTO(RoleEntity entity) {
		RoleDTO result = new RoleDTO();
		BeanUtils.copyProperties(entity, result);
		return result;
	}
	
	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity result = new RoleEntity();
		BeanUtils.copyProperties(dto, result);
		return result;
	}
	
	public RoleEntity toEntity(RoleDTO dto, RoleEntity entity) {
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}
}
