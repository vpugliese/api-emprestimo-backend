package com.api.emprestimo.controller;

import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.ClienteJaCadastrado;
import com.api.emprestimo.request.ClienteDTO;
import com.api.emprestimo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDto) throws ClienteJaCadastrado {
        return this.clienteService.cadastrarCliente(clienteDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> listarClientes() {
        return this.clienteService.listarClientes();
    }


    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO retornarCliente(@PathVariable String cpf) throws ClienteException {
        return this.clienteService.retornarCliente(cpf);
    }


    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable String cpf) throws ClienteException {
        this.clienteService.deletarCliente(cpf);
    }

    @PutMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO alterarCliente(@Valid @RequestBody ClienteDTO clienteDto, @PathVariable String cpf) throws ClienteException {
        return this.clienteService.alterarCliente(clienteDto, cpf);
    }
}
