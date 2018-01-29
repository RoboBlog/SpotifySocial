package pl.social.facebook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ProviderSignInAttempt;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;
import pl.security.JwtTokenUtil;
import pl.security.service.JwtAuthenticationResponse;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class FacebookSignInAdapter implements SignInAdapter {

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;
    @Value("${jwt.header}")
    private String tokenHeader;

    public FacebookSignInAdapter(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    //todo fix it and override class and get role
    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        log.info("User {} sign in by facebook", localUserId);

        SecurityContextHolder.getContext().setAuthentication
                (new UsernamePasswordAuthenticationToken(connection.getDisplayName(), null, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(localUserId);
        final String token = jwtTokenUtil.generateToken(userDetails);

        return request.getContextPath() + "/token/" + token  ;
    }
}