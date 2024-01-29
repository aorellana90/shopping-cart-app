package com.alexshopcart.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Value("${credenciales-api.usuario}")
	private String usuario;

	@Value("${credenciales-api.clave}")
	private String clave;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(clave);

		if (usuario.equals(username)) {
			return User.withUsername(usuario).password(encodedPassword).roles("USER").build();
		} else {
			throw new UsernameNotFoundException("No se encontro el usuario: " + username);
		}
	}

}
