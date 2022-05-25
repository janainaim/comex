package br.com.alura.comex.service;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.*;

public class ProcessadorDeJson  extends ProcessadorAdapter {


    public ProcessadorDeJson(){}

    @Override
    public List<Pedido> listarPedidos(String nomeArquivo) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper
                    .readValue(ClassLoader.getSystemResource(nomeArquivo.concat(".json")), new TypeReference<List<Pedido>>() {});

        } catch (
                ObjectStreamException e) {
            throw new RuntimeException("Arquivo pedido.json não localizado!");
        } catch (
                IOException e) {
            throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
        }

    }

}
