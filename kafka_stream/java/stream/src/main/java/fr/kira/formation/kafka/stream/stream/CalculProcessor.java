package fr.kira.formation.kafka.stream.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.kira.formation.kafka.stream.publisher.CalculDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Slf4j
public class CalculProcessor {

    private final ObjectMapper mapper;

    public CalculProcessor(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void process(StreamsBuilder builder){
        KStream<String, String> messages = builder.stream("input");
        messages
                .mapValues(value->{
                    log.info("Nouveau message: " + value);
                    return value;
                })
                .mapValues(this::calculate)
                .to("output");

    }

    public String calculate(String message){
        try {
            CalculDTO calcul = mapper.readValue(message, CalculDTO.class);
            return "{\"a\":"+calcul.getA()
                    +", \"b\":"+calcul.getB()
                    +", \"result\":"+(calcul.getA()+calcul.getB())+"}";
        } catch (Exception e) {
            return "error";
        }
    }
}
