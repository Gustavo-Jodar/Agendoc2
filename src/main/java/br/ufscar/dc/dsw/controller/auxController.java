package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.daoCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.security.UsuarioDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetailsService;

@Controller
@RequestMapping("/aux")
public class auxController {
    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/")
    public String login(Model model, @RequestParam("email") String email, @RequestParam("senha") String senha) {
        // User user = daoUser.findByEmail(email);
        UsuarioDetails user = (UsuarioDetails) userDetailsService.loadUserByUsername(email);

        if (user != null) {

            if (user.getPassword().equals(senha)) {

                String papel = user.getAuthorities().toArray()[0].toString();
                if (papel.replaceAll("\\P{L}+", "").equals("ADMIN"))
                    return "redirect:/admins";
                if (papel.replaceAll("\\P{L}+", "").equals("CLIENTE"))
                    return "redirect:/clientes";
                if (papel.replaceAll("\\P{L}+", "").equals("PROFISSIONAL"))
                    return "redirect:/profissional";
            }
        }

        return "redirect:/users/showProfissionais";
    }

}
