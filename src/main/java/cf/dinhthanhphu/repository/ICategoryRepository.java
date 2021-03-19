package cf.dinhthanhphu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cf.dinhthanhphu.entity.CategoryEntity;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByCode(String code);
	CategoryEntity findByCode(String code);
	
	@Query("select u from CategoryEntity u where u.code = ?1")
	CategoryEntity myfindByCode(String code);
	
}
