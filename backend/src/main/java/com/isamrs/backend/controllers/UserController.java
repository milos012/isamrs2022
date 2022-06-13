package com.isamrs.backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// import com.isamrs.backend.dto.LoginDTO;
// import com.isamrs.backend.dto.LoginUserDTO;
// import com.isamrs.backend.dto.UpdateProfileDTO;
// import com.isamrs.backend.dto.UserDTO;
// import com.isamrs.backend.model.User;
// import com.isamrs.backend.security.TokenUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.isamrs.backend.DTO.UserDTO;
import com.isamrs.backend.models.User;
import com.isamrs.backend.repositories.UserRepository;
import com.isamrs.backend.services.UserService;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//     @Autowired
//     private UserDetailsService userDetailsService;

//     @Autowired
//     private AuthenticationManager authenticationManager;


//     private TokenUtils tokenUtils;

//     @Autowired
//     private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());


//     @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<String> addUser(@RequestBody UserDTO user) {
//         try {

//             if (userService.getUser(user.getEmail()) != null) {
//                 return new ResponseEntity<String>(HttpStatus.CONFLICT);
//             }

//             user.setRoleType("PATIENT");

//             userService.saveUser(userService.UserDTOToUser(user));
//             return new ResponseEntity<String>(HttpStatus.CREATED);
//         } catch (Exception ex) {

//             return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

//         }

//     }

//     @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<LoginUserDTO> login(@RequestBody LoginDTO log) {
//         logger.info(">> login: email - " + log.getEmail() + " password - " + log.getPassword());

//         try {

//             UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(log.getEmail(),
//                     log.getPassword());
//             Authentication authentication = authenticationManager.authenticate(token);
//             SecurityContextHolder.getContext().setAuthentication(authentication);

//             HttpHeaders headers = new HttpHeaders();

//             // Reload user details so we can generate token
//             UserDetails details = userDetailsService.loadUserByUsername(log.getEmail());

//             String authToken = tokenUtils.generateToken(details);
//             headers.add("X-Auth-Token", authToken);
//             String userRoleType = "";
//             LoginUserDTO lu = new LoginUserDTO();
//             User user = userService.getUser(log.getEmail());
//             logger.info(user.getEmail() + ' ' + user.getPassword());
//             if (user != null) {
//                 userRoleType = user.getRoleType().name();
//                 logger.info(userRoleType);
//                 lu.setRoleType(userRoleType);
//                 lu.setToken(authToken);
//             } else {
//                 logger.info("user does not exist");
//                 return new ResponseEntity<LoginUserDTO>(lu, HttpStatus.BAD_REQUEST);
//             }

//             logger.info("<< logged");
//             return new ResponseEntity<LoginUserDTO>(lu, headers, HttpStatus.OK);

//         } catch (Exception ex) {
//             logger.info("invalid login");
//             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//         }
//     }

//     @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json",produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<String> update(@RequestBody UpdateProfileDTO profileDTO) {

//         System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//         User user = userService.UpdateDTOtoUser(profileDTO);
//         if (user == null) {
//             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//         }

//         boolean updated = userService.update(user);

//         if (updated == true) {
//             return new ResponseEntity<String>("OK", HttpStatus.OK);

//         }

//         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

//     }
    
    @RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUsers(){

        List<User> users = userService.getAllUsers();
        //convert to DTO
        List<UserDTO> uDTOs = new ArrayList<>();
        for (User u : users) {
            uDTOs.add(new UserDTO(u));
        }

        return new ResponseEntity<>(uDTOs, HttpStatus.OK);

    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
		
		Optional<User> user = userService.findUserById(id);
		
		if (user != null) {
			return new ResponseEntity<>(new UserDTO(user.get()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value="/{email}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
		
		User user = userService.findUserByEmail(email);
		
		if (user != null) {
			return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value="/changepw/{id}", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UserDTO> changeUserPassword(@PathVariable Long id, String pw){
		
		Optional<User> foundUser = userService.findUserById(id);
		if (foundUser.get() != null) {
			foundUser.get().setPassword(pw);
			userService.saveUser(foundUser.get());
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) throws Exception {
		User user = new User();
		user.setFname(userDTO.getFname());
		user.setLname(userDTO.getLname());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
		user.setAuthority(userDTO.getAuthority());

        user.setCottages(userDTO.getCottages());
		user.setBoats(userDTO.getBoats());
		user.setInstructorOffers(userDTO.getInstructorOffers());


    	userService.saveUser(user);
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
	}


}