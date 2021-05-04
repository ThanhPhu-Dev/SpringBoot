package cf.dinhthanhphu;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
	
@SpringBootApplication
public class Application implements CommandLineRunner{

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
//	@Autowired
//	IUserRepository userRepository;
//	@Autowired	
//	PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	IRoleRepository roleRepository;

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
