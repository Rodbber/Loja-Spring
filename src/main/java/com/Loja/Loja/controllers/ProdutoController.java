package com.Loja.Loja.controllers;

import com.Loja.Loja.dtos.ProdutoRecordsDto;
import com.Loja.Loja.models.EmpresaModel;
import com.Loja.Loja.models.ProdutoModel;
import com.Loja.Loja.repositories.EmpresaRepository;
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
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    @Autowired
    EmpresaRepository empresaRepository;

    @PostMapping("/produtos")
    public ResponseEntity<Object> saveProduto(@RequestBody @Valid ProdutoRecordsDto produtoRecordsDto){
        var produtoModel = new ProdutoModel();
        Optional<EmpresaModel> optionalEmpresaModel = empresaRepository.findById(produtoRecordsDto.empresa_id());
        if(optionalEmpresaModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Empresa não encontrada.");
        }
        BeanUtils.copyProperties(produtoRecordsDto, produtoModel);
        produtoModel.setEmpresa(optionalEmpresaModel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtoModel));
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Object> putProduto(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProdutoRecordsDto produtoRecordsDto){
        Optional<ProdutoModel> produto = repository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados não encontrados.");
        }
        var produtoModel = produto.get();
        BeanUtils.copyProperties(produtoRecordsDto, produtoModel);
        produtoModel.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtoModel));
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoModel>> getProduto(){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findAll());
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> getProduto(@PathVariable(value = "id") UUID id){
        Optional<ProdutoModel> produtoModel = repository.findById(id);
        if(produtoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados não encontrados.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoModel);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable(value = "id") UUID id){
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto deletado com sucesso.");
    }
}
