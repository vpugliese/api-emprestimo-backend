package com.api.emprestimo.service;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.entities.Emprestimo;
import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.EmprestimoException;
import com.api.emprestimo.repository.ClienteRepository;
import com.api.emprestimo.repository.EmprestimoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {

    private final ClienteRepository clienteRepository;
    private final EmprestimoRepository emprestimoRepository;

    @Autowired
    public ApiService(ClienteRepository clienteRepository, EmprestimoRepository emprestimoRepository) {
        this.clienteRepository = clienteRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    //Cliente
    public Cliente cadastrarCliente(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes(){
        return this.clienteRepository.findAll();
    }

    public Cliente retornarCliente(Long cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            return this.clienteRepository.findById(cpf).get();
        }
        throw new ClienteException(cpf);
    }

    public Cliente deletarCliente(Long cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            this.clienteRepository.deleteById(cpf);
        } throw new ClienteException(cpf);
    }

    public Cliente alterarCliente(Long cpf, @Valid Cliente cliente) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            Cliente clienteAlterado = this.clienteRepository.findById(cpf).get();
            cliente.setCpf(cpf);
            if (cliente.getCpf() == null) {
                cliente.setCpf(clienteAlterado.getCpf());
            }
            return this.clienteRepository.save(cliente);
        } throw new ClienteException(cpf);
    }

    //Empréstimo

    public Emprestimo cadastrarEmprestimo(Emprestimo emprestimo) {
        return this.emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos(){
        return this.emprestimoRepository.findAll();
    }

    public Emprestimo consultarEmprestimo(Long id) throws EmprestimoException {
        if (this.emprestimoRepository.existsById(id)) {
            return this.emprestimoRepository.findById(id).get();
        }
        throw new EmprestimoException(id);
    }

    public Emprestimo deletarEmprestimo(Long id) throws EmprestimoException {
        if (this.emprestimoRepository.existsById(id)) {
            this.emprestimoRepository.deleteById(id);
        } throw new EmprestimoException(id); //Fazer mensagem
    }

}
