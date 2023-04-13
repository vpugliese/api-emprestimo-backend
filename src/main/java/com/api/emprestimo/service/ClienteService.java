package com.api.emprestimo.service;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.exception.ClienteException;
import com.api.emprestimo.exception.ClienteJaCadastrado;
import com.api.emprestimo.mapper.ApiMapper;
import com.api.emprestimo.repository.ClienteRepository;
import com.api.emprestimo.request.ClienteDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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


    public ClienteDTO cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDto) throws ClienteJaCadastrado {
        if (clienteRepository.existsById(clienteDto.getCpf())) {
            throw new ClienteJaCadastrado(clienteDto.getCpf());
        }
        Cliente cliente = apiMapper.toCliente(clienteDto);
        clienteRepository.save(cliente);
        return apiMapper.toClienteDTO(cliente);
    }

    public List<ClienteDTO> listarClientes() {
        List<Cliente> listaClientes = clienteRepository.findAll();
        return apiMapper.listClienteDTO(listaClientes);
    }

    public ClienteDTO retornarCliente(String cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            Cliente cliente = clienteRepository.findById(cpf).get();
            return apiMapper.toClienteDTO(cliente);
        }
        throw new ClienteException(cpf);
    }


    public ClienteDTO alterarCliente(@Valid @RequestBody ClienteDTO clienteDto, String cpf) throws ClienteException {
        if (clienteRepository.existsById(cpf)) {
            Cliente clienteAlterado = this.clienteRepository.findById(cpf).get();
            apiMapper.mapAtualizarCliente(clienteDto, clienteAlterado);
            this.clienteRepository.save(clienteAlterado);
            return apiMapper.toClienteDTO(clienteAlterado);
        }
        throw new ClienteException(cpf);
    }

    @Transactional
    public void deletarCliente(String cpf) throws ClienteException {
        if (this.clienteRepository.existsById(cpf)) {
            this.clienteRepository.deleteById(cpf);
        } else throw new ClienteException(cpf);
    }


}
