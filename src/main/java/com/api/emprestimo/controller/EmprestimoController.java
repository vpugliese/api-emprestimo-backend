package com.api.emprestimo.controller;

import com.api.emprestimo.entities.Emprestimo;
import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.EmprestimoException;
import com.api.emprestimo.exception.ValorExcedidoException;
import com.api.emprestimo.mapper.ApiMapper;
import com.api.emprestimo.repository.ClienteRepository;
import com.api.emprestimo.repository.EmprestimoRepository;
import com.api.emprestimo.request.EmprestimoDTO;
import com.api.emprestimo.service.ClienteService;
import com.api.emprestimo.service.EmprestimoService;
import com.api.emprestimo.service.MensagemSucesso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes/{cpf}/emprestimos")
public class EmprestimoController {

    private EmprestimoRepository emprestimoRepository;
    private ClienteRepository clienteRepository;
    private ApiMapper apiMapper;
    private ClienteService clienteService;
    private EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoRepository emprestimoRepository, ClienteRepository clienteRepository, ApiMapper apiMapper, ClienteService clienteService, EmprestimoService emprestimoService) {
        this.emprestimoRepository = emprestimoRepository;
        this.clienteRepository = clienteRepository;
        this.apiMapper = apiMapper;
        this.clienteService = clienteService;
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmprestimoDTO cadastrarEmprestimo(@Valid @RequestBody EmprestimoDTO emprestimoDto, @PathVariable String cpf) throws ClienteException, ValorExcedidoException {
        return emprestimoService.cadastrarEmprestimo(emprestimoDto, cpf);
    }


    @GetMapping
    public List<EmprestimoDTO> listarEmprestimos(@PathVariable String cpf) throws ClienteException {
        return this.emprestimoService.listarEmprestimos(cpf);
    }

    @GetMapping("/{id}")
    public EmprestimoDTO consultarEmprestimo(@PathVariable String cpf,@PathVariable Long id) throws EmprestimoException, ClienteException {
        return this.emprestimoService.consultarEmprestimo(cpf, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public MensagemSucesso deletarEmprestimo(@PathVariable Long id, @PathVariable String cpf) throws EmprestimoException, ClienteException {
        return this.emprestimoService.deletarEmprestimo(id, cpf);
    }
}
