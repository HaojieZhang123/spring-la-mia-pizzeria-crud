package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model, @RequestParam(required = false) String name) {

        List<Pizza> pizzas;

        if (name != null) {
            pizzas = repository.findByNameContainingIgnoreCase(name);
        } else {
            pizzas = repository.findAll();
        }

        if (pizzas.isEmpty()) {
            model.addAttribute("message", "Non ci sono pizze disponibili.");
        } else {
            model.addAttribute("pizzas", pizzas);
        }

        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        Pizza pizza = repository.findById(id).get();

        // .get() will throw an exception if the pizza is not found, not null.
        // will wait for future lessons to handle this.
        // if (pizza == null) {
        // model.addAttribute("message", "Pizza non trovata.");
        // } else {
        // model.addAttribute("pizza", pizza);
        // }

        model.addAttribute("pizza", pizza);

        return "pizzas/show";
    }

}
