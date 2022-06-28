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


@Controller
@RequestMapping("/v1")
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class CountByEmploymentController {
    private final EmploymentService employmentService;
    private final ObjectMapper objectMapper;
    private final Data data;

    @GetMapping("/countByEmployment")
    public ModelAndView countByEmployment() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("countByEmployment");
        modelAndView.addObject("employmentRequest", new EmploymentDto());
        modelAndView.addObject("employments", data.getEmployments());
        return modelAndView;
    }

    @PostMapping("/countByEmployment")
    public String countByEmployment(@ModelAttribute("employmentRequest") EmploymentDto employmentRequest, Model model) {
        String result = "";
        try {
            result = employmentService.countByEmployment(employmentRequest.getSelectedEmployment());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        model.addAttribute("result", result);
        return "countByEmployment";
    }

}
