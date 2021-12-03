package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	JavaMailSenderImpl mailSender;

	@Test
	void contextLoads() {
		//һ���򵥵��ʼ�
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject("third - Test from springboot to QQ");
		simpleMailMessage.setText("This is third test hello world, from spring boot - to QQ");
		simpleMailMessage.setTo("2431206033@qq.com");
		simpleMailMessage.setFrom("bingyang038@gmail.com");

		mailSender.send(simpleMailMessage);
	}

	@Test
	void contextLoads2() throws MessagingException {
		//һ�����ӵ��ʼ�
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		//��װ
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

		//����
		helper.setSubject("��������");
		helper.setText("<p style='color:red'>����html�ĸ�ʽ����</p>",true);

		//����
		helper.addAttachment("1.jpg",new File("C:\\Users\\error\\Desktop\\1.jpg"));

		helper.setTo("2431206033@qq.com");
		helper.setFrom("bingyang038@gmail.com");
		mailSender.send(mimeMessage);
	}
}
