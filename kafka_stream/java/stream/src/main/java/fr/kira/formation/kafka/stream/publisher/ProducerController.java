package fr.kira.formation.kafka.stream.publisher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class ProducerController {


    private final KafkaTemplate<String, String> template;

    public ProducerController(
            KafkaTemplate<String, String> publisherTemplate) {
        this.template = publisherTemplate;
    }

    @PostMapping
    public void publish(
            @RequestParam(value = "topic", defaultValue = "input") String topic,
            @RequestBody CalculDTO message) {
        this.template.send(topic, message.toString());
    }
}
