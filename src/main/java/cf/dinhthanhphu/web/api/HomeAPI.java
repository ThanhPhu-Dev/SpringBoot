package cf.dinhthanhphu.web.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
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

import com.sun.mail.handlers.message_rfc822;

import cf.dinhthanhphu.CustomerNotFoundException;
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
import cf.dinhthanhphu.service.IRessetPassword;
import cf.dinhthanhphu.utils.SendMailUtils;
import cf.dinhthanhphu.utils.URLUtils;

@RestController
@RequestMapping("/api")
public class HomeAPI {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private INewAccountService newAccountService;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private RoleConvert roleConvert;
	
	@Autowired
	private IRessetPassword resetPassword;
	
	@Autowired
	private SendMailUtils sendmail;
	

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
		roles.add(roleConvert.toDTO(roleRepository.findOne(2L)));
		newAccountService.save(singupRequest.getUsername(), singupRequest.getPassword(), singupRequest.getFullName(), singupRequest.getEmail(), roles);
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
	
	@PostMapping("/forgetpassword")
	public ResponseEntity<?> processForgetPassword(HttpServletRequest req){
		String username = req.getParameter("username");
		String token = RandomStringUtils.random(30,true,false);
		
		try {
			
			String email = resetPassword.updateResetPasswordToken(token, username);
			String resetPasswordLink = URLUtils.getSiteURL(req) + "/resetpassword?token"+token;
			sendmail.sendEmail(email, resetPasswordLink);
		} catch (CustomerNotFoundException | UnsupportedEncodingException | MessagingException e) {
			ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/resetpassword")
	public String processResetPassword(HttpServletRequest req){
		String token = req.getParameter("token");
		String password = req.getParameter("password");
		CustomUserDetails user = resetPassword.getByResetPasswordToken(token);
		if(user == null) {
			return "Không tồn tại";
		}else {
			resetPassword.updatePassword(user, password);
		}
		return "thành Công";
	}
}
