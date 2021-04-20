package cf.dinhthanhphu.payload;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class SingupRequest {

	@NotBlank
	private String username;

	@NotBlank
	private String password;
	
	@NotBlank
	private String fullName;
}
