package cf.dinhthanhphu.payload;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
