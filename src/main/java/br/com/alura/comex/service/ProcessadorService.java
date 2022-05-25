package br.com.alura.comex.service;

import br.com.alura.comex.baseDados.ProcessadorAdapter;

import java.util.List;

public class ProcessadorService {

    private List<ProcessadorAdapter> processador;

    public ProcessadorService(List<ProcessadorAdapter> processador) {
        this.processador = processador;
    }

    //Tentativa de chamada automática e processamento de todos os arquivos (fiz no intervalo do almoço e do plantão)
    //Acredito que seja interessante eu seguir posteriormente com o template method do xml e json que foram apresentadas no plantão
    public void processarDados(){
        this.processador.forEach(p -> p.listarPedidos("pedidos"));
    }




}
