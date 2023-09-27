package com.sendgrid.services.sendEmailService;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
@Service
public class EmailService {
    @Value("${spring.sendgrid.from-email}")
    private String fromEmail;
    @Value("${spring.sendgrid.text-email}")
    private String textEmail;
    @Value("${spring.sendgrid.subject-email}")
    private String subjectEmail;
    @Value("${spring.sendgrid.title-email}")
    private String titleEmail;
    @Autowired
    private SendGrid sendGrid;

    public void sendEmail(String email){
            Email from = new Email(fromEmail);
            Email to = new Email(email);
            Content content = new Content("text/plain", titleEmail + " " + "\n\n"+textEmail);
            Mail mail = new Mail(from, subjectEmail, to, content);
       Request request= new Request();
        try {
            request.setMethod(Method.POST);
            request.setBody(mail.build());
            request.setEndpoint("mail/send");
            Response response = sendGrid.api(request);
            if (response.getStatusCode()!=202){
                throw new RuntimeException("Err to send email");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

