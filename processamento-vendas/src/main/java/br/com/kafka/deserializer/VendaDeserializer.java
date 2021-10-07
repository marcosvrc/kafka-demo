package br.com.kafka.deserializer;

import br.com.kafka.entity.Venda;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

public class VendaDeserializer implements Deserializer<Venda> {

    @Override
    public Venda deserialize(String topic, byte[] venda) {
        try{
            return new ObjectMapper().readValue(venda, Venda.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close(){}
}
