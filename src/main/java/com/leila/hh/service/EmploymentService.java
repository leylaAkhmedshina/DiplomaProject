package com.leila.hh.service;

import java.util.Map;

public interface EmploymentService {

    Map<String, String> countByEmploymentAndLivingCity(String selectedEmployment, String employment);

    String countByEmployment(String selectedEmployment);

    String averageAgeByEmploymentAndCity(String selectedEmployment, String city);

    Map<String, String> salaryByEmployment(String selectedEmployment);

    Map<String, String> experienceByEmployment(String selectedEmployment);

    Map<String, String> lastJobByEmploymentAndAgeAndLanguage(String selectedEmployment, String age, String language);
}
