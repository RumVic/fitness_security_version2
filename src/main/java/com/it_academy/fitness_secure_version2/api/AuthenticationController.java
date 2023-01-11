package com.it_academy.fitness_secure_version2.api;


import com.it_academy.fitness_secure_version2.dto.AuthenticationRequest;
import com.it_academy.fitness_secure_version2.filter.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService ;

    private final JwtUtil jwtUtil;
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        //anytime I try to authenticate my User I will delegate this whole
        //process to Spring. Spring will call UserDetailService so on so forth authenticate user;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        if(user != null ){
            return ResponseEntity.ok(jwtUtil.generateToken(user));

        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }

}
