package cf.dinhthanhphu.constant;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadPath {
	
	public static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	public static final Path RESOURCE_PATH = Paths.get("resources");
	public static final Path IMAGE_PATH = Paths.get("images");

}
