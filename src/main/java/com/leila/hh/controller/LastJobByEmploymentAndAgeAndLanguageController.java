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

@Controller
@RequestMapping("/v1")
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class LastJobByEmploymentAndAgeAndLanguageController {
    private final ObjectMapper objectMapper;
    private final Data data;
    private final EmploymentService employmentService;

    @GetMapping({"/lastJobByEmploymentAndAgeAndLanguage"})
    public ModelAndView lastJobByEmploymentAndAgeAndLanguage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lastJobByEmploymentAndAgeAndLanguage");
        modelAndView.addObject("employmentRequest", new EmploymentDto());
        modelAndView.addObject("employments", data.getEmployments());
        return modelAndView;
    }

    @PostMapping("/lastJobByEmploymentAndAgeAndLanguage")
    public String lastJobByEmploymentAndAgeAndLanguage(@ModelAttribute("employmentRequest") EmploymentDto employmentRequest, Model model) {
        Map<String, String> result;
        try {
            result = employmentService.lastJobByEmploymentAndAgeAndLanguage(employmentRequest.getSelectedEmployment(), employmentRequest.getAge(), employmentRequest.getLanguage());
        } catch (Exception e) {
            result = new HashMap<>();
            log.error(e.getMessage());
        }
        model.addAttribute("employmentRequest", new EmploymentDto());
        model.addAttribute("result", result);
        return "lastJobByEmploymentAndAgeAndLanguage";
    }

}
