package com.globallogic.demo.app.msusergenerator.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.demo.app.msusergenerator.domain.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.globallogic.demo.app.msusergenerator.security.Constants.HEADER_AUTHORIZACION_KEY;
import static com.globallogic.demo.app.msusergenerator.security.Constants.SUPER_SECRET_KEY;
import static com.globallogic.demo.app.msusergenerator.security.Constants.TOKEN_BEARER_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			User credenciales = new ObjectMapper().readValue(request.getInputStream(), User.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credenciales.getName(), credenciales.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		final Instant now = Instant.now();
		String token = Jwts.builder()
				.setSubject(((UserDetails)auth.getPrincipal()).getUsername())
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
				.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(SUPER_SECRET_KEY))
				.compact();
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
	}
}