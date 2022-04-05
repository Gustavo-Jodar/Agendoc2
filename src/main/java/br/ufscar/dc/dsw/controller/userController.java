package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.daoProfissional;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.User;
import br.ufscar.dc.dsw.util.Formata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class userController {

    @Autowired
    daoProfissional daoProfissional;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

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

    @GetMapping("/verificaUsuarioLogado")
    public String verificaUsuarioLogado(Model model) {

        // tem que verificar se o usuario já está logado antes de fazer isso
        // pra mandar ele pra página correta
        return "redirect:/users/showProfissionais";
    }

    @GetMapping("/showLogin")
    public String apresentaFormLogin(Model model) {
        return "user/login.html";
    }

    @GetMapping("/verificaEstaLogado/{cpf}")
    public String verificaEstaLogado(@PathVariable("cpf") String cpf, Model model) {
        /*
         * // checa se já tem um usuário logado
         * User usuarioLogado = (User)
         * request.getSession().getAttribute("usuarioLogado");
         * 
         * if (usuarioLogado != null) {
         * // se ja tiver logado abre link pra marcar consulta.
         * String cpf_profissional = request.getParameter("cpf");
         * Profissional profissional_escolhido =
         * daoProfissional.getByCpf(cpf_profissional);
         * request.setAttribute("profissionalEscolhido", profissional_escolhido);
         * RequestDispatcher dispatcher =
         * request.getRequestDispatcher("/cliente/appointment.jsp");
         * dispatcher.forward(request, response);
         * } else {
         * RequestDispatcher dispatcher =
         * request.getRequestDispatcher("/user/login.jsp");
         * dispatcher.forward(request, response);
         * }
         */
        return "redirect:/users/showLogin";

    }

}
