package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.daoCliente;
import br.ufscar.dc.dsw.dao.daoProfissional;
import br.ufscar.dc.dsw.dao.daoUser;

import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.security.UsuarioDetailsServiceImpl;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.User;
import br.ufscar.dc.dsw.util.Formata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class userController {

    @Autowired
    daoCliente daoCliente;

    @Autowired
    daoProfissional daoProfissional;

    @Autowired
    daoUser daoUser;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/retornaIndex")
    public String retornaIndex(Model model) {
        return "index.html";
    }

    @GetMapping("/showProfissionais")
    public String showProfissionais(Model model, @RequestParam(value = "area", required = false) String area,
            @RequestParam(value = "especialidade", required = false) String especialidade) {

        List<Profissional> lista_profissionais = new ArrayList<>();

        if ((area == null && especialidade == null) || (area == "" && especialidade == "")) {
            lista_profissionais = daoProfissional.findAll();
        } else {
            Formata f = new Formata();
            especialidade = f.formataString(especialidade);
            area = f.formataString(area);
            if (especialidade == null || especialidade == "") {
                lista_profissionais = daoProfissional.getWithFilter_a(area);
            } else if (area == null || area == "") {
                lista_profissionais = daoProfissional.getWithFilter_e(especialidade);
            } else {
                lista_profissionais = daoProfissional.getWithFilter_a_e(especialidade, area);
            }
        }
        model.addAttribute("listaProfissionais", lista_profissionais);
        return "seeProf.html";
    }

    @GetMapping("/showTypes")
    public String showTypes(Model model) {
        return "/user/userType.html";
    }

    @GetMapping("showCadastroCliente")
    public String showCadastroCliente(Model model) {
        return "/cliente/cadastro.html";
    }

    @PostMapping("/salvarCliente")
    public String salvar(Model model, Cliente cliente, BindingResult result) throws ParseException {
        /*
         * if (result.hasErrors()) {
         * return "redirect:/showCadastro";
         * }
         */
        String startDateStrNascimento = model.getAttribute("nascimento").toString();
        startDateStrNascimento = startDateStrNascimento.replace('/', '-');

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date nascimento = sdf.parse(startDateStrNascimento);

        cliente.setNascimento(nascimento);

        System.out.println("nascimento = " + cliente.getNascimento());
        cliente.setPapel("CLIENTE");

        daoCliente.save(cliente);
        return "redirect:/users/showLogin";
    }

    @GetMapping("showCadastroProfissional")
    public String showCadastroProfissional(Model model) {
        return "/profissional/cadastro.jsp";
    }

    @GetMapping("/verificaUsuarioLogado")
    public String verificaUsuarioLogado(Model model) {

        // tem que verificar se o usuario já está logado antes de fazer isso
        // pra mandar ele pra página correta
        return "redirect:/aux/";
    }

    @GetMapping("/showLogin")
    public String apresentaFormLogin(Model model) {
        return "/user/login.html";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam("email") String email, @RequestParam("senha") String senha) {
        // User user = daoUser.findByEmail(email);
        User user = daoUser.findByEmail(email);
        if (user != null) {

            if (user.getSenha().equals(senha)) {
                String papel = user.getPapel();
                if (papel.replaceAll("\\P{L}+", "").equals("ADMIN"))
                    return "redirect:/admins";
                if (papel.replaceAll("\\P{L}+", "").equals("CLIENTE"))
                    return "redirect:/clientes";
                if (papel.replaceAll("\\P{L}+", "").equals("PROFISSIONAL"))
                    return "redirect:/profissional";
            }
        }

        return "/users";
    }

}
