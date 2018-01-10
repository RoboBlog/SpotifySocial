package pl.security

import org.springframework.security.core.userdetails.UsernameNotFoundException
import pl.security.service.JwtUserDetailsServiceImpl
import pl.user.User
import pl.user.UserRepository
import spock.lang.Specification


class JwtUserDetailsServiceTest extends Specification {
    def "loadUserByUsername with correct username return user details"(){
        given:
            def userRepository = Mock(UserRepository.class)
            def jwtUserDetailsService = new JwtUserDetailsServiceImpl(userRepository)

            userRepository.findByUsername("test") >> { String username -> Optional.of(new User("test", "test", "test@gmail.com")) }
        when:
            def userDetails = jwtUserDetailsService.loadUserByUsername("test")

        then:
            userDetails.getUsername() == "test"
    }

    def "loadUserByUsername with incorrect username return UsernameNotFoundException"(){
        given:
            def userRepository = Mock(UserRepository.class)
            def jwtUserDetailsService = new JwtUserDetailsServiceImpl(userRepository)

            userRepository.findByUsername("test") >> { String username -> Optional.of(new User("test", "test", "test@gmail.com")) }
            userRepository.findByUsername(_ as String) >> { String username -> Optional.ofNullable() }
        when:
            def userDetails = jwtUserDetailsService.loadUserByUsername("tes1t")

        then:
            thrown UsernameNotFoundException
    }
}
