package cf.dinhthanhphu.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cf.dinhthanhphu.entity.RoleEntity;
import lombok.Data;

@Data
public class CustomUserDetails extends BaseDTO implements UserDetails {

	private static final long serialVersionUID = 3360998041653627481L;

	private String fullName;

	private String userName;

	private String password;
	
	private String email;

	private Integer status;
	
	private String resetPasswordToken;

	private List<RoleEntity> roles = new ArrayList<RoleEntity>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (RoleEntity auUs : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + auUs.getName()));
		}
		return authorities;
	}

	public CustomUserDetails() {
		super();
	}

	public CustomUserDetails(CustomUserDetails user) {
		BeanUtils.copyProperties(user, this);
	}

	public CustomUserDetails(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
