package com.urise.webapp.model;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrganizationSection extends Section{
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
    private List<Organization> organizations;

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public void printSection(SectionType sectionType, Section section) {

        System.out.println(sectionType.getTitle());
        System.out.println();
        for (Organization organization : organizations) {
            System.out.println(organization.getName());
            for (Organization.Job job : organization.getJobs()) {
                System.out.println(
                        job.getStartDate().format(dtf) + " - " +
                        (job.getFinishDate().toEpochDay() == 0 ? "Сeйчас" : job.getFinishDate().format(dtf)) + "   " +
                        job.getJobName() +
                        (job.getJobDescription().equals("") ? "\r" :  "\n" + job.getJobDescription())
                );
            }
            System.out.println();
        }
        printSectionDelimeter();
    }
}
