package oryanmoshe.kafka.connect.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.apache.kafka.common.Configurable;
import org.apache.kafka.common.config.ConfigException;

import java.util.Map;

public class SimpleAWSCredentialsProvider implements AWSCredentialsProvider, Configurable {

    public static final String ACCESS_KEY_NAME = "access.key";
    public static final String SECRET_KEY_NAME = "secret.key";

    private AWSCredentials credentials;

    @Override
    public AWSCredentials getCredentials() {
        return credentials;
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Refresh is not supported for this credentials provider");
    }

    @Override
    public void configure(final Map<String, ?> configs) {
        validateConfigs(configs);

        final String accessKeyId = (String) configs.get(ACCESS_KEY_NAME);
        final String secretKey = (String) configs.get(SECRET_KEY_NAME);

        credentials = new BasicAWSCredentials(accessKeyId, secretKey);
    }

    private void validateConfigs(Map<String, ?> configs) {
        if (!configs.containsKey(ACCESS_KEY_NAME) || !configs.containsKey(SECRET_KEY_NAME)) {
            throw new ConfigException(String.format("%s and %s are mandatory configuration properties", ACCESS_KEY_NAME,
                    SECRET_KEY_NAME));
        }
    }
}