package cf.dinhthanhphu.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cf.dinhthanhphu.dto.CustomUserDetails;
import cf.dinhthanhphu.entity.UserEntity;
import cf.dinhthanhphu.repository.IUserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Kiểm tra xem user có tồn tại trong database không?
		UserEntity user = userRepository.findOneByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(user);
	}

	// JWTAuthenticationFilter sẽ sử dụng hàm này
	// kiểm tra tồn tại user không thông qua id
	@Transactional
	public UserDetails loadUserById(Long id) {

		UserEntity user = Optional.ofNullable(userRepository.findOne(id))
				.orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
		return new CustomUserDetails(user);
	}

}
