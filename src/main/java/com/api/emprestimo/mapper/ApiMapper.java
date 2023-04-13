package com.api.emprestimo.mapper;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.entities.Emprestimo;
import com.api.emprestimo.request.ClienteDTO;
import com.api.emprestimo.request.EmprestimoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApiMapper {

    ApiMapper INSTANCE = Mappers.getMapper(ApiMapper.class);

    //Mapeamento do Cliente e ClienteDTO

    @Mapping(source = "endereco.rua", target = "rua")
    @Mapping(source="endereco.complemento", target = "complemento")
    @Mapping(source="endereco.cep", target = "cep")
    ClienteDTO toClienteDTO(Cliente cliente);

    @InheritInverseConfiguration
    Cliente toCliente(ClienteDTO clienteDTO);

    List<ClienteDTO> listClienteDTO(List<Cliente> cliente);

    @InheritInverseConfiguration
    void mapAtualizarCliente(ClienteDTO clientDto, @MappingTarget Cliente cliente);

    //Mapeamento do Empréstimo e EmpréstimoDTO

    @Mapping(source = "relacionamento", target = "relacionamento")
    EmprestimoDTO toEmprestimoDTO(Emprestimo emprestimo);

    Emprestimo toEmprestimo(EmprestimoDTO emprestimoDTO);

    List<EmprestimoDTO> listEmprestimoDTO(List<Emprestimo> emprestimo);
}
