package be.vdab.bierhuis.controller;

import be.vdab.bierhuis.forms.BierForm;
import be.vdab.bierhuis.services.BierService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("bier")
class BierController {
    private final BierService bierService;

    public BierController(BierService bierService) {
        this.bierService = bierService;
    }

    @GetMapping("{id}")
    public ModelAndView toonBier(@PathVariable long id) {
        return new ModelAndView("bier")
                .addObject("bier", bierService.findById(id));
    }

    @PostMapping
    public String toevoegen(@Valid BierForm bierForm, Errors errors) {
        if(errors.hasErrors()) {
            return "bier/1";
        }
        return "redirect:/";
    }
}
