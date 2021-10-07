package br.com.kafka.service;

import br.com.kafka.deserializer.VendaDeserializer;
import br.com.kafka.entity.Venda;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;

public class ProcessadorVendas {

    private static final String HOST_SERVER = "localhost:9092";
    private static final String GROUP_NAME = "grupo-processamento";
    private static final String TOPIC_NAME = "venda-ingressos";

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST_SERVER);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, VendaDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_NAME);
        //Configuração para que o consumer pegue as mensagens do começo.
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"10");

        try(KafkaConsumer<String, Venda>  consumer = new KafkaConsumer<>(properties)){
            consumer.subscribe(Collections.singletonList(TOPIC_NAME));

            while(true){
                ConsumerRecords<String, Venda> records = consumer.poll(Duration.ofMillis(200));

                for(ConsumerRecord<String,Venda> record: records){
                   Venda venda = record.value();
                   System.out.println(venda);
                    Thread.sleep(200);
                }
            }
        }
    }
}
