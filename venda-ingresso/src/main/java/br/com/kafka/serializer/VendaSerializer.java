package br.com.kafka.serializer;

import br.com.kafka.entity.Venda;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class VendaSerializer implements Serializer<Venda> {


    public byte[] serialize(String topic, Venda venda) {
        try{
            return new ObjectMapper().writeValueAsBytes(venda);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close(){}
}
