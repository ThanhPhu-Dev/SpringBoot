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

import cf.dinhthanhphu.CustomerNotFoundException;
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
	public CustomUserDetails save(String username, String password, String fullname,String email, List<RoleDTO> roles) {
		
		List<RoleEntity> lstrole = roles.stream().map(s -> roleRepository.findOne(s.getId())).collect(Collectors.toList());
		UserEntity user = new UserEntity(username, passwordEncoder.encode(password), fullname,email, 1, lstrole);
		return convert.toDTO(userRepository.save(user));
	}

	@Override
	public String updateResetPasswordToken(String token, String username) throws CustomerNotFoundException {
		UserEntity user = userRepository.findOneByUserName(username);
		if(user != null || user.getEmail() != null) {
			user.setResetPasswordToken(token);
			userRepository.save(user);
			return user.getEmail();
		}else {
			throw new CustomerNotFoundException("không thể tìm thấy " + user.getEmail());
		}
	}

	@Override
	public CustomUserDetails getByResetPasswordToken(String resetPasswordToken) {
		return convert.toDTO(userRepository.findOneByResetPasswordToken(resetPasswordToken));
	}

	@Override
	public void updatePassword(CustomUserDetails user, String newpassword) {
		UserEntity userentity = userRepository.findOneByUserName(user.getUsername());
		String encodedPasword = passwordEncoder.encode(newpassword);
		userentity.setPassword(encodedPasword);
		userentity.setResetPasswordToken(null);
		userRepository.save(userentity);
	}
}
