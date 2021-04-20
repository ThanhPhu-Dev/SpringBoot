package cf.dinhthanhphu;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import cf.dinhthanhphu.entity.RoleEntity;
import cf.dinhthanhphu.entity.UserEntity;
import cf.dinhthanhphu.repository.IRoleRepository;
import cf.dinhthanhphu.repository.IUserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
	@Autowired
	IUserRepository userRepository;
	@Autowired	
	PasswordEncoder passwordEncoder;
	
	@Autowired
	IRoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		// Khi chương trình chạy
		// Insert vào csdl một user.
//		List<RoleEntity> a = roleRepository.findAllById(2L);
//		UserEntity user = new UserEntity();
//		user.setUserName("thanhphu3");
//		user.setPassword(passwordEncoder.encode("123"));
//		user.setRoles(a);
//		userRepository.save(user);
		//System.out.println(user);
	}
} 	
