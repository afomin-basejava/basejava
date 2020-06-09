package com.urise.webapp.model;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MM/yyyy");
    private List<Organization> organizations;

    public OrganizationSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "OrganizationSection must be not null");
        this.organizations = organizations;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (Organization organization : organizations) {
            String orgName = organization.getName();
            String url = organization.getUrl();
            if (!url.equals("")) {
//                url = "<a href = " + url + ">" + orgName + "</a>";
                url = orgName + "  " + url;
            } else {
                url = orgName;
            }
            stringBuilder.append(url).append("\n");
            for (Organization.Job job : organization.getJobs()) {
                stringBuilder
                    .append(job.getStartDate().format(DTF))
                    .append(" - ")
                    .append((job.getFinishDate().toEpochDay() == 0 ? "Сeйчас" : job.getFinishDate().format(DTF)))
                    .append("\t\t")
                    .append((job.getJobDescription().equals("") ? "\r" : "\n" + job.getJobDescription()))
                    .append("\n")
                ;
            }
        }
        return stringBuilder.toString();
    }

}
