package be.vdab.bierhuis.controller;

import be.vdab.bierhuis.forms.BierForm;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import be.vdab.bierhuis.sessions.StateMandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public ModelAndView toevoegen(@Valid BierForm bierForm, Errors errors, @RequestParam("id") String idN) {
        if(errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("bier");
            try {
                long id = Long.parseLong(idN);
                bierService.findById(id).ifPresent(bier -> {
                    modelAndView.addObject("bier", bier);
                });
            } catch (NumberFormatException ex) {
                return new ModelAndView("fout");
            }
            return modelAndView;
        }
        bierService.findById(bierForm.getId()).ifPresent(bier -> {
            mandje.add(bierForm.getId(), bierForm.getAantal());
            mandje.addTotaal(bier.teBetalen(bierForm.getAantal()));
            stateMandje.setGevuld(true);
        });
        return new ModelAndView("redirect:/mandje");
    }
}
