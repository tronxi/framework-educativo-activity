package es.upm.frameworkeducativoactivity.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmazonS3Config {

    @Value("${aws-s3.access-key}")
    private String accessKey;
    @Value("${aws-s3.secret-key}")
    private String secretKey;

    @Bean
    AmazonS3 amazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_WEST_1)
                .build();
    }
}
