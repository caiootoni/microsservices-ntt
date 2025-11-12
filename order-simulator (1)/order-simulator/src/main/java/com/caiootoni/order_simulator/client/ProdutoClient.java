package com.caiootoni.order_simulator.client;

import com.caiootoni.order_simulator.dto.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "produto-service")
public interface ProdutoClient {

    @GetMapping("/produtos")
    List<Produto> listarProdutos();

    @GetMapping("/produtos/{id}")
    Produto buscarProduto(@PathVariable Long id);
}