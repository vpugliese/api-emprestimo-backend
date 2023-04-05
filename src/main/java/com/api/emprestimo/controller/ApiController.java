package com.api.emprestimo.controller;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.entities.Emprestimo;
import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.EmprestimoException;
import com.api.emprestimo.service.ApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ApiController {

    private ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
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
    public Cliente retornarCliente(@PathVariable Long cpf) throws ClienteException {
    return this.apiService.retornarCliente(cpf);
    }


    //DELETE /clientes/53210216002
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{cpf}")
    public Cliente deletarCliente(@PathVariable Long cpf) throws ClienteException {
        return this.apiService.deletarCliente(cpf);
    }

    //PUT /clientes/53210216002
    @PutMapping("/{id}")
    public Cliente alterarCliente(@PathVariable Long cpf, @Valid @RequestBody Cliente cliente) throws ClienteException {
        return this.apiService.alterarCliente(cpf, cliente);
    }

    //Empréstimos

    @PostMapping("/{cpf}/emprestimos")
    @ResponseStatus(HttpStatus.CREATED)
    public Emprestimo cadastrarEmprestimo(@Valid @RequestBody Emprestimo emprestimo) {
        return this.apiService.cadastrarEmprestimo(emprestimo);
    }

    @GetMapping("/{cpf}/emprestimos")
    public List<Emprestimo> listarEmprestimos() {
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
