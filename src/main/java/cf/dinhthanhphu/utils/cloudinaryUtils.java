package cf.dinhthanhphu.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Component
public class cloudinaryUtils {
	private Cloudinary cloud;
	public Cloudinary getcloudinaryUtils() {
		return new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "imagetp",
				"api_key", "864783699686865",
				"api_secret", "V7anAlaMywEENEqxLq0774IEL0A"
				));
	}
	
	public cloudinaryUtils() {
		cloud = getcloudinaryUtils();
	}
	
	private Map upload(MultipartFile file , String folder) {
		Map uploadResult;
		try {
			uploadResult = cloud.uploader().upload(file.getBytes(),
			        ObjectUtils.asMap("resource_type", "auto",
			        				 "folder", folder == null ? "" : folder + "/"));
			return uploadResult;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,String> uploadImage(MultipartFile file){
		
		Map<String,String> result = new HashMap<String, String>();
		Map uploadResult = upload(file,"image");
		result.put("public_id", (String) uploadResult.get("public_id"));
		result.put("url", (String) uploadResult.get("url"));
		return result;
	}
}
