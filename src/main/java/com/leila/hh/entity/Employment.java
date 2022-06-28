package com.leila.hh.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "employment")
public class Employment {
    @Id
    @Column
    @GeneratedValue
    private Long id;
    @Column(name = "vacancy")
    private Date vacancy;
    @Column(name = "surname")
    private Long surname;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "year")
    private Long year;
    @Column(name = "money")
    private Long money;
    @Column(name = "experience")
    private String experience;
    @Column(name = "last_job_position")
    private Long last_job_position;
    @Column(name = "lastjob")
    private String lastjob;
    @Column(name = "last_job_date_start")
    private Long last_job_date_start;
    @Column(name = "last_job_date_finish")
    private Long last_job_date_finish;
    @Column(name = "profession")
    private String profession;
    @Column(name = "citizenship")
    private Long citizenship;
    @Column(name = "region")
    private String region;
    @Column(name = "education")
    private Long education;
    @Column(name = "language")
    private Long language;
    @Column(name = "updating")
    private String updating;
    @Column(name = "resume_url")
    private Long resume_url;
    @Column(name = "id_from_hh")
    private String id_from_hh;
    @Column(name = "img_path")
    private Long img_path;
    @Column(name = "save_date")
    private Long save_date;
    @Column(name = "found")
    private String found;
    @Column(name = "b_date")
    private Long b_date;
}