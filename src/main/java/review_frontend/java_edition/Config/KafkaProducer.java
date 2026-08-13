package review_frontend.java_edition.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String ,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void topicProducer(String topic , String body){

            kafkaTemplate.send(topic, body)
                    .whenComplete((result, ex) -> {

                        if (ex != null) {
                            System.out.println("KAFKA ERROR: " + ex.getMessage());
                        } else {
                            System.out.println(
                                    "KAFKA SUCCESS: topic=" +
                                            result.getRecordMetadata().topic() +
                                            ", partition=" +
                                            result.getRecordMetadata().partition() +
                                            ", offset=" +
                                            result.getRecordMetadata().offset()
                            );
                        }
                    });
    }

}