package com.dbserver.lojaback.builder;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonFormat
public class Produto {
    private Integer id;
    private String descricao;
    private String nome;
    private Double precoUnitario;
    private Integer quantidade;

}

