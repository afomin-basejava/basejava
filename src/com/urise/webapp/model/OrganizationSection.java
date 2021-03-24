package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)

public class OrganizationSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MM/yyyy");

    private List<Organization> organizations;

    public OrganizationSection() {
    }

    public OrganizationSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "OrganizationSection must be not null");
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationSection)) return false;
        OrganizationSection that = (OrganizationSection) o;
        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

//    @Override
//    public String toString() {
//        return "OrganizationSection{" +
//                "organizations=" + organizations +
//                '}';
//    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(organizations.size()).append("\n");
        for (Organization organization : organizations) {
            String orgName = organization.getName();
            stringBuilder.append(orgName).append("\n");
            String url = organization.getUrl();
            stringBuilder.append(url).append("\n");
            stringBuilder.append(organization.getJobs().size()).append("\n");
            for (Organization.Job job : organization.getJobs()) {
                stringBuilder
                    .append(job.getStartDate())
                    .append("\n")
                    .append(job.getFinishDate())
                    .append("\n")
                    .append(job.getName())
                    .append("\n")
                    .append(((job.getDescription() == null) || job.getDescription().isEmpty()) ? "empty" : job.getDescription())
                    .append("\n")
                ;
            }
        }
        return stringBuilder.toString();
    }

}
