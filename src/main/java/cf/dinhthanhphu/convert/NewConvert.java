package cf.dinhthanhphu.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cf.dinhthanhphu.dto.NewDTO;
import cf.dinhthanhphu.entity.NewEntity;
import cf.dinhthanhphu.repository.ICategoryRepository;

@Component
public class NewConvert {
	
	@Autowired
	private ICategoryRepository categoryRepository;

	public NewEntity convertToEntity(NewDTO dto)
	{
		NewEntity entity = new NewEntity();
		BeanUtils.copyProperties(dto, entity);
		entity.setCategory(categoryRepository.findOneByCode(dto.getCateagoryCode()));
		return entity;
	}
	
	public NewDTO convertToDTO(NewEntity entity)
	{
		NewDTO dto = new NewDTO();
		BeanUtils.copyProperties(entity, dto);
		dto.setCateagoryCode(entity.getCategory().getCode());
		return dto;
	}
	
	public NewEntity convertToEntity(NewDTO dto, NewEntity entity)
	{
		BeanUtils.copyProperties(dto, entity);
		entity.setCategory(categoryRepository.myfindByCode(dto.getCateagoryCode()));
		return entity;
	}
}
