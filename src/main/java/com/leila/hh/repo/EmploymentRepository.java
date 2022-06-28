package com.leila.hh.repo;

import com.leila.hh.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {

    @Query(value = "SELECT count(*) FROM employments WHERE vacancy = :employment",
            nativeQuery = true)
    String countByEmployment(@Param("employment") String employment);

    @Query(value = "select region ,count(region) from employments d  where vacancy = :employment group by region",
            nativeQuery = true)
    List<Object[]> countByEmploymentAndWithoutCity(@Param("employment") String selectedEmployment);

    @Query(value = "select region ,count(region) from employments d  where vacancy = :employment and region = :city group by region ",
            nativeQuery = true)
    List<Object[]> countByEmploymentAndLivingCity(@Param("employment") String employment, @Param("city") String city);

    @Query(value = "select ROUND(avg(year)) from employments d  where vacancy = :employment and year != 0",
            nativeQuery = true)
    String averageAgeByEmploymentWithoutCity(@Param("employment") String selectedEmployment);

    @Query(value = "select ROUND(avg(year)) from employments d  where region = :city and year != 0 and vacancy = :employment",
            nativeQuery = true)
    String averageAgeByEmploymentAndCity(@Param("employment") String employment, @Param("city") String city);

    @Query(value = "select count(money) id from employments d  where vacancy = :employment and money != 0 and  money > :moneyFrom and money < :moneyTo",
            nativeQuery = true)
    String salaryByEmployment(@Param("employment") String employment, @Param("moneyFrom") BigInteger moneyFrom, @Param("moneyTo") BigInteger moneyTo);

    @Query(value = "select  count(money) from employments d  where vacancy = :employment and experience >= :experienceFrom and experience <= :experienceTo",
            nativeQuery = true)
    String experienceByEmployment(@Param("employment") String employment, @Param("experienceFrom") String experienceFrom, @Param("experienceTo") String experienceTo);

    @Query(value = "select  lastjob, count(lastjob) from employments where vacancy = :employment group by lastjob",
            nativeQuery = true)
    List<Object[]> lastJobByEmploymentWithoutAgeAndLanguage(@Param("employment") String employment);

    @Query(value = "select  lastjob, count(lastjob) from employments where vacancy = :employment and language like :language group by lastjob",
            nativeQuery = true)
    List<Object[]> lastJobByEmploymentWithoutAge(@Param("employment") String employment, @Param("language") String language);

    @Query(value = "select  lastjob, count(lastjob) from employments where vacancy = :employment and year = :year and language like :language group by lastjob",
            nativeQuery = true)
    List<Object[]> lastJobByEmploymentAndAgeAndLanguage(@Param("employment") String employment, @Param("year") String year, @Param("language") String language);

}
