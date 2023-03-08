package it.epicode.BW2.common.security.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.epicode.BW2.model.BeServiceUser;
import lombok.Data;

@Data
public class BeServiceUserDetailsImpl implements UserDetails{
	
	private Long id;
	private String username;
	private String email;
	@JsonIgnore
	private String password;
	private boolean accountNonLocked = true;
	private boolean accountNonExpired = false;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	private Date expirationTime;

	private Collection<? extends GrantedAuthority> authorities;

	public BeServiceUserDetailsImpl(Long i, String username, String email, String password, boolean enabled,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = i;
		this.username = username;
		this.email = email;
		this.password = password;
		this.accountNonLocked = enabled;
		this.accountNonExpired = enabled;
		this.credentialsNonExpired = enabled;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	public static BeServiceUserDetailsImpl build(BeServiceUser user) {
		List<GrantedAuthority> authorities = user.getBeServiceRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
		return new BeServiceUserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
				true, authorities);
	}
}
