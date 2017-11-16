package pl.security.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.security.JwtTokenUtil;
import pl.security.JwtUser;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "${origins}")
@RestController
public class UserRestController {


    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public UserRestController(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/user")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }

}
