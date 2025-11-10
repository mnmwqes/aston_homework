package com.example.notification;

import com.example.notification.kafka.UserEvent;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import javax.mail.internet.MimeMessage;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "spring.kafka.consumer.auto-offset-reset=earliest",
        "spring.kafka.listener.missing-topics-fatal=false"
})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NotificationIntegrationTest {

    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private GreenMail greenMail;

    @BeforeAll
    static void startKafka() {
        kafka.start();
        System.setProperty("spring.kafka.bootstrap-servers", kafka.getBootstrapServers());
    }

    @BeforeEach
    void setUp() {
        greenMail = new GreenMail(ServerSetupTest.SMTP);
        greenMail.start();
        System.setProperty("spring.mail.host", "localhost");
        System.setProperty("spring.mail.port", Integer.toString(greenMail.getSmtp().getPort()));
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @AfterAll
    static void stopKafka() {
        kafka.stop();
    }

    @Test
    void whenUserCreated_thenEmailSent() throws Exception {
        UserEvent event = new UserEvent(UserEvent.Operation.CREATED, "test@example.com");
        kafkaTemplate.send("user-events", event.getEmail(), event);
        kafkaTemplate.flush();
        Thread.sleep(1000);
        MimeMessage[] messages = greenMail.getReceivedMessages();
        assertThat(messages).hasSize(1);
        assertThat(messages[0].getSubject()).isEqualTo("Аккаунт создан");
        String body = (String) messages[0].getContent();
        assertThat(body).contains("Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан.");
    }

    @Test
    void whenUserDeleted_thenEmailSent() throws Exception {
        UserEvent event = new UserEvent(UserEvent.Operation.DELETED, "test2@example.com");
        kafkaTemplate.send("user-events", event.getEmail(), event);
        kafkaTemplate.flush();
        Thread.sleep(1000);
        MimeMessage[] messages = greenMail.getReceivedMessages();
        assertThat(messages).hasSize(1);
        assertThat(messages[0].getSubject()).isEqualTo("Аккаунт удалён");
    }
}
