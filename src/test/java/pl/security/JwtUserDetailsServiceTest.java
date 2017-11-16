package pl.security;

import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.security.service.JwtUserDetailsServiceImpl;
import pl.user.User;
import pl.user.UserRepository;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JwtUserDetailsServiceTest {

    @Test
    public void loadUserByUsername_CorrectUsername_UserDetails(){
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername("test")).thenReturn(new User("test", "test", "test@gmail.com"));
        JwtUserDetailsServiceImpl jwtUserDetailsService = new JwtUserDetailsServiceImpl(userRepository);

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername("test");

        assertThat(userDetails.getUsername()).isEqualTo("test");
    }


    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_IncorrectUsername_UserDetails(){
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername("test")).thenReturn(new User("test", "test", "test@gmail.com"));
        JwtUserDetailsServiceImpl jwtUserDetailsService = new JwtUserDetailsServiceImpl(userRepository);

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername("tes1t");

    }
}
