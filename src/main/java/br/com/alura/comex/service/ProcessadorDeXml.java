package br.com.alura.comex.service;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.List;

public class ProcessadorDeXml extends ProcessadorAdapter {
    @Override
    public List<Pedido> listarPedidos(String nomeArquivo) {

        try{

            XmlMapper xmlMapper = new XmlMapper();

            xmlMapper.registerModule(new JavaTimeModule());
            return xmlMapper.readValue(ClassLoader.getSystemResource(nomeArquivo.concat(".xml")), new TypeReference<List<Pedido>>() {});


        } catch (
                ObjectStreamException e) {
            throw new RuntimeException("Arquivo pedido.xml não localizado!");
        } catch (
                IOException e) {
            throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
        }
    }
}
