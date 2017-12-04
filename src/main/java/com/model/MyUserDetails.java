package com.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails extends User implements UserDetails{

	private Collection<? extends GrantedAuthority> authorities;  
	
	 public MyUserDetails() {
		
	 }
	 
	 public MyUserDetails(User user,Collection<? extends GrantedAuthority> authorities) {
			this.setId(user.getId());
			this.setName(user.getName());
			this.setCertificate(user.getCertificate());
			this.setEmail(user.getEmail());
			this.setNameSpell(user.getNameSpell());
			this.setPhone(user.getPhone());
			this.setPassword(user.getPassword());
			this.setRole(user.getRole());
			this.setUserType(user.getUserType());
			this.setUserName(user.getUserName());
			this.authorities=authorities;
	 }
	
	
	
	@Override  
    public Collection<? extends GrantedAuthority> getAuthorities() {  
        return authorities;  
    }  

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
