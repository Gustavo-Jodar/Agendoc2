package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.daoCliente;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admins")
public class adminController {

    @Autowired
    daoCliente daoCliente;

    @Autowired
    daoProfissional daoProfissional;

    @GetMapping("/")
    public String index(Model model) {
        return "/admin/index.html";
    }

    @GetMapping("showPaginaAdmin")
    public String showPaginaAdmin(Model model) {
        return "/admin/index.html";
    }

    @GetMapping("/listaClientes")
    public String listaClientes(ModelMap model) {
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes = daoCliente.findAll();
        model.addAttribute("listaClientes", listaClientes);

        return "/admin/listaClientes.html";
    }

    @GetMapping("/listaProfissionais")
    public String listaProfissionais(Model model) {
        List<Profissional> lista_profissionais = daoProfissional.findAll();

        model.addAttribute("listaProfissionais", lista_profissionais);
        return "/admin/listaProfissionais.html";
    }

    /*
     * // função para acessar página de edição de cliente
     * private void apresentaEdicaoCliente(HttpServletRequest request,
     * HttpServletResponse response)
     * throws ServletException, IOException {
     * 
     * String emailCliente = request.getParameter("email");
     * User clienteUser = daoUser.getbyLogin(emailCliente);
     * Cliente cliente = daoCliente.getbyLogin(clienteUser);
     * 
     * System.out.println(cliente.getCpf());
     * RequestDispatcher dispatcher =
     * request.getRequestDispatcher("/admin/editCliente.jsp");
     * request.setAttribute("clienteEdit", cliente);
     * 
     * dispatcher.forward(request, response);
     * }
     * 
     * // função para acessar página de edição de profissional
     * private void apresentaEdicaoProfissioanl(HttpServletRequest request,
     * HttpServletResponse response)
     * throws ServletException, IOException {
     * 
     * String emailProfissional = request.getParameter("email");
     * User profissionalUser = daoUser.getbyLogin(emailProfissional);
     * Profissional profissional = daoProfissional.getbyLogin(profissionalUser);
     * 
     * System.out.println(profissional.getCpf());
     * RequestDispatcher dispatcher =
     * request.getRequestDispatcher("/admin/editProfissional.jsp");
     * request.setAttribute("profissionalEdit", profissional);
     * 
     * dispatcher.forward(request, response);
     * }
     * 
     * // função para acessar página de edição de profissional
     * private void apresentaAdicionarProfissional(HttpServletRequest request,
     * HttpServletResponse response)
     * throws ServletException, IOException {
     * 
     * RequestDispatcher dispatcher =
     * request.getRequestDispatcher("/admin/addProfissional.jsp");
     * dispatcher.forward(request, response);
     * }
     * 
     * // função para acessar página de edição de profissional
     * private void apresentaAdicionarCliente(HttpServletRequest request,
     * HttpServletResponse response)
     * throws ServletException, IOException {
     * 
     * RequestDispatcher dispatcher =
     * request.getRequestDispatcher("/admin/addCliente.jsp");
     * dispatcher.forward(request, response);
     * }
     * 
     * // função para edição de cliente
     * private void editaCliente(HttpServletRequest request, HttpServletResponse
     * response)
     * throws ServletException, IOException, ParseException {
     * request.setCharacterEncoding("UTF-8");
     * String cpf = request.getParameter("cpf");
     * String nome = request.getParameter("nome");
     * String email = request.getParameter("email");
     * String senha = request.getParameter("senha");
     * String telefone = request.getParameter("telefone");
     * String sexo = request.getParameter("sexo");
     * 
     * String startDateStrNascimento = request.getParameter("nascimento");
     * startDateStrNascimento = startDateStrNascimento.replace('/', '-');
     * 
     * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     * 
     * try {
     * Date nascimento = sdf.parse(startDateStrNascimento);
     * 
     * Cliente cliente = new Cliente(cpf, nome, email, senha, telefone, sexo,
     * nascimento);
     * daoCliente.update(cliente);
     * 
     * List<Cliente> listaClientes = daoCliente.getAll();
     * request.setAttribute("listaClientes", listaClientes);
     * 
     * response.sendRedirect("listaClientes");
     * 
     * } catch (RuntimeException | ParseException | IOException e) {
     * Erro erro = new Erro();
     * erro.add(
     * "Operação não sucedida, verifique se seu dados estão corretos!\nOperation failed, please check if your data is correct!"
     * );
     * request.setAttribute("mensagens", erro);
     * RequestDispatcher rd =
     * request.getRequestDispatcher("/admins/apresentaEdicaoCliente");
     * rd.forward(request, response);
     * // throw new ServletException(e);
     * }
     * 
     * }
     * 
     * // função para edição de profissional
     * private void editaProfissional(HttpServletRequest request,
     * HttpServletResponse response)
     * throws ServletException, IOException, ParseException {
     * request.setCharacterEncoding("UTF-8");
     * Formata f = new Formata();
     * 
     * String cpf = request.getParameter("cpf");
     * String nome = request.getParameter("nome");
     * String email = request.getParameter("email");
     * String senha = request.getParameter("senha");
     * String bio = request.getParameter("bio");
     * String especialidade =
     * f.formataString(request.getParameter("especialidade"));
     * String area = f.formataString(request.getParameter("area"));
     * 
     * String startDateStrNascimento = request.getParameter("nascimento");
     * startDateStrNascimento = startDateStrNascimento.replace('/', '-');
     * 
     * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     * 
     * try {
     * Date nascimento = sdf.parse(startDateStrNascimento);
     * 
     * Profissional profissional = new Profissional(cpf, nome, email, senha, bio,
     * area, especialidade, nascimento);
     * daoProfissional.update(profissional);
     * 
     * List<Profissional> listaProfissionais = daoProfissional.getAll();
     * request.setAttribute("listaProfissionaiss", listaProfissionais);
     * 
     * // NAO É NO LISTA QUE É PRA REDIRECIONAAAAAAAAAAAAAAAAAAAR
     * response.sendRedirect("listaProfissionais");
     * 
     * } catch (RuntimeException | ParseException | IOException e) {
     * Erro erro = new Erro();
     * erro.add(
     * "Operação não sucedida, verifique se seu dados estão corretos!\nOperation failed, please check if your data is correct!"
     * );
     * request.setAttribute("mensagens", erro);
     * RequestDispatcher rd =
     * request.getRequestDispatcher("/admins/apresentaEdicaoProfissional");
     * rd.forward(request, response);
     * }
     * }
     * 
     * // função para remoção de cliente
     * private void removerCliente(HttpServletRequest request, HttpServletResponse
     * response)
     * throws ServletException, IOException {
     * String cpfCliente = request.getParameter("cpf");
     * 
     * daoUser.remobeByCpf(cpfCliente);
     * List<Cliente> listaClientes = daoCliente.getAll();
     * 
     * request.setAttribute("listaClientes", listaClientes);
     * 
     * RequestDispatcher dispatcher =
     * request.getRequestDispatcher("/admin/listaClientes.jsp");
     * dispatcher.forward(request, response);
     * }
     * 
     * // função para remoção de profissioanl
     * private void removerProfissional(HttpServletRequest request,
     * HttpServletResponse response)
     * throws ServletException, IOException {
     * String cpfProfissional = request.getParameter("cpf");
     * 
     * daoUser.remobeByCpf(cpfProfissional);
     * List<Profissional> listaProfissionais = daoProfissional.getAll();
     * 
     * request.setAttribute("listaProfissionais", listaProfissionais);
     * 
     * RequestDispatcher dispatcher =
     * request.getRequestDispatcher("/admin/listaProfissionais.jsp");
     * dispatcher.forward(request, response);
     * }
     * 
     */
}
