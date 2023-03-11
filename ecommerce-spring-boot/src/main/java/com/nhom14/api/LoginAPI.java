package com.nhom14.api;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.LoginRequestDTO;
import com.nhom14.dto.MessageDTO;
import com.nhom14.dto.UserDTO;
import com.nhom14.entity.CustomUserDetails;
import com.nhom14.security.token.JwtTokenProvider;
import com.nhom14.service.UserService;

@RestController
@RequestMapping
public class LoginAPI {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserService userService;

	@PostMapping("/api/login")
	@CrossOriginsList
	public UserDTO login(@RequestBody LoginRequestDTO loginRequest) {
		System.out.println("loginRequest: "+loginRequest);
		System.out.println("KHÔNG LỖI NÈ");
		// Xác thực từ username và password.
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		// Nếu không xảy ra exception tức là thông tin hợp lệ
		System.out.println("KHÔNG LỖI NÈ");
		
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		UserDTO userDTO = userService
				.findOneById(((CustomUserDetails) authentication.getPrincipal()).getUser().getId());
		System.out.println("Bearer " + jwt);
		userDTO.setJwt("Bearer " + jwt);
		return userDTO;
	}

	@GetMapping("/api/user")
	@CrossOriginsList
	public ResponseEntity<?> getUserFromJWT() {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		System.out.println("id " + userId);
		UserDTO userDTO = userService.findOneById(userId);

		System.out.println("/api/user userDTO " + userDTO);

		return ResponseEntity.status(200).body(userDTO);
	}

	@PostMapping("/api/sign-up")
	@CrossOriginsList
	public UserDTO signUp(@RequestBody UserDTO userDTO) {

		System.out.println(userDTO);
		return userService.signUp(userDTO);
	}

	@PostMapping("/api/logout")
	@CrossOriginsList
	public MessageDTO logout() throws ServletException {
		SecurityContextHolder.clearContext();
		return new MessageDTO("Logout successfully!");
	}
	
}
