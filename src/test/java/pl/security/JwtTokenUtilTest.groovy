package pl.security

import io.jsonwebtoken.ExpiredJwtException
import org.assertj.core.util.DateUtil
import org.springframework.test.util.ReflectionTestUtils
import pl.util.TimeProvider
import spock.lang.Specification

class JwtTokenUtilTest extends Specification {

    private static final String TEST_USERNAME = "testUser"

    def "generateToken with differentCreationDates should return different tokens"() {
        given:
        def timeProvider = Mock(TimeProvider.class)
        timeProvider.now() >> DateUtil.yesterday() >> DateUtil.now()
        def jwtTokenUtil = new JwtTokenUtil(timeProvider: timeProvider)

        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L) // one hour
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret")

        when:
        def token = jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME))
        def laterToken = jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME))

        then:
        token != laterToken
    }


    def "getUsernameFromToken should return username"() {
        given:
        def timeProvider = Mock(TimeProvider.class)
        timeProvider.now() >> DateUtil.now()
        def jwtTokenUtil = new JwtTokenUtil(timeProvider: timeProvider)

        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L) // one hour
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret")

        when:
        def token = jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME))
        def username = jwtTokenUtil.getUsernameFromToken(token)

        then:
        username == TEST_USERNAME
    }

    def "getIssuedAtDateFromToken should return issued date"() {
        given:
        def timeProvider = Mock(TimeProvider.class)
        def now = DateUtil.now()
        timeProvider.now() >> now
        def jwtTokenUtil = new JwtTokenUtil(timeProvider: timeProvider)

        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L) // one hour
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret")

        when:
        def token = jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME))
        def issuedDate = jwtTokenUtil.getIssuedAtDateFromToken(token)

        then:
        issuedDate.toString() == now.toString()
    }

    def "getExpirationDate should return expiration date"() {
        given:
        def timeProvider = Mock(TimeProvider.class)
        def now = DateUtil.now()
        timeProvider.now() >> now
        def jwtTokenUtil = new JwtTokenUtil(timeProvider: timeProvider)

        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L) // one hour
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret")

        when:
        def token = jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME))
        def exprationDate = jwtTokenUtil.getExpirationDateFromToken(token)

        then:
        exprationDate > now
    }


    def "validateToken with correct token should return validate"() {
        given:
        def timeProvider = Mock(TimeProvider.class)
        timeProvider.now() >> DateUtil.now()
        def jwtTokenUtil = new JwtTokenUtil(timeProvider: timeProvider)
        def userDetails = Mock(JwtUser.class)
        userDetails.getUsername() >> TEST_USERNAME
        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L) // one hour
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret")

        when:
        def token = jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME))
        def validate = jwtTokenUtil.validateToken(token, userDetails)
        then:
        validate
    }

    def "canTokenBeRefreshed with expired token should return ExpiredJwtException"() {
        given:
        def timeProvider = Mock(TimeProvider.class)
        timeProvider.now() >> DateUtil.yesterday()
        def jwtTokenUtil = new JwtTokenUtil(timeProvider: timeProvider)

        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L) // one hour
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret")

        when:
        def token = jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME))
        def beRefreshed = jwtTokenUtil.canTokenBeRefreshed(token, DateUtil.tomorrow())
        then:
        thrown ExpiredJwtException
    }


    def "canTokenBeRefreshed with not expired token should refresh token"() {
        given:
        def timeProvider = Mock(TimeProvider.class)
        timeProvider.now() >> DateUtil.now()
        def jwtTokenUtil = new JwtTokenUtil(timeProvider: timeProvider)

        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L) // one hour
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret")

        when:
        def token = jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME))
        def beRefreshed = jwtTokenUtil.canTokenBeRefreshed(token, DateUtil.yesterday())
        then:
        beRefreshed
    }

    def "canTokenBeRefreshed with changed password should can not refresh token"() {
        given:
        def timeProvider = Mock(TimeProvider.class)
        timeProvider.now() >> DateUtil.now()
        def jwtTokenUtil = new JwtTokenUtil(timeProvider: timeProvider)

        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L) // one hour
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret")

        when:
        def token = jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME))
        def beRefreshed = jwtTokenUtil.canTokenBeRefreshed(token, DateUtil.tomorrow())
        then:
        !beRefreshed
    }

    def "canRefreshToken should refresh token"() {
        given:
        def timeProvider = Mock(TimeProvider.class)
        timeProvider.now() >> DateUtil.now() >> DateUtil.tomorrow()
        def jwtTokenUtil = new JwtTokenUtil(timeProvider: timeProvider)

        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L) // one hour
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret")

        when:
        def firstToken = jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME))
        def refreshedToken = jwtTokenUtil.refreshToken(firstToken)
        def firstTokenDate = jwtTokenUtil.getIssuedAtDateFromToken(firstToken)
        def refreshedTokenDate = jwtTokenUtil.getIssuedAtDateFromToken(refreshedToken)

        then:
        firstTokenDate < refreshedTokenDate
    }
}


