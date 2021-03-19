package cf.dinhthanhphu.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cf.dinhthanhphu.dto.NewDTO;
import cf.dinhthanhphu.paging.PagingGenerics;
import cf.dinhthanhphu.service.INewService;

@RestController
public class NewAPI {

	@Autowired
	private INewService newService;
	
	//required = false là không bắt buộc param tồn tại.
	@GetMapping(value ="/new")
	public PagingGenerics<NewDTO> createNew(@RequestParam(value = "page", defaultValue = "1") Integer page,
											@RequestParam(value = "limit", defaultValue = "100") Integer limit) {
		PagingGenerics<NewDTO> newOutput = new PagingGenerics<NewDTO>();
		if(page != null && limit != null)
		{
			newOutput.setPage(page);
			Pageable pageable = new PageRequest(page - 1, limit);
			newOutput.setListResult(newService.findAll(pageable));
			newOutput.setTotalPage((int) Math.ceil((double) (newService.totalItem() / limit)));
			
		}
		return newOutput;
	}
	
	@PostMapping(value ="/new")
	public NewDTO createNew(@RequestBody NewDTO model) {
		return newService.saveOrUpdate(model);
	}
	
	@PutMapping(value ="/new")
	public NewDTO updateNew(@RequestBody NewDTO model) {
		return newService.saveOrUpdate(model);
	}

	@DeleteMapping(value ="/new")
	public void deleteNew(@RequestBody long[] ids) {
		newService.delete(ids);
	}
}
