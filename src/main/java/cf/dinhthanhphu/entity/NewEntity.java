package cf.dinhthanhphu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "news")
public class NewEntity extends BaseEntity{
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@Column(name = "shortdescription", columnDefinition = "TEXT")
	private String shortDescription;
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	//quan hệ này phải đi kèm với @onetomany
	//category_id là column bên bảng new dùng để nối đến khóa chính trong category.
	//tên biến category phải giống bên mapped bên categoryentity để biết new 1 - n với entity nào
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
    private CategoryEntity category;
	
}
