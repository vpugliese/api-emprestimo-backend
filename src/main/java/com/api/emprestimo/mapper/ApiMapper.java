package com.api.emprestimo.mapper;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.entities.Emprestimo;
import com.api.emprestimo.request.ClienteDTO;
import com.api.emprestimo.request.EmprestimoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO retornaClienteDTO (Cliente cliente);
    Cliente retornaCliente (ClienteDTO clienteDTO);
    EmprestimoDTO retornaEmprestimoDTO (Emprestimo emprestimo);
    Emprestimo retornaEmprestimo (EmprestimoDTO emprestimoDTO);

}
