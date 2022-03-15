package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
@ComponentScan(basePackages = {"com.example.demo"})
public class DynamoDbConfiguration {
	
	@Value("${access_key}")
	private String access_key ;
	
	@Value("${secret_key}")
	private String secret_key;
	
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(buildAmazonDynamoDB());
	}
	
	
	private AmazonDynamoDB buildAmazonDynamoDB() {
		return AmazonDynamoDBClientBuilder
				.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration(
						"dynamodb.us-east-1.amazonaws.com",
						"us-east-1"
						)
					)
				.withCredentials(
						new AWSStaticCredentialsProvider(
								new BasicAWSCredentials(access_key,
										secret_key)
								))
				.build();
				
	}
	
}
