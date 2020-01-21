package be.vdab.bierhuis.controller;

import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.services.BrouwerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("brouwers")
class BrouwersController {
    private final BrouwerService brouwerService;
    private final BierService bierService;

    public BrouwersController(BrouwerService brouwerService, BierService bierService) {
        this.brouwerService = brouwerService;
        this.bierService = bierService;
    }

    @GetMapping
    public ModelAndView toonBrouwers() {
        return new ModelAndView("brouwers").addObject("brouwers", brouwerService.findAll());
    }
    @GetMapping("{id}")
    public ModelAndView toonBierenVanBrouwer(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("bieren");
        if(brouwerService.findById(id).isPresent()) {
            modelAndView.addObject("brouwer", brouwerService.findById(id).get());
        }
        return modelAndView.addObject("bieren", bierService.findByBrouwer(id));
    }
}
