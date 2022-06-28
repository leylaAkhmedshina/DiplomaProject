package com.leila.hh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leila.hh.model.EmploymentDto;
import com.leila.hh.service.EmploymentService;
import com.leila.hh.utils.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static com.leila.hh.utils.Data.EMPTY_STRING_VALUE;

@Controller
@RequestMapping("/v1")
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class SalaryByEmploymentController {
    private final ObjectMapper objectMapper;
    private final EmploymentService employmentService;
    private final Data data;

    @GetMapping({"/salaryByEmployment"})
    public ModelAndView salaryByEmployment() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("salaryByEmployment");
        modelAndView.addObject("employmentRequest", new EmploymentDto());
        modelAndView.addObject("employments", data.getEmployments());
        return modelAndView;
    }

    @PostMapping("/salaryByEmployment")
    public String salaryByEmployment(@ModelAttribute("salaryByEmployment") EmploymentDto employmentRequest, Model model) {
        Map<String, String> result;
        try {
            if (employmentRequest.getCity() == null)
                employmentRequest.setCity(EMPTY_STRING_VALUE);
            result = employmentService.salaryByEmployment(employmentRequest.getSelectedEmployment());
        } catch (Exception e) {
            log.error(e.getMessage());
            result = new HashMap<>();
        }

        model.addAttribute("result", result);
        model.addAttribute("employmentRequest", new EmploymentDto());
        return "salaryByEmployment";
    }
}
