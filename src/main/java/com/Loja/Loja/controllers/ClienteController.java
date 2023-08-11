package com.Loja.Loja.controllers;

import com.Loja.Loja.dtos.ClienteRecordsDto;
import com.Loja.Loja.dtos.ProdutoRecordsDto;
import com.Loja.Loja.models.ClienteModel;
import com.Loja.Loja.models.EmpresaModel;
import com.Loja.Loja.models.ProdutoModel;
import com.Loja.Loja.repositories.ClienteRepository;
import com.Loja.Loja.repositories.EmpresaRepository;
import com.Loja.Loja.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ClienteController {
    @Autowired
    ClienteRepository repository;
    @Autowired
    EmpresaRepository empresaRepository;

    @PostMapping("/clientes")
    public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteRecordsDto clienteRecordsDto){
        var clienteModel = new ClienteModel();
        Optional<EmpresaModel> optionalEmpresaModel = empresaRepository.findById(clienteRecordsDto.empresa_id());
        if(optionalEmpresaModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Empresa não encontrada.");
        }
        BeanUtils.copyProperties(clienteRecordsDto, clienteModel);
        clienteModel.setEmpresa(optionalEmpresaModel.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(clienteModel));
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Object> putCliente(@PathVariable(value = "id") Long id, @RequestBody @Valid ClienteRecordsDto clienteRecordsDto){
        Optional<ClienteModel> cliente = repository.findById(id);
        if(cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados não encontrados.");
        }
        var clienteModel = cliente.get();
        BeanUtils.copyProperties(clienteRecordsDto, clienteModel);
        clienteModel.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(clienteModel));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteModel>> getCliente(){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findAll());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> getCliente(@PathVariable(value = "id") Long id){
        Optional<ClienteModel> clienteModel = repository.findById(id);
        if(clienteModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados não encontrados.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteModel);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") Long id){
        if(repository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso.");
    }
}
