package be.vdab.bierhuis.controller;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.forms.MandjeTable;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final BierService bierService;

    public MandjeController(Mandje mandje, BierService bierService) {
        this.mandje = mandje;
        this.bierService = bierService;
    }

    //     private BigDecimal totaal = BigDecimal.ZERO;
    @GetMapping
    public ModelAndView toonMandje() {
        ModelAndView modelAndView = new ModelAndView("mandje");
        if (mandje.isGevuld()) {
            List<MandjeTable> mandjeList = new ArrayList<>();
            mandje.getBieren().entrySet().stream().forEach(entry -> {
                bierService.findById(entry.getKey()).ifPresent(bier -> {
                    BigDecimal tebetalen = bier.teBetalen(entry.getValue());
                    mandjeList.add(new MandjeTable(bier, entry.getValue(), tebetalen));
                });
            });
            modelAndView.addObject("mandje", mandjeList);
        }
        modelAndView.addObject("totaal", mandje.getTotaal())
        .addObject(new Bestelbon(0, null, null, null, null, null));
        return modelAndView;
    }
    @PostMapping("bevestigen")
    public String bevestigen(@Valid Bestelbon bestelbon, Errors errors) {
        if(errors.hasErrors()) {
            return "mandje";
        }

        return "redirect:/";
    }
}
