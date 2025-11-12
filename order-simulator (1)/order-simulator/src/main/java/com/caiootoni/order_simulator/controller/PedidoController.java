package com.caiootoni.order_simulator.controller;

import com.caiootoni.order_simulator.dto.Produto;
import com.caiootoni.order_simulator.model.ItemPedido;
import com.caiootoni.order_simulator.model.PedidoSimulacao;
import com.caiootoni.order_simulator.client.ProdutoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private ProdutoClient produtoClient;

    private AtomicLong idCounter = new AtomicLong();

    @GetMapping("/produtos-disponiveis")
    public List<Produto> listarProdutosDisponiveis() {
        return produtoClient.listarProdutos();
    }

    @PostMapping("/simular")
    public PedidoSimulacao simularPedido(@RequestBody List<ItemPedido> itens) {
        PedidoSimulacao pedido = new PedidoSimulacao();
        pedido.setId(idCounter.incrementAndGet());

        List<ItemPedido> itensValidados = itens.stream()
                .map(item -> {
                    try {
                        Produto produto = produtoClient.buscarProduto(item.getProdutoId());
                        return new ItemPedido(
                                produto.getId(),
                                item.getQuantidade(),
                                produto.getPreco()
                        );
                    } catch (Exception e) {
                        throw new RuntimeException("Produto não encontrado: " + item.getProdutoId());
                    }
                })
                .collect(Collectors.toList());

        pedido.setItens(itensValidados);
        return pedido;
    }

    @GetMapping("/{id}/simular")
    public PedidoSimulacao simularPedidoComProduto(@PathVariable Long id) {
        Produto produto = produtoClient.buscarProduto(id);

        PedidoSimulacao pedido = new PedidoSimulacao();
        pedido.setId(idCounter.incrementAndGet());

        ItemPedido item = new ItemPedido(produto.getId(), 1, produto.getPreco());
        pedido.setItens(List.of(item));

        return pedido;
    }
}
