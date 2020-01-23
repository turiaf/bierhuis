package be.vdab.bierhuis.controller;

import be.vdab.bierhuis.sessions.StateMandje;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
class MyControllerAdvice {
    private final StateMandje stateMandje;

    MyControllerAdvice(StateMandje stateMandje) {
        this.stateMandje = stateMandje;
    }
    @ModelAttribute
    void  extraDataToevoegenAanModel(Model model) {
        model.addAttribute(stateMandje);
    }
}
