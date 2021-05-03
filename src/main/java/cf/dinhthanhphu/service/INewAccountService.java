package cf.dinhthanhphu.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cf.dinhthanhphu.CustomerNotFoundException;
import cf.dinhthanhphu.dto.CustomUserDetails;
import cf.dinhthanhphu.dto.RoleDTO;

public interface INewAccountService {
	boolean existsByUsername(String username);
	CustomUserDetails save(String username, String password, String fullname,String email,  List<RoleDTO> role);
	String updateResetPasswordToken(String token, String username) throws CustomerNotFoundException;
	CustomUserDetails getByResetPasswordToken(String resetPasswordToken);
	void updatePassword(CustomUserDetails user, String newpassword);
	CustomUserDetails updateAvatar(CustomUserDetails user, MultipartFile urlImage) throws IOException;
	CustomUserDetails findOneByUserName(String username);
}
