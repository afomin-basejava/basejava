package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String url;
    private List<Job> jobs;

    public Organization() {

    }
    public Organization(String name, List<Job> jobs) {
        this.name = name;
        this.jobs = jobs;
    }

    public Organization(String name, String url, List<Job> jobs) {
        this.name = name;
        this.url = url;
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return getName().equals(that.getName()) &&
                Objects.equals(getUrl(), that.getUrl()) &&
                Objects.equals(getJobs(), that.getJobs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUrl(), getJobs());
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Job implements Serializable {

        private String name;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate finishDate;
        private String description;

        public Job() {
        }

        public Job(String name, LocalDate startDate, LocalDate finishDate, String description) {
            Objects.requireNonNull(name, "Job name must be no null");
            Objects.requireNonNull(startDate, "startDate must be no null");
            Objects.requireNonNull(finishDate, "finishDate must be no null");
            this.name = name;
            this.startDate = startDate;
            this.finishDate = finishDate;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getFinishDate() {
            return finishDate;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Job job = (Job) o;
            return name.equals(job.name) &&
                    startDate.equals(job.startDate) &&
                    finishDate.equals(job.finishDate) &&
                    Objects.equals(description, job.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, startDate, finishDate, description);
        }

        @Override
        public String toString() {
            return "Job{" +
                    "jobName='" + name + '\'' +
                    ", startDate=" + startDate +
                    ", finishDate=" + finishDate +
                    ", jobDescription='" + description + '\'' +
                    '}';
        }
    }
}
