package com.nhom14.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nhom14.converter.UserConverter;
import com.nhom14.dto.UserDTO;
import com.nhom14.entity.CustomUserDetails;
import com.nhom14.entity.RoleEntity;
import com.nhom14.entity.UserEntity;
import com.nhom14.repository.RoleRepository;
import com.nhom14.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserConverter userConverter;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        UserEntity user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
    
    public UserDetails loadUserById(Long Id) {
        // Kiểm tra xem user có tồn tại trong database không?
        UserEntity user = userRepository.findOne(Id);
        if (user == null) {
            throw new UsernameNotFoundException("UserId: "+Id);
        }
        return new CustomUserDetails(user);
    }
    
    public UserDTO signUp(UserDTO userDTO) {
    	
    	UserEntity userEntity = userConverter.toEntity(userDTO);
    	
    	String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
    	userEntity.setPassword(encodedPassword);
    	
    	//Adding USER role code for register
    	List<RoleEntity> roles = new ArrayList<RoleEntity>();
    	roles.add(roleRepository.findOneByCode("USER"));
    	userEntity.setRoles(roles);
    	
    	//Make sure id of new user is empty
    	userEntity.setId(null);
    	
    	return userConverter.toDTO(userRepository.save(userEntity));
	}
    
    public boolean checkEmailsExisted(String email) {
    	UserEntity userEntity = userRepository.findOneByEmail(email);
    	if(userEntity != null) {
    		return true;
    	}
    	return false;
    }
    
    public UserDTO findOneById(Long id) {
    	return userConverter.toDTO(userRepository.findOne(id));
    }
    
}
