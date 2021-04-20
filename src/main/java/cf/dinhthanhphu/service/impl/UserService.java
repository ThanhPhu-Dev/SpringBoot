package cf.dinhthanhphu.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cf.dinhthanhphu.convert.RoleConvert;
import cf.dinhthanhphu.convert.UserConvert;
import cf.dinhthanhphu.dto.CustomUserDetails;
import cf.dinhthanhphu.dto.RoleDTO;
import cf.dinhthanhphu.entity.RoleEntity;
import cf.dinhthanhphu.entity.UserEntity;
import cf.dinhthanhphu.repository.IRoleRepository;
import cf.dinhthanhphu.repository.IUserRepository;
import cf.dinhthanhphu.service.INewAccountService;
import cf.dinhthanhphu.service.IRessetPassword;

@Service
public class UserService implements UserDetailsService, INewAccountService, IRessetPassword {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserConvert convert;
	
	@Autowired
	private RoleConvert RoleConvert;
	
	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Kiểm tra xem user có tồn tại trong database không?
		UserEntity user = userRepository.findOneByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(convert.toDTO(user));
	}

	// JWTAuthenticationFilter sẽ sử dụng hàm này
	// kiểm tra tồn tại user không thông qua id
	@Transactional
	public UserDetails loadUserById(Long id) {

		UserEntity user = Optional.ofNullable(userRepository.findOne(id))
				.orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
		return new CustomUserDetails(convert.toDTO(user));
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUserName(username);
	}

	@Transactional
	@Override
	public CustomUserDetails save(String username, String password, String fullname, List<RoleDTO> roles) {
		
		List<RoleEntity> lstrole = roles.stream().map(s -> roleRepository.findOne(s.getId())).collect(Collectors.toList());
		UserEntity user = new UserEntity(username, passwordEncoder.encode(password), fullname, 1);
		user.setRoles(lstrole);
		return convert.toDTO(userRepository.save(user));
	}

	@Override
	public void updateResetPasswordToken(String token, String email) {
		
	}

	@Override
	public CustomUserDetails getByResetPasswordToken(String resetPasswordToken) {
		return null;
	}

	@Override
	public void updatePassword(CustomUserDetails user, String newpassword) {
		
	}
	
	

}
