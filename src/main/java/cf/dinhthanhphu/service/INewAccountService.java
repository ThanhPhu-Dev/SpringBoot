package cf.dinhthanhphu.service;

import java.util.List;

import cf.dinhthanhphu.dto.CustomUserDetails;
import cf.dinhthanhphu.dto.RoleDTO;

public interface INewAccountService {
	boolean existsByUsername(String username);
	CustomUserDetails save(String username, String password, String fullname, List<RoleDTO> role);
}
