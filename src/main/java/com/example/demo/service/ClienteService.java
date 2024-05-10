package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.http.controller.dto.filtro.ClienteFiltro;

import java.util.List;
import java.util.Optional;

public interface ClienteService {


     Cliente salvar(Cliente cliente);

     List<Cliente> listaCliente(ClienteFiltro clienteFiltro);

     Optional<Cliente> buscarPorNumConta(String numconta);

     void removerPorNumConta(String numconta);


}

