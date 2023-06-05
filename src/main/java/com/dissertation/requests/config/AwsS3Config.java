package com.dissertation.requests.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AwsS3Config {
    private static final String ACCESS_KEY = "AKIA3P3YSMNY4JBPMBOJ";
    private static final String SECRET_ACCESS_KEY = "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";
    private static final String REGION = "eu-west-1";
    private static final String ENDPOINT = "s3.eu-west-1.amazonaws.com";

    public AmazonS3 getS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY);
        return AmazonS3ClientBuilder.standard().withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT, REGION))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

}
