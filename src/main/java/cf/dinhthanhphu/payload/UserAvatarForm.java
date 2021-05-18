package cf.dinhthanhphu.payload;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import com.cloudinary.StoredFile;
import com.cloudinary.*;

@Data
public class UserAvatarForm extends StoredFile{

	private String username;
	private MultipartFile[] avatar;
	
//	public String getUrl() {
//        if (version != null && format != null && publicId != null) {
//            return Singleton.getCloudinary().url()
//                    .resourceType(resourceType)
//                    .type(type)
//                    .format(format)
//                    .version(version)
//                    .generate(publicId);
//        } else return null;
//    }
//	
//	public String getComputedSignature() {
//        return getComputedSignature(Singleton.getCloudinary());
//    }
//
//    public boolean validSignature() {
//        return getComputedSignature().equals(signature);
//    }
}
