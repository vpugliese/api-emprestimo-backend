package com.api.emprestimo.service;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.entities.Emprestimo;
import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.EmprestimoException;
import com.api.emprestimo.exception.ValorExcedidoException;
import com.api.emprestimo.mapper.ApiMapper;
import com.api.emprestimo.repository.ClienteRepository;
import com.api.emprestimo.repository.EmprestimoRepository;
import com.api.emprestimo.request.EmprestimoDTO;
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
    public EmprestimoDTO cadastrarEmprestimo(@Valid EmprestimoDTO emprestimoDto, String cpf) throws ClienteException, ValorExcedidoException {
        if (cpf.equals(emprestimoDto.getCpfCliente()) && clienteRepository.existsById(cpf)) {
            Cliente cliente = clienteRepository.findByCpf(cpf);
            BigDecimal valorInicial = emprestimoDto.getValorInicial();
            if (cliente.podeReceberEmprestimo(valorInicial)) {
                Emprestimo emprestimo = apiMapper.toEmprestimo(emprestimoDto);
                emprestimo.setCliente(cliente);
                emprestimo.setValorFinal();
                emprestimoRepository.save(emprestimo);
                return apiMapper.toEmprestimoDTO(emprestimo);
            } throw new ValorExcedidoException(cpf);

        } throw new ClienteException(cpf);
    }


    public List<EmprestimoDTO> listarEmprestimos(String cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            Cliente cliente = clienteRepository.findByCpf(cpf);
            return apiMapper.listEmprestimoDTO(cliente.getEmprestimos());
        }
        throw new ClienteException(cpf);
    }

    public EmprestimoDTO consultarEmprestimo(String cpf, Long id) throws EmprestimoException, ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            if (this.emprestimoRepository.existsById(id)) {
                Emprestimo emprestimoPorId = emprestimoRepository.findById(id).get();
                return apiMapper.toEmprestimoDTO(emprestimoPorId);
            }
            throw new EmprestimoException(id);
        } throw new ClienteException(cpf);
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
