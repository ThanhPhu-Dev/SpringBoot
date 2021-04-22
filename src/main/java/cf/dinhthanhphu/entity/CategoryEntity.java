package cf.dinhthanhphu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{
	

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	//MapopedBy trỏ tới tên biến category ở trong news.
	//tên MapopedBy phải giống tên biến ở class NewEntity
	@OneToMany(mappedBy = "category")
	private List<NewEntity> news = new ArrayList<>();

}
