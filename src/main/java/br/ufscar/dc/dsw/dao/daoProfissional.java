package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.User;
import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface daoProfissional extends CrudRepository<Profissional, String> {

    Profissional findByCpf(String cpf);

    List<Profissional> findAll();

    Profissional save(Profissional profissional);

    void deleteByCpf(String cpf);

    @Query("SELECT prof FROM Profissional prof WHERE prof.area = :area")
    List<Profissional> getWithFilter_a(@Param("area") String area);

    @Query("SELECT prof FROM Profissional prof WHERE prof.especialidade = :especialidade")
    List<Profissional> getWithFilter_e(@Param("especialidade") String especialidade);

    @Query("SELECT prof FROM Profissional prof WHERE prof.especialidade = :especialidade AND prof.area = :area")
    List<Profissional> getWithFilter_a_e(@Param("especialidade") String especialidade,
            @Param("area") String area);

}