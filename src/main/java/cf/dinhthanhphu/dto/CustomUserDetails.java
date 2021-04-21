package cf.dinhthanhphu.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cf.dinhthanhphu.entity.RoleEntity;

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
