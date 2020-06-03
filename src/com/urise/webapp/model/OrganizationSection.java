package com.urise.webapp.model;

import java.util.List;

public class OrganizationSection extends Section{
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
                System.out.println
                        (job.getStartDate().getMonthValue() + "/" + job.getStartDate().getYear() + " - " +
                        (job.getFinishDate().toEpochDay() == 0 ? "Сейчас" : job.getFinishDate().getMonthValue()+ "/" + job.getFinishDate().getYear()) + "    " +
                        job.getJobName() +
                        (job.getJobDescription().equals("") ? "\r" :  "\n" + job.getJobDescription()));
            }
        }
        printSectionDelimeter();
    }
}
