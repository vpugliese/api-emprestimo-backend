package com.api.emprestimo.service;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.ClienteJaCadastrado;
import com.api.emprestimo.mapper.ApiMapper;
import com.api.emprestimo.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private ApiMapper apiMapper;

    @Autowired
    public ClienteService(ApiMapper apiMapper, ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.apiMapper = apiMapper;
    }


    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) throws ClienteJaCadastrado {
        if (clienteRepository.existsById(cliente.getCpf())) {
            throw new ClienteJaCadastrado(cliente.getCpf());
        }
        return this.clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return this.clienteRepository.findAll();
    }

    public Cliente retornarCliente(String cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            return this.clienteRepository.findById(cpf).get();
        }
        throw new ClienteException(cpf);
    }

    public Cliente alterarCliente(@Valid Cliente cliente, String cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            return this.clienteRepository.save(cliente);
        }
        throw new ClienteException(cpf);
    }

    @Transactional
    public Cliente deletarCliente(String cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            this.clienteRepository.deleteById(cpf);
        }
        throw new ClienteException(cpf);
    }

}
