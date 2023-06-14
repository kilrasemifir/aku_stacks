package fr.kira.formation.kafka.stream.publisher;

import lombok.Data;

@Data
public class CalculDTO {
    private int a;
    private int b;

    @Override
    public String toString() {
        return "{\"a\":" + a + ", \"b\":" + b + '}';
    }
}
