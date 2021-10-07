package br.com.kafka.entity;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class Venda {

    private Long operacao;
    private Long cliente;
    private Integer qtdeIngressos;
    private BigDecimal valorTotal;
    private String status;

}
