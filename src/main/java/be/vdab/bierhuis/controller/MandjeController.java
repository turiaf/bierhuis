package be.vdab.bierhuis.controller;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.forms.BestelLijn;
import be.vdab.bierhuis.services.BestelBonService;
import be.vdab.bierhuis.services.BestelbonlijnService;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import be.vdab.bierhuis.sessions.StateMandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final BierService bierService;
    private final BestelbonlijnService bestelbonlijnService;
    private final StateMandje stateMandje;

    public MandjeController(Mandje mandje, BierService bierService, BestelbonlijnService bestelbonlijnService, StateMandje stateMandje) {
        this.mandje = mandje;
        this.bierService = bierService;
        this.bestelbonlijnService = bestelbonlijnService;
        this.stateMandje = stateMandje;
    }

    //     private BigDecimal totaal = BigDecimal.ZERO;
    @GetMapping
    public ModelAndView toonMandje() {
        ModelAndView modelAndView = new ModelAndView("mandje");
        if (mandje.isGevuld()) {
            List<BestelLijn> mandjeList = new ArrayList<>();
            mandje.getBieren().entrySet().stream().forEach(entry -> {
                bierService.findById(entry.getKey()).ifPresent(bier -> {
                    BigDecimal tebetalen = bier.teBetalen(entry.getValue());
                    mandjeList.add(new BestelLijn(bier, entry.getValue(), tebetalen));
                });
            });
            modelAndView.addObject("mandje", mandjeList);
        }
        modelAndView.addObject("totaal", mandje.getTotaal())
        .addObject(new Bestelbon(0, null, null, null, null, null));
        return modelAndView;
    }
    @PostMapping("bevestigen")
    public ModelAndView bevestigen(@Valid Bestelbon bestelbon, Errors errors, RedirectAttributes redirect) {
        if(errors.hasErrors()) {
            return new ModelAndView("mandje");
        }
//        ModelAndView modelAndView = new ModelAndView("bevestigd");
        long idBon = bestelbonlijnService.bestelbonBevestigen(mandje.getBieren(), bestelbon);
        mandje.delete();
        stateMandje.setGevuld(false);
//        modelAndView.addObject("bestelBon", idBon);
        redirect.addAttribute("toegevoegd", idBon);
        return new ModelAndView("redirect:/mandje/bestelBon");
    }
    @GetMapping("bestelBon")
    public ModelAndView toonBevestigen() {
        return new ModelAndView("bestelBon");
    }
}
