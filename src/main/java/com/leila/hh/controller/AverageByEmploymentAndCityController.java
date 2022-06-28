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

import static com.leila.hh.utils.Data.EMPTY_STRING_VALUE;

@Controller
@RequestMapping("/v1")
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class AverageByEmploymentAndCityController {
    private final ObjectMapper objectMapper;
    private final EmploymentService employmentService;
    private final Data data;

    @GetMapping({"/averageAgeByEmploymentAndCity"})
    public ModelAndView averageAgeByEmploymentAndCity() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("averageAgeByEmploymentAndCity");
        modelAndView.addObject("employmentRequest", new EmploymentDto());
        modelAndView.addObject("employments", data.getEmployments());
        return modelAndView;
    }

    @PostMapping("/averageAgeByEmploymentAndCity")
    public String averageAgeByEmploymentAndCity(@ModelAttribute("employmentRequest") EmploymentDto employmentRequest, Model model) {
        String result;
        try {
            if (employmentRequest.getCity() == null)
                employmentRequest.setCity(EMPTY_STRING_VALUE);
            result = employmentService.averageAgeByEmploymentAndCity(employmentRequest.getSelectedEmployment(), employmentRequest.getCity());
        } catch (Exception e) {
            log.error(e.getMessage());
            result = EMPTY_STRING_VALUE;
        }

        model.addAttribute("result", result);
        return "averageAgeByEmploymentAndCity";
    }

}
