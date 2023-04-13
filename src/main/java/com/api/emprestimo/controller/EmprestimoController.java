package com.api.emprestimo.controller;

import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.EmprestimoException;
import com.api.emprestimo.exception.ValorExcedidoException;
import com.api.emprestimo.request.EmprestimoDTO;
import com.api.emprestimo.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes/{cpf}/emprestimos")
public class EmprestimoController {


    private EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmprestimoDTO cadastrarEmprestimo(@Valid @RequestBody EmprestimoDTO emprestimoDto, @PathVariable String cpf) throws ClienteException, ValorExcedidoException {
        return emprestimoService.cadastrarEmprestimo(emprestimoDto, cpf);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmprestimoDTO> listarEmprestimos(@PathVariable String cpf) throws ClienteException {
        return this.emprestimoService.listarEmprestimos(cpf);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmprestimoDTO consultarEmprestimo(@PathVariable String cpf, @PathVariable Long id) throws EmprestimoException, ClienteException {
        return this.emprestimoService.consultarEmprestimo(cpf, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletarEmprestimo(@PathVariable Long id, @PathVariable String cpf) throws EmprestimoException, ClienteException {
        this.emprestimoService.deletarEmprestimo(id, cpf);
    }
}
