package cf.dinhthanhphu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private Integer status;

	@Column(name = "username")
	private String userName;
	
	@Column(name="avatar")
	private String avatar;

	@Column(name = "resetpasswordtoken", columnDefinition = "VARCHAR(45)")
	private String resetPasswordToken;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
	private List<RoleEntity> roles = new ArrayList<RoleEntity>();

	public UserEntity() {
		super();
	}

	public UserEntity(String userName, String password, String fullName,String email, Integer status, List<RoleEntity> lst) {
		this.fullName = fullName;
		this.password = password;
		this.userName = userName;
		this.status = status;
		this.email = email;
		this.roles = lst;
	}
}
