package com.api.emprestimo.mapper;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.entities.Emprestimo;
import com.api.emprestimo.request.ClienteDTO;
import com.api.emprestimo.request.EmprestimoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApiMapper {

    ApiMapper INSTANCE = Mappers.getMapper(ApiMapper.class);

    ClienteDTO toClienteDTO (Cliente cliente);
    Cliente toCliente (ClienteDTO clienteDTO);
    EmprestimoDTO toEmprestimoDTO (Emprestimo emprestimo);
    Emprestimo toEmprestimo (EmprestimoDTO emprestimoDTO);

}
