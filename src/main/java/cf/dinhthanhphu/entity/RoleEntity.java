package cf.dinhthanhphu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{

	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	private List<UserEntity> users = new ArrayList<UserEntity>();
	
}
