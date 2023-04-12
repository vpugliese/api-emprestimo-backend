package com.api.emprestimo.controller;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.entities.Emprestimo;
import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.EmprestimoException;
import com.api.emprestimo.mapper.ApiMapper;
import com.api.emprestimo.request.ClienteDTO;
import com.api.emprestimo.service.ApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// TODO
// Consertar achar o empréstimo pelo CPF do cliente na URL
// 2. Fazer a segunda validação (10x o salário) dos Empréstimos
// 3. Fazer a terceira validação (soma dos empréstimos)
// 4. Inserir os DTO's.

@RestController
@RequestMapping("/clientes")
public class ApiController {

    private ApiService apiService;
    private ApiMapper apiMapper;

    @Autowired
    public ApiController(ApiMapper apiMapper, ApiService apiService) {
        this.apiMapper = apiMapper;
        this.apiService = apiService;
    }

    //POST /clientes
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastrarCliente(@Valid @RequestBody Cliente cliente){
        return this.apiService.cadastrarCliente(cliente);
    }

    //GET /clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return this.apiService.listarClientes();
    }


    //GET /clientes/53210216002
    @GetMapping("/{cpf}")
    public Cliente retornarCliente(@PathVariable String cpf) throws ClienteException {
    return this.apiService.retornarCliente(cpf);
    }


    //DELETE /clientes/53210216002
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{cpf}")
    public Cliente deletarCliente(@PathVariable String cpf) throws ClienteException {
        return this.apiService.deletarCliente(cpf);
    }

    //PUT /clientes/53210216002
    @PutMapping("/{cpf}")
    public Cliente alterarCliente(@Valid @RequestBody Cliente cliente, @PathVariable String cpf) throws ClienteException {
        return this.apiService.alterarCliente(cliente, cpf);
    }

    //Empréstimos

//    @PostMapping("/{cpf}/emprestimos")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Emprestimo cadastrarEmprestimo(@Valid @RequestBody Emprestimo emprestimo) {
//        return this.apiService.cadastrarEmprestimo(emprestimo);
//    }

    @PostMapping("/{cpf}/emprestimos")
    @ResponseStatus(HttpStatus.CREATED)
    public Emprestimo cadastrarEmprestimo(@Valid @RequestBody Emprestimo emprestimo) {
        return this.apiService.cadastrarEmprestimo(emprestimo);
    }


    @GetMapping("/{cpf}/emprestimos")
    public List<Emprestimo> listarEmprestimos(@PathVariable String cpf) {
        return this.apiService.listarEmprestimos();
    }



    @GetMapping("/{cpf}/emprestimos/{id}")
    public Emprestimo consultarEmprestimo(@PathVariable Long id) throws EmprestimoException {
        return this.apiService.consultarEmprestimo(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{cpf}/emprestimos/{id}")
    public Emprestimo deletarEmprestimo(@PathVariable Long id) throws EmprestimoException {
        return this.apiService.deletarEmprestimo(id);
    }





}
