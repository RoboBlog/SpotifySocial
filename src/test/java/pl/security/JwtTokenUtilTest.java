package pl.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JwtTokenUtilTest {

    @Mock
    private TimeProvider timeProviderMock;

    private static final String TEST_USERNAME = "testUser";

    @InjectMocks
    private JwtTokenUtil jwtTokenUtil;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L); // one hour
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret");
    }

    @Test
    public void generateToken_DifferentCreationDates_DifferentTokens() throws Exception {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.yesterday())
                .thenReturn(DateUtil.now());

        final String token = createToken();
        final String laterToken = createToken();

        assertThat(token).isNotEqualTo(laterToken);
    }

    @Test
    public void getUsernameFromToken_Token_Username() throws Exception {
        when(timeProviderMock.now()).thenReturn(DateUtil.now());

        final String token = createToken();

        assertThat(jwtTokenUtil.getUsernameFromToken(token)).isEqualTo(TEST_USERNAME);
    }

    @Test
    public void getIssuedAtDateFromToken_Token_IssuedDate() throws Exception {
        final Date now = DateUtil.now();
        when(timeProviderMock.now()).thenReturn(now);

        final String token = createToken();

        assertThat(jwtTokenUtil.getIssuedAtDateFromToken(token)).isInSameMinuteWindowAs(now);
    }

    @Test
    public void getExpirationDate_Token_ExpirationDate() throws Exception {
        final Date now = DateUtil.now();
        when(timeProviderMock.now()).thenReturn(now);
        final String token = createToken();

        final Date expirationDateFromToken = jwtTokenUtil.getExpirationDateFromToken(token);
        assertThat(DateUtil.timeDifference(expirationDateFromToken, now)).isCloseTo(3600000L, within(1000L));
    }

    @Test
    public void getAudienceFromToken_Token_Audioence() throws Exception {
        when(timeProviderMock.now()).thenReturn(DateUtil.now());
        final String token = createToken();

        assertThat(jwtTokenUtil.getAudienceFromToken(token)).isEqualTo(JwtTokenUtil.AUDIENCE_WEB);
    }

    @Test(expected = ExpiredJwtException.class)
    public void canTokenBeRefreshed_Token_ExpiredJwtException() throws Exception {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.yesterday());
        String token = createToken();
        jwtTokenUtil.canTokenBeRefreshed(token, DateUtil.tomorrow());
    }

    @Test
    public void canTokenBeRefreshed_ChangedPassword_CannotRefreshed() throws Exception {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.now());
        String token = createToken();
        assertThat(jwtTokenUtil.canTokenBeRefreshed(token, DateUtil.tomorrow())).isFalse();
    }

    @Test
    public void canTokenBeRefreshed_NotExpiredToken_RefreshToken() {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.now());
        String token = createToken();
        assertThat(jwtTokenUtil.canTokenBeRefreshed(token, DateUtil.yesterday())).isTrue();
    }

    @Test
    public void canRefreshToken() throws Exception {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.now())
                .thenReturn(DateUtil.tomorrow());
        String firstToken = createToken();
        String refreshedToken = jwtTokenUtil.refreshToken(firstToken);
        Date firstTokenDate = jwtTokenUtil.getIssuedAtDateFromToken(firstToken);
        Date refreshedTokenDate = jwtTokenUtil.getIssuedAtDateFromToken(refreshedToken);
        assertThat(firstTokenDate).isBefore(refreshedTokenDate);
    }

    @Test
    public void validateToken_CorrectToken_Validate() throws Exception {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.now());
        UserDetails userDetails = mock(JwtUser.class);
        when(userDetails.getUsername()).thenReturn(TEST_USERNAME);

        String token = createToken();
        assertThat(jwtTokenUtil.validateToken(token, userDetails)).isTrue();
    }

    private String createToken() {
        return jwtTokenUtil.generateToken(new UserDetailsDummy(TEST_USERNAME));
    }

}