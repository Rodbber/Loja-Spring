package com.Loja.Loja.controllers;

import com.Loja.Loja.dtos.CarrinhoProdutoRecordsDto;
import com.Loja.Loja.dtos.ProdutoRecordsDto;
import com.Loja.Loja.models.CarrinhoModel;
import com.Loja.Loja.models.CarrinhoProdutoModel;
import com.Loja.Loja.models.EmpresaModel;
import com.Loja.Loja.models.ProdutoModel;
import com.Loja.Loja.repositories.CarrinhoProdutoRepository;
import com.Loja.Loja.repositories.CarrinhoRepository;
import com.Loja.Loja.repositories.ProdutoRepository;
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
public class CarrinhoProdutoController {
    @Autowired
    CarrinhoProdutoRepository repository;
    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/carrinho/produtos")
    public ResponseEntity<Object> saveCarrinhoProduto(@RequestBody @Valid CarrinhoProdutoRecordsDto produtoRecordsDto){
        var produtoModel = new CarrinhoProdutoModel();
        Optional<CarrinhoModel> optionalCarrinhoModel = carrinhoRepository.findById(produtoRecordsDto.carrinho_id());
        Optional<ProdutoModel> optionalProdutoModel = produtoRepository.findById(produtoRecordsDto.produto_id());
        if(optionalCarrinhoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Carrinho não encontrado.");
        }
        if(optionalProdutoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto não encontrado.");
        }
        BeanUtils.copyProperties(produtoRecordsDto, produtoModel);
        produtoModel.setCarrinho(optionalCarrinhoModel.get());
        produtoModel.setProduto(optionalProdutoModel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtoModel));
    }

    @GetMapping("/carrinho/produtos")
    public ResponseEntity<List<CarrinhoProdutoModel>> getCarrinhoProduto(){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findAll());
    }

    @GetMapping("/carrinho/produtos/{id}")
    public ResponseEntity<Object> getCarrinhoProduto(@PathVariable(value = "id") UUID id){
        Optional<CarrinhoProdutoModel> produtoModel = repository.findById(id);
        if(produtoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados não encontrados.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoModel);
    }

    @DeleteMapping("/carrinho/produtos/{id}")
    public ResponseEntity<Object> deleteCarrinhoProduto(@PathVariable(value = "id") UUID id){
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto do carrinho deletado com sucesso.");
    }
}
