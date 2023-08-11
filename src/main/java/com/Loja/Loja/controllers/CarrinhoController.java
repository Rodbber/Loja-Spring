package com.Loja.Loja.controllers;

import com.Loja.Loja.dtos.CarrinhoRecordsDto;
import com.Loja.Loja.dtos.ProdutoRecordsDto;
import com.Loja.Loja.models.CarrinhoModel;
import com.Loja.Loja.models.EmpresaModel;
import com.Loja.Loja.models.ProdutoModel;
import com.Loja.Loja.repositories.CarrinhoRepository;
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
public class CarrinhoController {
    @Autowired
    CarrinhoRepository repository;

    @Autowired
    EmpresaRepository empresaRepository;

    @PostMapping("/carrinhos")
    public ResponseEntity<Object> saveCarrinho(@RequestBody @Valid CarrinhoRecordsDto carrinhoRecordsDto){
        var carrinhoModel = new CarrinhoModel();
        Optional<EmpresaModel> optionalEmpresaModel = empresaRepository.findById(carrinhoRecordsDto.empresa_id());
        if(optionalEmpresaModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Empresa não encontrada.");
        }
        BeanUtils.copyProperties(carrinhoRecordsDto, carrinhoModel);
        carrinhoModel.setEmpresa(optionalEmpresaModel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(carrinhoModel));
    }

    @GetMapping("/carrinhos")
    public ResponseEntity<List<CarrinhoModel>> getCarrinho(){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findAll());
    }

    @GetMapping("/carrinhos/{id}")
    public ResponseEntity<Object> getCarrinho(@PathVariable(value = "id") UUID id){
        Optional<CarrinhoModel> produtoModel = repository.findById(id);
        if(produtoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados não encontrados.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoModel);
    }

    @DeleteMapping("/carrinhos/{id}")
    public ResponseEntity<Object> deleteCarrinho(@PathVariable(value = "id") UUID id){
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Carrinho deletado com sucesso.");
    }
}
