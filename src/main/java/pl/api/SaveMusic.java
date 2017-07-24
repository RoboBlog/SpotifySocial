package pl.api;

import com.google.common.io.Files;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;


/**
 * Created by maciek on 7/23/17.
 */
@Service
public class SaveMusic {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
    public void update(String tracks){
        String name = nextSessionId();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(name), "utf-8"))) {
            writer.write(tracks);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
