package com.api.emprestimo.repository;

import com.api.emprestimo.entities.Cliente;
import com.api.emprestimo.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    boolean existsById(String cpf);


    List<Emprestimo> findById(String cpf);
}
