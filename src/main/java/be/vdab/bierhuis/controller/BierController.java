package be.vdab.bierhuis.controller;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.forms.BierForm;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import be.vdab.bierhuis.sessions.StateMandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("bier")
class BierController {
    private final BierService bierService;
    private final Mandje mandje;
    private final StateMandje stateMandje;

    public BierController(BierService bierService, Mandje mandje, StateMandje stateMandje) {
        this.bierService = bierService;
        this.mandje = mandje;
        this.stateMandje = stateMandje;
    }

    @GetMapping("{id}")
    public ModelAndView toonBier(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("bier");
        bierService.findById(id).ifPresent(bier -> {
            modelAndView.addObject("bier", bier);
        });
        modelAndView.addObject("bierForm", new BierForm(null, null));
        return modelAndView;
    }


    @PostMapping
    public ModelAndView toevoegen(@Valid BierForm bierForm, Errors errors) {
        if(errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("bier");
            if(bierForm != null && bierForm.getId() != null) {
                bierService.findById(bierForm.getId()).ifPresent(bier -> {
                    modelAndView.addObject("bier", bier);
                });
            }
            return modelAndView;
        }
        bierService.findById(bierForm.getId()).ifPresent(bier -> {
            mandje.add(bierForm.getId(), bierForm.getAantal());
            mandje.addTotaal(bier.teBetalen(bierForm.getAantal()));
            if(mandje.isGevuld()) {
                stateMandje.setGevuld(true);
            }
        });
        return new ModelAndView("redirect:/mandje");
    }
}
