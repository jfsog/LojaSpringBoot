package com.jfsog.MySpringBootApplication.Controllers;

import com.jfsog.MySpringBootApplication.Entidades.Propriedade;
import com.jfsog.MySpringBootApplication.Repositorios.PropriedadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PropriedadeController {
    @Autowired
    public final PropriedadeRepository repository;
    public PropriedadeController(PropriedadeRepository repository) {
        this.repository = repository;
    }
    @RequestMapping(value = "/find/{filtro}", method = RequestMethod.GET)
    public List<Propriedade> findByFiltro(@PathVariable("filtro") String filtro) {
        var l = repository.findByNomeContainingIgnoreCaseOrderByCategoriaAscSubcategoriaAscNomeAsc(
                filtro);
        l.forEach(System.out::println);
        return l;
    }
}
