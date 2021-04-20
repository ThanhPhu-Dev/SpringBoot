package cf.dinhthanhphu.web.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import cf.dinhthanhphu.convert.RoleConvert;
import cf.dinhthanhphu.dto.CustomUserDetails;
import cf.dinhthanhphu.dto.RoleDTO;
import cf.dinhthanhphu.entity.RoleEntity;
import cf.dinhthanhphu.jwt.JwtTokenProvider;
import cf.dinhthanhphu.payload.LoginRequest;
import cf.dinhthanhphu.payload.LoginResponses;
import cf.dinhthanhphu.payload.SingupRequest;
import cf.dinhthanhphu.repository.IRoleRepository;
import cf.dinhthanhphu.service.INewAccountService;

@RestController
@RequestMapping("/api")
public class HomeAPI {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private INewAccountService newAccountService;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private RoleConvert roleConvert;
	
	
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
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SingupRequest singupRequest, HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(newAccountService.existsByUsername(singupRequest.getUsername())) {
			return ResponseEntity.status(401).body("Tài Khoản đã tồn tại");
		}
		//create account
		List<RoleDTO> roles = new ArrayList<>();
		roles.add(roleConvert.toDTO(roleRepository.findOne(1L)));
		newAccountService.save(singupRequest.getUsername(), singupRequest.getPassword(), singupRequest.getFullName(), roles);
		return ResponseEntity.ok("Đăng ký thành công");
	}

	// Api /api/random yêu cầu phải xác thực mới có thể request
	@GetMapping("/random")
	public ResponseEntity<?> randomStuff() {
		return ResponseEntity.ok("JWT Hợp lệ mới có thể thấy được message này");
	}

	@GetMapping("/logout-success")
	public ResponseEntity<?> logout() {
		return ResponseEntity.ok("Logout Thành công mới thấy dòng này");
	}
}
