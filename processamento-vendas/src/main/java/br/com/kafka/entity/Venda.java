package br.com.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

    private Long operacao;
    private Long cliente;
    private Integer qtdeIngressos;
    private BigDecimal valorTotal;
    private String status;

}
