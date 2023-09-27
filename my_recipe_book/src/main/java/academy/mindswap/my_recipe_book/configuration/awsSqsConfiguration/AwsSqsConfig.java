package academy.mindswap.my_recipe_book.configuration.awsSqsConfiguration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSqsConfig {
    @Value("${app.config.aws.access_key_id}")
    private String awsAccessKey;
    @Value("${app.config.aws.secret_key_id}")
    private String awsSecretKey;
    @Value("${cloud.aws.sqs.endpoint}")
    private String endpoint;

    @Bean
   public QueueMessagingTemplate  queueMessagingTemplate(){
        return new QueueMessagingTemplate(amazonSQSAsync());
    }
    @Bean
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration( endpoint,"us-east-1"))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey,awsSecretKey)))
                .build();
    }
}
