package cf.dinhthanhphu.payload;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserAvatarForm {

	private String username;
	private MultipartFile avatar;
}
