package br.com.kafka.service;

import br.com.kafka.entity.Venda;
import br.com.kafka.serializer.VendaSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

public class GeradorVendas {

    private static Random rand = new Random();
    private static long operacao = 0;
    private static BigDecimal valorIngresso = BigDecimal.valueOf(500);

    private static final String HOST_SERVER = "localhost:9092";
    private static final String TOPIC_NAME = "venda-ingressos";

    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST_SERVER);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VendaSerializer.class.getName());

        try(KafkaProducer<String, Venda> producer = new KafkaProducer<String,Venda>(properties)){
            while (true){
                Venda venda = gerarVenda();
                ProducerRecord<String,Venda> record = new ProducerRecord<String, Venda>(TOPIC_NAME, venda);
                producer.send(record);
                Thread.sleep(200);
            }
        }
    }

    private static Venda gerarVenda(){
        long cliente = rand.nextLong();
        int qtdeIngressos = rand.nextInt(10);

        final Venda venda = Venda.builder()
                .operacao(operacao++)
                .cliente(cliente)
                .qtdeIngressos(qtdeIngressos)
                .valorTotal(valorIngresso.multiply(BigDecimal.valueOf(qtdeIngressos)))
                .status("PROCESSING").build();

        return venda;
    }
}
