package ru.korotkov.neo.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import ru.korotkov.neo.models.VacationRequest;


@Controller
public class VacationController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/calculate")
    public String calculateVacation(@Valid VacationRequest vacationRequest, BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            long vacationPay = vacationRequest.calculateVacationPay();

            model.addAttribute("salary", vacationRequest.salary());
            model.addAttribute("vacationDays", vacationRequest.vacationDays());
            model.addAttribute("vacationPay", vacationPay);
        }


        return "calculator";
    }
}


