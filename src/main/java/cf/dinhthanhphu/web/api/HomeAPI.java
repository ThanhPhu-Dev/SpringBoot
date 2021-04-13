package cf.dinhthanhphu.web.api;

import javax.validation.Valid;

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

import cf.dinhthanhphu.dto.CustomUserDetails;
import cf.dinhthanhphu.jwt.JwtTokenProvider;
import cf.dinhthanhphu.payload.LoginRequest;
import cf.dinhthanhphu.payload.LoginResponses;
import cf.dinhthanhphu.service.INewAccountService;

@RestController
@RequestMapping("/api")
public class HomeAPI {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
//	@Autowired
//	private INewAccountService newAccountService;

	@GetMapping(value = { "/", "/home" })
	public String homepage() {
		return "home";
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = null;
		String jwt = null;
		try {
			// Xác thực thông tin người dùng Request lên
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (Exception e) {
			return ResponseEntity.status(401).body(e.getMessage());
		}
		try {
			// Nếu không xảy ra exception tức là thông tin hợp lệ
			// Set thông tin authentication vào Security Context
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Trả về jwt cho người dùng.
			jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		} catch (Exception e) {
			System.err.println("lổi conetxt");
		}

		return ResponseEntity.ok(new LoginResponses(jwt));
	}
	
//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody SingupRequest singupRequest) {
//		if(newAccountService.existsByUsername(singupRequest.getUsername())) {
//			return ResponseEntity.status(401).body(new RandomStuff("Tài Khoản đã tồn tại"));
//		}
//		//create account
//		newAccountService.save(singupRequest.getUsername(), singupRequest.getPassword());
//		return ResponseEntity.ok(new RandomStuff("Đăng ký thành công"));
//	}
//
//	// Api /api/random yêu cầu phải xác thực mới có thể request
	@GetMapping("/random")
	public ResponseEntity<?> randomStuff() {
		return ResponseEntity.ok("JWT Hợp lệ mới có thể thấy được message này");
	}
//
//	@GetMapping("/logout-success")
//	public ResponseEntity<?> logout() {
//		return ResponseEntity.ok("Logout Thành công mới thấy dòng này");
//	}
}
