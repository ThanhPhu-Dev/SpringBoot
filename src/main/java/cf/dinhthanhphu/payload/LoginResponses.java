package cf.dinhthanhphu.payload;

import lombok.Data;

@Data
public class LoginResponses {
	
	private String accessToken;
	private String tokenType = "Bearer";

	public LoginResponses(String accessToken) {
		this.accessToken = accessToken;
	}

}
