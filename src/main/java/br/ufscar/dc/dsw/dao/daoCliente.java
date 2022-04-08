package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Cliente;

@SuppressWarnings("unchecked")
public interface daoCliente extends CrudRepository<Cliente, String> {
    Cliente findByEmail(String email);

    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    void deleteByCpf(String cpf);

}
