package com.caiootoni.product_catalog;

import com.caiootoni.product_catalog.model.Produto;
import com.caiootoni.product_catalog.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}

    @Bean
    public CommandLineRunner initData(ProdutoRepository repository) {
        return args -> {
            repository.save(new Produto("Notebook", "Notebook Dell i7", new BigDecimal("4500.00")));
            repository.save(new Produto("Mouse", "Mouse sem fio", new BigDecimal("89.90")));
            repository.save(new Produto("Teclado", "Teclado mecânico", new BigDecimal("299.90")));
        };
    }
}
