package fr.kira.formation.kafka.publisher;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/publish")
    public void publish(
            @RequestParam(defaultValue = "0") String a,
            @RequestParam(defaultValue = "0") String b,
            @RequestParam(defaultValue = "demo") String topic
    ) {
        String json = "{\"a:"+a+",\"b\":"+b+"}";
        kafkaTemplate.send(topic, json);
    }
}
