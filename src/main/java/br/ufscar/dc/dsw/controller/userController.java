package br.ufscar.dc.dsw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class userController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

}
