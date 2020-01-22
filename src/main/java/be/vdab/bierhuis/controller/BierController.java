package be.vdab.bierhuis.controller;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.forms.BierForm;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("bier")
class BierController {
    private final BierService bierService;
    private final Mandje mandje;

    public BierController(BierService bierService, Mandje mandje) {
        this.bierService = bierService;
        this.mandje = mandje;
    }

    @GetMapping("{id}")
    public ModelAndView toonBier(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("bier");
        Optional<Bier> bier = bierService.findById(id);
        if(bier.isPresent()) {
            modelAndView.addObject("bier", bier.get());
        }
        modelAndView.addObject("bierForm", new BierForm(null, null));
        return modelAndView;
    }

    @PostMapping
    public ModelAndView toevoegen(@Valid BierForm bierForm, Errors errors) {
        if(errors.hasErrors()) {
//            ModelAndView modelAndView = new ModelAndView("bier").addObject("")
            return new ModelAndView("bier");
        }
        bierService.findById(bierForm.getId()).ifPresent(bier -> {
            mandje.add(bierForm.getId(), bierForm.getAantal());
            mandje.addTotaal(bier.teBetalen(bierForm.getAantal()));
        });
        return new ModelAndView("redirect:/mandje");
    }
}
