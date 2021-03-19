package cf.dinhthanhphu.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cf.dinhthanhphu.dto.NewDTO;

public interface INewService {
	NewDTO saveOrUpdate(NewDTO dto);
	void delete(long[] ids);
	List<NewDTO> findAll(Pageable pagetable);
	int totalItem();
}
