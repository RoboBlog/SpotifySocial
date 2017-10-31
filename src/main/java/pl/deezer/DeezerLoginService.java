package pl.deezer;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class DeezerLoginService {
    private final String deezerApiKey = System.getenv("DEEZER_API_KEY");
    private final String deezerAppId = System.getenv("DEEZER_APP_ID");
    private final String deezerRedirectUrl = System.getenv("DEEZER_REDIRECT_URL");

    public String getDeezerLoginUrl(){
        String url = "https://connect.deezer.com/oauth/auth.php?app_id="+deezerAppId+"&redirect_uri="+deezerRedirectUrl+"&perms=basic_access,email";
        return url;
    }


    public String getAccessToken(String code) throws IOException {
        String firstUrl = "https://connect.deezer.com/oauth/access_token.php?app_id="+deezerAppId+"&secret="+deezerApiKey+"&code="+code;
        URL url = new URL(firstUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();

        return response;

    }
}
