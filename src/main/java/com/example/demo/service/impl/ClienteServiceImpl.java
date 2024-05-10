package com.example.demo.service.impl;


import com.example.demo.entity.Cliente;
import com.example.demo.http.controller.dto.filtro.ClienteFiltro;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listaCliente(ClienteFiltro clienteFiltro){
        Cliente cliente = Cliente.builder()
                .id(clienteFiltro.getId())
                .nome(clienteFiltro.getNome())
                .numconta(clienteFiltro.getNumconta())
                .saldoconta(clienteFiltro.getSaldoconta())
                .build();

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);


        Example example;
        example = Example.of(cliente, exampleMatcher);

        return clienteRepository.findAll(example);
    }

    public Optional<Cliente> buscarPorNumConta(String numconta){
        return clienteRepository.findById(numconta);
    }

    public void removerPorNumConta(String numconta){
        clienteRepository.deleteById(numconta);
    }


}

