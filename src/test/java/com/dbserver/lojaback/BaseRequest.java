package com.dbserver.lojaback;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class BaseRequest {


    private BigDecimal preco;
    private Integer quantidade;
    private String nome;
    private String descricao;

    public BaseRequest(){}

    public JSONObject requestObject(String nome, String descricao, Integer quantidade, BigDecimal preco) {
        JSONObject request = new JSONObject();
        try {
            request.put("descricao", descricao);
            request.put("nome", nome);
            request.put("precoUnitario", preco);
            request.put("quantidade", quantidade);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return request;
    }

    public JSONObject requestObject(String descricao, Integer quantidade, BigDecimal preco)  {
        JSONObject request = new JSONObject();
        try {
            request.put("descricao", descricao);
            request.put("precoUnitario", preco);
            request.put("quantidade", quantidade);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return request;
    }

    public JSONObject requestObject(String nome, String descricao, Long quantidade, BigDecimal preco)  {
        JSONObject request = new JSONObject();
        try {
            request.put("descricao", descricao);
            request.put("nome", nome);
            request.put("precoUnitario", preco);
            request.put("quantidade", quantidade);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return request;
    }
}
