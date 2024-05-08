package com.example.demo.http.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listaCliente(){
        return clienteService.listaCliente();
    }

    @GetMapping("/{numconta}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscarClientePorNumConta(@PathVariable("numconta")String numconta) {
            return clienteService.buscarPorNumConta(numconta)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }


    @DeleteMapping("/{numconta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerClientePorNumConta(@PathVariable("numconta")String numconta){
        clienteService.buscarPorNumConta(numconta)
                .map(cliente -> {
                    clienteService.removerPorNumConta(cliente.getNumconta());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable("numconta")String numconta,@RequestBody Cliente cliente){
        clienteService.buscarPorNumConta(numconta)
                .map(clienteBase ->{
                    modelMapper.map(cliente, clienteBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }
}
