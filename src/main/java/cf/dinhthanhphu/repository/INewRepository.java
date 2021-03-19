package cf.dinhthanhphu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cf.dinhthanhphu.entity.NewEntity;

public interface INewRepository extends JpaRepository<NewEntity, Long>{
}
