package br.com.alura.comex.baseDados;

import br.com.alura.comex.model.Pedido;

import java.io.ObjectStreamException;
import java.util.List;

public abstract class ProcessadorAdapter {

    public abstract List<Pedido> listarPedidos(String nomeArquivo);

}
