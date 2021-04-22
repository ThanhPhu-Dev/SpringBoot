package cf.dinhthanhphu.dto;

import lombok.Data;

@Data
public class NewDTO extends BaseDTO{

	private String title;
	private String thumbnail;
	private String shortDescription;
	private String cateagoryCode;
	private String content;

}
