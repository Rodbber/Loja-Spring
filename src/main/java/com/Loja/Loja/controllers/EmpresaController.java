package com.Loja.Loja.controllers;

import com.Loja.Loja.dtos.EmpresaRecordsDto;
import com.Loja.Loja.models.EmpresaModel;
import com.Loja.Loja.repositories.EmpresaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class EmpresaController {
    @Autowired
    EmpresaRepository repository;

    @PostMapping("/empresas")
    public ResponseEntity<EmpresaModel> saveEmpresa(@RequestBody @Valid EmpresaRecordsDto empresaRecordsDto){
        var empresaModel = new EmpresaModel();
        BeanUtils.copyProperties(empresaRecordsDto, empresaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(empresaModel));
    }

    @PutMapping("/empresas/{id}")
    public ResponseEntity<Object> putEmpresa(@PathVariable(value = "id") Long id, @RequestBody @Valid EmpresaRecordsDto empresaRecordsDto){
        Optional<EmpresaModel> empresa = repository.findById(id);
        if(empresa.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados não encontrados.");
        }
        var empresaModel = empresa.get();
        BeanUtils.copyProperties(empresaRecordsDto, empresaModel);
        empresaModel.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaModel);
    }

    @GetMapping("/empresas")
    public ResponseEntity<List<EmpresaModel>> getEmpresas(){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findAll());
    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<Object> getEmpresa(@PathVariable(value = "id") Long id){
        Optional<EmpresaModel> empresaModel = repository.findById(id);
        if(empresaModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados não encontrados.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaModel);
    }

    @DeleteMapping("/empresas/{id}")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable(value = "id") Long id){
        try {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Empresa deletado com sucesso.");
        }catch (Throwable e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
