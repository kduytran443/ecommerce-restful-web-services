package com.nhom14.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private UserEntity user;
	
	public CustomUserDetails() {}

    public CustomUserDetails(UserEntity user) {
		super();
		this.user = user;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Mặc định mình sẽ để tất cả là ROLE_USER. Để demo cho đơn giản.
		
		String roleCode = "USER";
		
		for (RoleEntity role : user.getRoles()) {
			if(role.getCode().toLowerCase().equals("ADMIN")) {
				roleCode = "ADMIN";
				break;
			}
		}
		
        return Collections.singleton(new SimpleGrantedAuthority(roleCode));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

