package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.daoCliente;
import br.ufscar.dc.dsw.dao.daoUser;

import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.User;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.util.Formata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clientes")
public class clienteController {

    @Autowired
    daoCliente daoCliente;

    @GetMapping("/")
    public String index(Model model) {
        return "admin/index";
    }

}