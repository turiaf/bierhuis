package be.vdab.bierhuis.controller;

import be.vdab.bierhuis.services.BierService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
class IndexController {
    private final BierService bierService;

    public IndexController(BierService bierService) {
        this.bierService = bierService;
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index").addObject("bieren", bierService.findAantalBieren());
    }
}
