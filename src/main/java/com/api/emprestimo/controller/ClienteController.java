package com.api.emprestimo.controller;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.ClienteJaCadastrado;
import com.api.emprestimo.mapper.ApiMapper;
import com.api.emprestimo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// TODO
// Validações
// 4. Inserir os DTO's.

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private ClienteService clienteService;
    private ApiMapper apiMapper;

    @Autowired
    public ClienteController(ApiMapper apiMapper, ClienteService clienteService) {
        this.apiMapper = apiMapper;
        this.clienteService = clienteService;
    }

    //POST /clientes
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastrarCliente(@Valid @RequestBody Cliente cliente) throws ClienteJaCadastrado {
        return this.clienteService.cadastrarCliente(cliente);
    }

    //GET /clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return this.clienteService.listarClientes();
    }


    //GET /clientes/53210216002
    @GetMapping("/{cpf}")
    public Cliente retornarCliente(@PathVariable String cpf) throws ClienteException {
        return this.clienteService.retornarCliente(cpf);
    }


    //DELETE /clientes/53210216002
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{cpf}")
    public Cliente deletarCliente(@PathVariable String cpf) throws ClienteException {
        return this.clienteService.deletarCliente(cpf);
    }

    //PUT /clientes/53210216002
    @PutMapping("/{cpf}")
    public Cliente alterarCliente(@Valid @RequestBody Cliente cliente, @PathVariable String cpf) throws ClienteException {
        return this.clienteService.alterarCliente(cliente, cpf);
    }
}
