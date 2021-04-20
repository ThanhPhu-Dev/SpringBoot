package cf.dinhthanhphu.service;

import cf.dinhthanhphu.dto.CustomUserDetails;

public interface IRessetPassword {

	void updateResetPasswordToken(String token, String email);
	CustomUserDetails getByResetPasswordToken(String resetPasswordToken);
	void updatePassword(CustomUserDetails user, String newpassword);
}
