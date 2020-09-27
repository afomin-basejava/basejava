package com.urise.webapp.storage;

import com.urise.webapp.model.*;
import com.urise.webapp.util.DateUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import static com.urise.webapp.util.DateUtil.NOW;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = createResume("uuid1", "Григорий Кислин");
        printResume(resume);
    }

    private static final String UUID_1 = "uuid_1";
    private static final String FULL_NAME_1 = "Grigory Kislin";
    static final Resume RESUME_1;

    private static final String UUID_2 = "uuid_2";
    private static final String FULL_NAME_2 = "FULL_NAME_2";
    static final Resume RESUME_2;

    private static final String UUID_3 = "uuid_3";
    private static final String FULL_NAME_3 = "FULL_NAME_3";
    static final Resume RESUME_3;

    static {
        Map<ContactType, String> RESUME_CONTACTS = new EnumMap<>(ContactType.class);
        Map<SectionType, AbstractSection> RESUME_SECTIONS = new EnumMap<>(SectionType.class);

        RESUME_CONTACTS.put(ContactType.EMAIL, "mailto:gkislin@yandex.ru");
        RESUME_CONTACTS.put(ContactType.RESIDENCE, "Saint-Petersburg");
        RESUME_CONTACTS.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");

        RESUME_SECTIONS.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        RESUME_SECTIONS.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        RESUME_SECTIONS.put(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.")));
        RESUME_SECTIONS.put(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,",
                "MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\"")));
        RESUME_SECTIONS.put(SectionType.EDUCATION, new OrganizationSection(
                        Arrays.asList(
                                new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru",
                                        Arrays.asList(
                                                new Organization.Job("Аспирантура (программист С, С++)",
                                                        LocalDate.parse("1993-09", DateUtil.dateFormatter()),
                                                        LocalDate.parse("1997-09", DateUtil.dateFormatter()),
                                                        null)
                                                ,
                                                new Organization.Job("Инженер (программист Fortran, C)",
                                                        LocalDate.parse("1987-09", DateUtil.dateFormatter()),
                                                        LocalDate.parse("1993-09", DateUtil.dateFormatter()),
                                                        "jobDescription Инженер (программист Fortran, C)")
                                        )
                                ),
                                new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru",
                                        Arrays.asList(
                                                new Organization.Job("Закончил с отличием",
                                                        LocalDate.parse("1987-09", DateUtil.dateFormatter()),
                                                        LocalDate.parse("1993-09", DateUtil.dateFormatter()),
                                                        "nonNullJobDescription")
                                        )
                                )
                        )
                )
        );
        RESUME_SECTIONS.put(SectionType.EXPIRIENCE, new OrganizationSection(
                Arrays.asList(
                        new Organization(
                                "Java Online Projects", null,
                                Arrays.asList(
                                        new Organization.Job(
                                                "Автор проекта",
                                                LocalDate.parse("2013-10", DateUtil.dateFormatter()),
                                                LocalDate.parse(NOW.toString().substring(0, 7), DateUtil.dateFormatter()),
                                                "Создание, организация и проведение Java онлайн проектов и стажировок"
                                        )
                                )
                        )
                )
        ));
        RESUME_1 = ResumeTestData.createResume(UUID_1, FULL_NAME_1/*, RESUME_CONTACTS, RESUME_SECTIONS*/);

        RESUME_CONTACTS = new EnumMap<>(ContactType.class);
        RESUME_SECTIONS = new EnumMap<>(SectionType.class);
        RESUME_CONTACTS.put(ContactType.EMAIL, "mailto:uuid2@yandex.ru");
        RESUME_CONTACTS.put(ContactType.RESIDENCE, "Moscow");
        RESUME_CONTACTS.put(ContactType.PHONE, "+7(495)123-45-67");
        RESUME_SECTIONS.put(SectionType.PERSONAL, new TextSection("Personal data of FULL_NAME_2"));
        RESUME_SECTIONS.put(SectionType.OBJECTIVE, new TextSection("OBJECTIVE data of FULL_NAME_2"));
        RESUME_SECTIONS.put(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(
                "ATCHIEVMENT #1 of FULL_NAME_2",
                "ATCHIEVMENT #2 of FULL_NAME_2")));
        RESUME_SECTIONS.put(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(
                "QUALIFICATIONS",
                "Родной русский, английский \"upper intermediate\"")));
        RESUME_2 = ResumeTestData.createResume(UUID_2, FULL_NAME_2/*, RESUME_CONTACTS, RESUME_SECTIONS*/);

        RESUME_CONTACTS = new EnumMap<>(ContactType.class);
        RESUME_CONTACTS.put(ContactType.EMAIL, "mailto:uuid3@yandex.ru");
        RESUME_CONTACTS.put(ContactType.RESIDENCE, "Moscow");
        RESUME_CONTACTS.put(ContactType.PHONE, "+7(495)123-45-67");
        RESUME_3 = ResumeTestData.createResume(UUID_3, FULL_NAME_3/*, RESUME_CONTACTS*/);

    }

    public static Resume createResume(String uuid, String fullName) {
        return new Resume(uuid, fullName);
    }

    public static Resume createResume(String uuid, String fullName, Map<ContactType, String> contacts) {
        return new Resume(uuid, fullName, contacts);
    }

    public static Resume createResume(String uuid, String fullName, Map<ContactType, String> contacts, Map<SectionType, AbstractSection> sections) {
        return new Resume(uuid, fullName, contacts, sections);
    }

    private static void printContacts(Resume resume) {
        System.out.println(resume.getFullName() + "\n");
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static void printResume(Resume resume) {
        printContacts(resume);
        for (SectionType sectionType : SectionType.values()) {
            AbstractSection section = resume.getSection(sectionType);
            try {
                System.out.println(sectionType);
                System.out.println(section);
            } catch (RuntimeException rte) {
                System.out.println("wrong section " + sectionType + " " + section);
            }
        }
    }
}
