package be.vdab.bierhuis.controller;

import be.vdab.bierhuis.services.BierService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
class IndexController implements ErrorController {
    private final BierService bierService;
    private final static String PATH = "/error";

    public IndexController(BierService bierService) {
        this.bierService = bierService;
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index").addObject("bieren", bierService.findAantalBieren());
    }
    @Override
    @RequestMapping("/error")
    public String getErrorPath() {
        return "fout";
    }
}
