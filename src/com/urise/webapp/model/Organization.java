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
        this(name, "", jobs);
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
                getUrl().equals(that.getUrl()) &&
                Objects.equals(getJobs(), that.getJobs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUrl(), getJobs());
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Job implements Serializable {

        private String jobName;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate finishDate;
        private String jobDescription;

        public Job() {}
        public Job(String jobName, LocalDate startDate, LocalDate finishDate, String jobDescription) {
            Objects.requireNonNull(jobName, "jobName must be no null");
            Objects.requireNonNull(startDate, "startDate must be no null");
            Objects.requireNonNull(finishDate, "finishDate must be no null");
            this.jobName = jobName;
            this.startDate = startDate;
            this.finishDate = finishDate;
            this.jobDescription = jobDescription;
        }

        public String getJobName() {
            return jobName;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getFinishDate() {
            return finishDate;
        }

        public String getJobDescription() {
            return jobDescription;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Job job = (Job) o;
            return jobName.equals(job.jobName) &&
                    startDate.equals(job.startDate) &&
                    finishDate.equals(job.finishDate) &&
                    jobDescription.equals(job.jobDescription);
        }

        @Override
        public int hashCode() {
            return Objects.hash(jobName, startDate, finishDate, jobDescription);
        }

        @Override
        public String toString() {
            return "Job{" +
                    "jobName='" + jobName + '\'' +
                    ", startDate=" + startDate +
                    ", finishDate=" + finishDate +
                    ", jobDescription='" + jobDescription + '\'' +
                    '}';
        }
    }
}
