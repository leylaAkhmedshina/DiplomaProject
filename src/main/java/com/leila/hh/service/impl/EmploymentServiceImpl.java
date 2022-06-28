package com.leila.hh.service.impl;

import com.leila.hh.repo.EmploymentRepository;
import com.leila.hh.service.EmploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmploymentServiceImpl implements EmploymentService {
    private final EmploymentRepository employmentRepository;

    @Override
    public Map<String, String> countByEmploymentAndLivingCity(String selectedEmployment, String city) {
        if (city.isEmpty()) {
            List<Object[]> resultFromDb = employmentRepository.countByEmploymentAndWithoutCity(selectedEmployment);
            Map<String, String> result = new HashMap<>();
            for (Object[] ob : resultFromDb) {
                result.put((String) ob[0], (BigInteger) ob[1] + "");
            }
            return result;
        }
        employmentRepository.countByEmploymentAndLivingCity(selectedEmployment, city);
        List<Object[]> resultFromDb = employmentRepository.countByEmploymentAndLivingCity(selectedEmployment, city);
        Map<String, String> result = new HashMap<>();
        for (Object[] ob : resultFromDb) {
            result.put((String) ob[0], (BigInteger) ob[1] + "");
        }
        return result;
    }

    @Override
    public String countByEmployment(String selectedEmployment) {
        return employmentRepository.countByEmployment(selectedEmployment);
    }

    @Override
    public String averageAgeByEmploymentAndCity(String selectedEmployment, String city) {
        if (city.isEmpty()) {
            return employmentRepository.averageAgeByEmploymentWithoutCity(selectedEmployment);
        }
        return employmentRepository.averageAgeByEmploymentAndCity(selectedEmployment, city);
    }

    @Override
    public Map<String, String> salaryByEmployment(String selectedEmployment) {
        Map<BigInteger, BigInteger> salDiap = Map.of(
                BigInteger.valueOf(1L), BigInteger.valueOf(30000L),
                BigInteger.valueOf(30000L), BigInteger.valueOf(50000L),
                BigInteger.valueOf(50000L), BigInteger.valueOf(80000L),
                BigInteger.valueOf(80000L), BigInteger.valueOf(1000000L));
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<BigInteger, BigInteger> value : salDiap.entrySet()) {
            String vDb = employmentRepository.salaryByEmployment(selectedEmployment, value.getKey(), value.getValue());
            result.put(value.getKey() + "-" + value.getValue(), vDb);
        }
        return result;
    }

    @Override
    public Map<String, String> experienceByEmployment(String selectedEmployment) {
        Map<String, String> expDiap = Map.of("0", "3", "3", "6", "6", "");
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> value : expDiap.entrySet()) {
            result.put(value.getKey() + "-" + value.getValue()
                    , employmentRepository.experienceByEmployment(selectedEmployment, value.getKey(), value.getValue()));
        }
        return result;
    }

    @Override
    public Map<String, String> lastJobByEmploymentAndAgeAndLanguage(String selectedEmployment, String age, String language) {
        Map<String, String> result = new HashMap<>();
        List<Object[]> resultFromDb;
        if (age == null && language == null) {
            resultFromDb = employmentRepository.lastJobByEmploymentWithoutAgeAndLanguage(selectedEmployment);
            for (Object[] ob : resultFromDb) {
                result.put((String) ob[0], (BigInteger) ob[1] + "");
            }
        } else if (age == null) {
            resultFromDb = employmentRepository.lastJobByEmploymentWithoutAge(selectedEmployment, like(language));
            for (Object[] ob : resultFromDb) {
                result.put((String) ob[0], (BigInteger) ob[1] + "");
            }
        } else if (language == null) {
            resultFromDb = employmentRepository.lastJobByEmploymentWithoutAge(selectedEmployment, like(language));
            for (Object[] ob : resultFromDb) {
                result.put((String) ob[0], (BigInteger) ob[1] + "");
            }
        } else {
            resultFromDb = employmentRepository.lastJobByEmploymentAndAgeAndLanguage(selectedEmployment, age, like(language));
            for (Object[] ob : resultFromDb) {
                result.put((String) ob[0], (BigInteger) ob[1] + "");
            }
        }
        return result;
    }


    private String like(String value) {
        return "%" + value + "%";
    }

}
