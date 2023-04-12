package com.api.emprestimo.service;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.entities.Emprestimo;
import com.api.emprestimo.enums.Relacionamento;
import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.ClienteNaoEncontrado;
import com.api.emprestimo.exception.EmprestimoException;
import com.api.emprestimo.mapper.ApiMapper;
import com.api.emprestimo.repository.ClienteRepository;
import com.api.emprestimo.repository.EmprestimoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {

    private final ClienteRepository clienteRepository;
    private final EmprestimoRepository emprestimoRepository;
    private ApiMapper apiMapper;

    @Autowired
    public ApiService(ApiMapper apiMapper, ClienteRepository clienteRepository, EmprestimoRepository emprestimoRepository) {
        this.clienteRepository = clienteRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.apiMapper = apiMapper;
    }

    //Cliente
    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes(){
        return this.clienteRepository.findAll();
    }

    public Cliente retornarCliente(String cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            return this.clienteRepository.findById(cpf).get();
        }
        throw new ClienteException(cpf);
    }

    public Cliente alterarCliente(@Valid Cliente cliente, String cpf) throws ClienteException{
        if (this.clienteRepository.existsById(cpf)) {
            return this.clienteRepository.save(cliente);
        } throw new ClienteException(cpf);
    }

    @Transactional
    public Cliente deletarCliente(String cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            this.clienteRepository.deleteById(cpf);
        } throw new ClienteException(cpf);
    }


    //Empréstimo

    @Transactional
    public Emprestimo cadastrarEmprestimo(@Valid Emprestimo emprestimo) {
        if (clienteRepository.existsById(emprestimo.getCpf())) {
            Cliente cliente = clienteRepository.findById(emprestimo.getCpf()).get();
            emprestimo.setCliente(cliente);
            emprestimo.setValorFinal();
            return this.emprestimoRepository.save(emprestimo);
        } return null; //fazer a mensagem de erro
    }


    public List<Emprestimo> listarEmprestimos(){
        return this.emprestimoRepository.findAll();
    }

//    public List<Emprestimo> listarEmprestimos(String cpf){
//        if (this.clienteRepository.existsById(cpf)) {
//            return this.emprestimoRepository.findByCliente(cpf).get();
//        } return null;
//    }

    public Emprestimo consultarEmprestimo(Long id) throws EmprestimoException {
        if (this.emprestimoRepository.existsById(id)) {
            return this.emprestimoRepository.findById(id).get();
        }
        throw new EmprestimoException(id);
    }

    @Transactional
    public Emprestimo deletarEmprestimo(Long id) throws EmprestimoException {
        if (this.emprestimoRepository.existsById(id)) {
            this.emprestimoRepository.deleteById(id);
        } throw new EmprestimoException(id); //Fazer mensagem
    }

    //Checagem do número de empréstimos de um cliente
    public int quantosEmprestimos(Cliente cliente) {
        return cliente.getEmprestimos().size();
    }

    //Regra de Empréstimos até 10 vezes a renda mensal do cliente
//    public void permitirEmprestimo (Cliente cliente) {
//        if (cliente.getRenda() <= 10 * cliente.getRenda()) {
//
//        }
//    }

//    public Emprestimo permitirEmprestimo(Emprestimo emprestimo) {
//        if () {
//            return this.emprestimoRepository.save(emprestimo);
//
//        }
//    }




}
