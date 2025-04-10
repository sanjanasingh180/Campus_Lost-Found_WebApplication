package com.lostandfound.entities;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails
{
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
	@Column(name = "password",nullable = false)
	@JsonIgnore
	private String password;
	
	@Column(name = "role",nullable = false)
	private String role;
	
	@Column(name = "name",nullable = false,unique = false)
	private String name;
	
	@Column(name = "phone",nullable = false,unique = false)
	private String phone;
	
	@Column(name="enrollmentid",nullable = false,unique = true)
	private String enrollmentId;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
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

	public User(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		
	}

	public User(String email, String password, String role, String name, String phone, String enrollmentId) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.name = name;
		this.phone = phone;
		this.enrollmentId = enrollmentId;
	}
	
}