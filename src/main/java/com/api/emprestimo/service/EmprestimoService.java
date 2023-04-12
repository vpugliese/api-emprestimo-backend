package com.api.emprestimo.service;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.entities.Emprestimo;
import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.EmprestimoException;
import com.api.emprestimo.exception.ValorExcedidoException;
import com.api.emprestimo.mapper.ApiMapper;
import com.api.emprestimo.repository.ClienteRepository;
import com.api.emprestimo.repository.EmprestimoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final ClienteRepository clienteRepository;
    private ApiMapper apiMapper;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, ClienteRepository clienteRepository, ApiMapper apiMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.clienteRepository = clienteRepository;
        this.apiMapper = apiMapper;
    }


    @Transactional
    public Emprestimo cadastrarEmprestimo(@Valid Emprestimo emprestimo) throws ClienteException, ValorExcedidoException {
        if (clienteRepository.existsById(emprestimo.getCpf())) {
            Cliente cliente = clienteRepository.findByCpf(emprestimo.getCpf());
            BigDecimal valorInicial = emprestimo.getValorInicial();
            if (cliente.podeReceberEmprestimo(valorInicial)) {
                emprestimo.setCliente(cliente);
                emprestimo.setValorFinal();
                return this.emprestimoRepository.save(emprestimo);
            } throw new ValorExcedidoException(cliente.getCpf());

        } throw new ClienteException(emprestimo.getCpf());
    }


    public List<Emprestimo> listarEmprestimos(String cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            Cliente cliente = clienteRepository.findByCpf(cpf);
            return cliente.getEmprestimos();
        }
        throw new ClienteException(cpf);
    }

    public Emprestimo consultarEmprestimo(Long id) throws EmprestimoException {
        if (this.emprestimoRepository.existsById(id)) {
            return this.emprestimoRepository.findById(id).get();
        }
        throw new EmprestimoException(id);
    }

    @Transactional
    public MensagemSucesso deletarEmprestimo(Long id, String cpf) throws EmprestimoException, ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            if (this.emprestimoRepository.existsById(id)) {
                this.emprestimoRepository.deleteById(id);
                MensagemSucesso mensagem = new MensagemSucesso();
                mensagem.setMensagem("Empréstimo deletado com sucesso.");
                return mensagem;
            }
            throw new EmprestimoException(id);
        }
        throw new ClienteException(cpf);
    }


}
