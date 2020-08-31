package com.techelevator.example;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class FormExample {

    @NotBlank(message="Name is required")
    private String name;

    @Min(value=1, message="Minimum experience is 1 year.")
    @Max(value=5, message="Maximum experience is 5 years.")
    private int experience;

    private boolean knowHtml;

    private boolean knowCss;

    private boolean knowJavascript;

    @NotBlank(message="Skills are required")
    private String skills;

    @NotNull(message="Must choose a start date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isKnowHtml() {
        return knowHtml;
    }

    public void setKnowHtml(boolean knowHtml) {
        this.knowHtml = knowHtml;
    }

    public boolean isKnowCss() {
        return knowCss;
    }

    public void setKnowCss(boolean knowCss) {
        this.knowCss = knowCss;
    }

    public boolean isKnowJavascript() {
        return knowJavascript;
    }

    public void setKnowJavascript(boolean knowJavascript) {
        this.knowJavascript = knowJavascript;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
