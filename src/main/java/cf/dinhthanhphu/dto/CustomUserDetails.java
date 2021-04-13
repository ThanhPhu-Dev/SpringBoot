package cf.dinhthanhphu.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import cf.dinhthanhphu.entity.RoleEntity;
import cf.dinhthanhphu.entity.UserEntity;

public class CustomUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 3360998041653627481L;
	
	UserEntity user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (RoleEntity auUs : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+ auUs.getName()));
		}
        return authorities;
	}
	
	public CustomUserDetails(UserEntity user) {
		this.user = user;
	}
	
	public CustomUserDetails(String userName, String password) {
		this.user.setUserName(userName);
		this.user.setPassword(password);
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
