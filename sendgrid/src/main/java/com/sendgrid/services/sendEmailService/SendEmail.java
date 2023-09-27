package com.sendgrid.services.sendEmailService;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class SendEmail {
    @Value("${app.config.message.queue.topic}")
    private String messageQueueTopic;

    private final AmazonSQS amazonSQSClient;
    @Autowired
    EmailService emailService;
    @Scheduled(fixedDelay = 5000)
    public void receiveMessages() {
        try {
            String queueUrl = amazonSQSClient.getQueueUrl(messageQueueTopic).getQueueUrl();
            ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);
            if (!receiveMessageResult.getMessages().isEmpty()) {
                Message message = receiveMessageResult.getMessages().get(0);
                log.info("Incoming Message From SQS {}", message.getMessageId());
                log.info("Message Body {}", message.getBody());
                processInvoice();
                emailService.sendEmail(message.getBody());
               amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
            }
        } catch (QueueDoesNotExistException e) {
            log.error("Queue does not exist {}", e.getMessage());
        }
    }
    private void processInvoice() {
        log.info("Processing invoice generation and sending invoice emails from here..");
    }
}

