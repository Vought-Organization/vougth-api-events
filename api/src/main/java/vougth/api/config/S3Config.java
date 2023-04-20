package vougth.api.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${cloud.aws.credentials.access-key}")
    private String acessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.credentials.session-token}")
    private String tokenAWS;
    @Value("${cloud.aws.region.static}")
    private String region;


    @Bean
    public AmazonS3Client s3() {
        AWSCredentials awsCredentials = new AWSSessionCredentials() {
            @Override
            public String getSessionToken() {
                return tokenAWS;
            }

            @Override
            public String getAWSAccessKeyId() {
                return acessKey;
            }

            @Override
            public String getAWSSecretKey() {
                return secretKey;
            }
        };

        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

    }
}

