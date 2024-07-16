package com.jfsog.MySpringBootApplication.Controllers;

import com.jfsog.MySpringBootApplication.Entidades.Cliente;
import com.jfsog.MySpringBootApplication.Repositorios.ClienteRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {
    @Autowired
    private final ClienteRepository clienteRepository;
    private final String CLIENTE_URI = "clientes/";
    @GetMapping("/")
    public ModelAndView list() {
        return new ModelAndView(CLIENTE_URI + "list", "clientes", clienteRepository.findAll());
    }
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Cliente cliente) {
        return new ModelAndView(CLIENTE_URI + "view", "cliente", cliente);
    }
    @GetMapping("/novo")
    public String createForm(@ModelAttribute Cliente cliente) {
        return CLIENTE_URI + "form";
    }
    @PostMapping(params = "form")
    public ModelAndView create(
            @Valid Cliente cliente, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView(CLIENTE_URI + "form", "formErrors", result.getAllErrors());
        }
        cliente = this.clienteRepository.save(cliente);
        redirect.addFlashAttribute("globalMessage", "Cliente gravado com sucesso");
        return new ModelAndView("redirect:/%s{cliente.id}".formatted(CLIENTE_URI), "cliente.id",
                                cliente.getId());
    }
    @GetMapping(value = "alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Cliente cliente) {
        return new ModelAndView(CLIENTE_URI + "form", "cliente", cliente);
    }
    @GetMapping(value = "remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirect) {
        clienteRepository.deleteById(id);
        var clientes = clienteRepository.findAll();
        var mv = new ModelAndView(CLIENTE_URI + "list", "clientes", clientes);
        mv.addObject("globalMessage", "Cliente removido com sucesso");
        return mv;
    }
}
