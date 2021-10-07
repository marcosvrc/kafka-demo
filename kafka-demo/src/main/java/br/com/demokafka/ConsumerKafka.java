package br.com.demokafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerKafka {

    private static final String HOST_SERVER = "localhost:9092";
    private static final String GROUP_NAME = "group1";
    private static final String TOPIC_NAME = "testejava";

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST_SERVER);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_NAME);
        //Configuração para que o consumer pegue as mensagens do começo.
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String>  consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList(TOPIC_NAME));

        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
        for(ConsumerRecord<String,String> record: records){
            System.out.println(record.value());
        }
        consumer.close();

    }

}
