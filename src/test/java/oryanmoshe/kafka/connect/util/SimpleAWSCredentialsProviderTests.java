package oryanmoshe.kafka.connect.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.amazonaws.auth.AWSCredentials;

public class SimpleAWSCredentialsProviderTests {
    @ParameterizedTest
    @CsvSource({ "AKIAJWONYEXAMPLEIRAA, 8wpEXAMPLE3p18VjOUUw2Fz9FH" })
    void createCreds(final String accessKey, final String secretKey) {
        final SimpleAWSCredentialsProvider credsProvider = new SimpleAWSCredentialsProvider();
        final Map<String, String> config = new HashMap<String, String>() {
            {
                put("access.key", accessKey);
                put("secret.key", secretKey);
            }
        };
        final AWSCredentials beforeConfig = credsProvider.getCredentials();
        System.out.println(beforeConfig);
        assertEquals(null, beforeConfig, null + " should equal " + beforeConfig);

        credsProvider.configure(config);
        final AWSCredentials actualResult = credsProvider.getCredentials();
        System.out.println(actualResult);

        assertEquals(accessKey, actualResult.getAWSAccessKeyId(),
                accessKey + " should equal " + actualResult.getAWSAccessKeyId());
        assertEquals(secretKey, actualResult.getAWSSecretKey(),
                secretKey + " should equal " + actualResult.getAWSSecretKey());
    }
}