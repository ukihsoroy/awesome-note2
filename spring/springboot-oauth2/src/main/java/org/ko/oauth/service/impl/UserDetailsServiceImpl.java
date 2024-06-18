package org.ko.oauth.service.impl;

import org.ko.oauth.domain.Authority;
import org.ko.oauth.domain.UserEntity;
import org.ko.oauth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Throwable.class)
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity= userService.findByName(username);
		String name = userEntity.getUsername();
		String password = userEntity.getPassword();
		List<Authority> authorities = new ArrayList<>();
		//授权
		authorities.add(new Authority(1L, "ROLE_ADMIN"));
		authorities.add(new Authority(2L, "ROLE_USER"));

		return new User(name, password, true, true, true, true, authorities);
	}

}