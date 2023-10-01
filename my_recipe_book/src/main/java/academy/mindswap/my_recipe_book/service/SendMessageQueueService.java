package academy.mindswap.my_recipe_book.service;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SendMessageQueueService {

        @Value("${app.config.message.queue.topic}")
        private String messageQueueTopic;
        @Autowired
        AmazonSQS amazonSQSClient;

        public void createMessageQueue() {
            log.info("Creating message queue on AWS SQS");
            CreateQueueRequest request = new CreateQueueRequest();
            request.setQueueName(messageQueueTopic);
            try {
                CreateQueueResult queue = amazonSQSClient.createQueue(request);
                log.info("Create Queue Response {}", queue.getQueueUrl());
            } catch (QueueNameExistsException e) {
                log.error("Queue Name Exists {}", e.getErrorMessage());
            }
        }
        public void publishExpense(String email){
            createMessageQueue();
            try {
                GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(messageQueueTopic);
                log.info("Reading SQS Queue done: URL {}", queueUrl.getQueueUrl());
                amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), email);

                log.info(amazonSQSClient.listQueues().toString());

            }catch (QueueDoesNotExistException | InvalidMessageContentsException e){
                log.error("Queue does not exist {}", e.getMessage());
            }
        }
    }
