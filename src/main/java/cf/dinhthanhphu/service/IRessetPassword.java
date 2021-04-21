package cf.dinhthanhphu.service;

import cf.dinhthanhphu.CustomerNotFoundException;
import cf.dinhthanhphu.dto.CustomUserDetails;

public interface IRessetPassword {

	String updateResetPasswordToken(String token, String username) throws CustomerNotFoundException;
	CustomUserDetails getByResetPasswordToken(String resetPasswordToken);
	void updatePassword(CustomUserDetails user, String newpassword);
}
