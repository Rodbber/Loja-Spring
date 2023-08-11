package com.Loja.Loja.controllers;

import com.Loja.Loja.dtos.VendaRecordsDto;
import com.Loja.Loja.models.*;
import com.Loja.Loja.repositories.*;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class VendaController {
    @Autowired
    VendaRepository repository;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CarrinhoProdutoRepository carrinhoProdutoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/vendas")
    public ResponseEntity<Object> saveVenda(@RequestBody @Valid VendaRecordsDto vendaRecordsDto) {
        var vendaModel = new VendaModel();
        Optional<CarrinhoModel> optionalCarrinhoModel = carrinhoRepository.findById(vendaRecordsDto.carrinho_id());
        Optional<ClienteModel> optionalClienteModel = clienteRepository.findById(vendaRecordsDto.cliente_id());
        if (optionalCarrinhoModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Carrinho não encontrado.");
        }

        BeanUtils.copyProperties(vendaRecordsDto, vendaModel);
        vendaModel.setCarrinho(optionalCarrinhoModel.get());
        if (!optionalClienteModel.isEmpty()) {
            vendaModel.setCliente(optionalClienteModel.get());
        }
        UUID carrinho_id = optionalCarrinhoModel.get().getId();
        List<CarrinhoProdutoModel> lista = carrinhoProdutoRepository.findAllByCarrinho_Id(carrinho_id);
        for (CarrinhoProdutoModel carrinho : lista) {
            ProdutoModel produto = carrinho.getProduto();
            produto.setQuantidade(produto.getQuantidade() - carrinho.getQuantidade());
        }
        //return ResponseEntity.status(HttpStatus.CREATED).body(vendaModel);

        var venda = repository.save(vendaModel);
//        // var carrinho_produtos = carrinhoProdutoRepository.findAllByCarrinho_Id(optionalCarrinhoModel.get().getId());
//
//
        return ResponseEntity.status(HttpStatus.CREATED).body(venda);
    }

    @PutMapping("/vendas/{id}")
    public ResponseEntity<Object> putVenda(@PathVariable(value = "id") Long id, @RequestBody @Valid VendaRecordsDto produtoRecordsDto) {
        Optional<VendaModel> venda = repository.findById(id);
        if (venda.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados não encontrados.");
        }
        var vendaModel = venda.get();
        BeanUtils.copyProperties(produtoRecordsDto, vendaModel);
        vendaModel.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(vendaModel));
    }

    @GetMapping("/vendas")
    public ResponseEntity<List<VendaModel>> getVenda() {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findAll());
    }

    @GetMapping("/vendas/{id}")
    public ResponseEntity<Object> getVenda(@PathVariable(value = "id") Long id) {
        Optional<VendaModel> produtoModel = repository.findById(id);
        if (produtoModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados não encontrados.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoModel);
    }

    @DeleteMapping("/vendas/{id}")
    public ResponseEntity<Object> deleteVenda(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venda deletado com sucesso.");
    }
}
