package com.ensah.core.web.controllers;

import com.ensah.core.services.Impl.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {

        LOG.debug("Token requested for user: '{}'", authentication.getName());
        System.out.println("Token requested for user: " + authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication) {

        return authentication.getName();
    }

//    @PostMapping("/signin")
//    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtil.generateJwtToken(authentication);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//        JwtResponse res = new JwtResponse();
//        res.setToken(jwt);
//        res.setId(userDetails.getId());
//        res.setUsername(userDetails.getUsername());
//        res.setRoles(roles);
//        return ResponseEntity.ok(res);
//    }

}
