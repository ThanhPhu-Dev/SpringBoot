package cf.dinhthanhphu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cf.dinhthanhphu.convert.NewConvert;
import cf.dinhthanhphu.dto.NewDTO;
import cf.dinhthanhphu.entity.NewEntity;
import cf.dinhthanhphu.repository.INewRepository;
import cf.dinhthanhphu.service.INewService;

@Service
@Transactional
public class NewService implements INewService{

	@Autowired
	private INewRepository newRepository;
	 
	@Autowired
	private NewConvert convert;
	
	@Override
	public NewDTO saveOrUpdate(NewDTO dto) {
		NewEntity newEntity = new NewEntity();
		if(dto.getId() != null) {
			//khi lấy ra cái cũ ra
			NewEntity oldnew= newRepository.findOne(dto.getId());
			//gọi hàm covert để update thông tin mới vào nó.
			newEntity = convert.convertToEntity(dto, oldnew);
			
		}else{
			newEntity = convert.convertToEntity(dto);
		}
		return convert.convertToDTO(newRepository.save(newEntity));
	}


	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			newRepository.delete(id);
		}
		
	}


	@Override
	public List<NewDTO> findAll(Pageable pagetable) {
		List<NewEntity> entities = newRepository.findAll(pagetable).getContent();
		return entities.stream().map(i -> convert.convertToDTO(i)).collect(Collectors.toList());
	}


	@Override
	public int totalItem() {
		return (int) newRepository.count();
	}
	
	
}
