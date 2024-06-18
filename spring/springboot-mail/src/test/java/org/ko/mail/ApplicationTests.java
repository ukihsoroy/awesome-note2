package org.ko.mail;

import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${spring.mail.username}")
    private String from;

    @Test
    public void sendSimpleMail() throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(from);
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }

    @Test
    public void sendAttachmentsMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(from);
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("static/joker.jpg"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);

        mailSender.send(mimeMessage);
    }

    @Test
    public void sendInlineMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(from);
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:joker\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("static/joker.jpg"));
        helper.addInline("joker", file);

        mailSender.send(mimeMessage);
    }

    @Test
    public void sendTemplateMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(from);
        helper.setSubject("主题：模板邮件");

        Map<String, Object> model = new HashMap();
        model.put("name", "world");
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(text, true);
        mailSender.send(mimeMessage);
    }
}
