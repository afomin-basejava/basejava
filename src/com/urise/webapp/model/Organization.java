package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Organization {
    private String name;
    private List<Job> jobs;

    public Organization(String name, List<Job> jobs) {
        this.name = name;
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return name.equals(that.name) &&
                jobs.equals(that.jobs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, jobs);
    }

    public static class Job {
        private String jobName;
        private LocalDate startDate;
        private LocalDate finishDate;
        private String jobDescription;

        public Job(String jobName, LocalDate startDate, LocalDate finishDate, String jobDescription) {
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
