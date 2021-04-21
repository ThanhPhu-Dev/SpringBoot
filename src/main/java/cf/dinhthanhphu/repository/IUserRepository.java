package cf.dinhthanhphu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cf.dinhthanhphu.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByUserName(String username); 
	boolean existsByUserName(String userName);
	UserEntity findOneByResetPasswordToken(String token);
}
