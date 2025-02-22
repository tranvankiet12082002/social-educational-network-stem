package com.trvanket.app.jwt.service.impl;

import com.trvanket.app.dto.CredentialDto;
import com.trvanket.app.jwt.service.JwtService;
import com.trvanket.app.jwt.util.JwtUtil;
import com.trvanket.app.service.client.UserClientService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
//
//	private final UserClientService userClientService;
	
	private final JwtUtil jwtUtil;
	
	@Override
	public String extractUserId(final String token) {
		log.info("JwtServiceImpl, String, extractCredentialId");
		return this.jwtUtil.extractUserId(token);
	}
	
	@Override
	public Date extractExpiration(final String token) {
		log.info("JwtServiceImpl, Date, extractExpiration");
		return this.jwtUtil.extractExpiration(token);
	}
	
	@Override
	public <T> T extractClaims(final String token, final Function<Claims, T> claimsResolver) {
		log.info("JwtServiceImpl, <T> T, extractClaims");
		return this.jwtUtil.extractClaims(token, claimsResolver);
	}

	@Override
	public Boolean validateToken(final String token) {
		log.info("JwtServiceImpl, Boolean, validateToken");
		return this.jwtUtil.validateToken(token);
	}

	@Override
	public String extractUserRole(String token) {
		return this.jwtUtil.extractUserRole(token);
	}

	boolean isValid(CredentialDto credentialDto) {
		return credentialDto.getIsEnabled() &&
				credentialDto.getIsAccountNonExpired() &&
				credentialDto.getIsAccountNonLocked() &&
				credentialDto.getIsCredentialsNonExpired();
	}

//	@Override
//	public boolean isValidUser(String accessToken) {
//		String userId = this.extractUserId(accessToken);
//		CredentialDto credentialDto = userClientService.getCredentialDto(userId);
//		return isValid(credentialDto);
//	}
//
//	@Override
//	public boolean isValidAdmin(String accessToken) {
//		String userId = this.extractUserId(accessToken);
//		CredentialDto credentialDto = userClientService.getCredentialDto(userId);
//		if (isValid(credentialDto)) {
//			return credentialDto.getRole().equals("ROLE_ADMIN");
//		}
//		return false;
//	}


}










