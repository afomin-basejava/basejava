package com.urise.webapp.model;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrganizationSection extends Section{
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MM/yyyy");
    private List<Organization> organizations;
    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }
    @Override
    public void printSection(SectionType sectionType, Section section) {
//        D:/basejava/src/com/urise/webapp/model/GriroryKislinResume.html
        System.out.println(sectionType.getTitle());
        System.out.println();
        for (Organization organization : organizations) {
            String orgName = organization.getName();
            String url = organization.getUrl();
            if (!url.equals("")) {
                url = "<a href = " + url + ">" + orgName + "</a>";
            } else {
                url = orgName;
            }
            System.out.println(url);
            for (Organization.Job job : organization.getJobs()) {
                System.out.println(
                        job.getStartDate().format(DTF) + " - " +
                        (job.getFinishDate().toEpochDay() == 0 ? "Сeйчас" : job.getFinishDate().format(DTF)) + "   " +
                        job.getJobName() +
                        (job.getJobDescription().equals("") ? "\r" :  "\n" + job.getJobDescription())
                );
            }
            System.out.println();
        }
        printSectionDelimeter();
    }
}
