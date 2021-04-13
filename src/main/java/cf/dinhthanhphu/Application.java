package cf.dinhthanhphu;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import cf.dinhthanhphu.entity.RoleEntity;
import cf.dinhthanhphu.entity.UserEntity;
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

	@Override
	public void run(String... args) throws Exception {
		// Khi chương trình chạy
		// Insert vào csdl một user.
//		List<RoleEntity> a = new ArrayList<>();
//		RoleEntity r =  new RoleEntity();
//		r.setName("USER");
//		r.setCode("2");
//		a.add(r);
//		UserEntity user = new UserEntity();
//		user.setUserName("thanhphu");
//		user.setPassword(passwordEncoder.encode("123"));
//		user.setRoles(a);
//		userRepository.saveAndFlush(user);
		//System.out.println(user);
	}
} 	
