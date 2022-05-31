package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.relatorios.RelatorioSintetico;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioSinteticoTest {

    @Mock
    RelatorioSintetico relatorioSinteticoMock = Mockito.mock(RelatorioSintetico.class);

    @Test
    public void DeveRetornarTotalDeProdutosVendidos(){
        long retorno =  relatorioSinteticoMock.getTotalDeCategorias();
        Assert.assertEquals(retorno, 0);
    }

    private List<Pedido> pedidosMock(){
        List<Pedido> listaMock  = new ArrayList<>();

        Pedido pedido = new Pedido(
                "Itens Pessoais",
                "Garrafa 2.0",
                "Ana",
                new BigDecimal("100"),
                14,
                LocalDate.now());

        listaMock.add(pedido);

        return listaMock;
    }

}
