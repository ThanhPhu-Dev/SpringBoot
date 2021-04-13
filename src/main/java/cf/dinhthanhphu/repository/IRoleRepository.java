package cf.dinhthanhphu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cf.dinhthanhphu.entity.RoleEntity;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long>{
	List<RoleEntity> findAllById(Long i);
}
