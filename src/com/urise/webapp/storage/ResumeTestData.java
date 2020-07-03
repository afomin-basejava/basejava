package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.urise.webapp.model.ContactType.*;

public class ResumeTestData {
//    interface ResumeCreator<T, U, R> {
//        R createResume(T uuid, U fullName);
//    }
    private static List<Organization> organizationList = new ArrayList<>();
    public static void main(String[] args) {
        Resume resume = new ResumeTestData().createResumeWithSections("uuid1", "Григорий Кислин");
        printResume(resume);
    }

    public Resume createResumeWithSections(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContact(RESIDENCE, "Saint-Petersburg");
        resume.setContact(PHONE, "+7(921) 855-0482");
        resume.setContact(EMAIL, "mailto:gkislin@yandex.ru");
        resume.setContact(SKYPE, "skype:grigory.kislin");
        resume.setContact(LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContact(GITHUB, "https://github.com/gkislin");
        resume.setContact(STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.setContact(HOMEPAGE, "http://gkislin.ru/");
//----------------------------------------------------------
        AbstractSection section = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSection(SectionType.OBJECTIVE, section);
        section = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSection(SectionType.PERSONAL, section);
//----------------------------------------------------------
        List<String> stringList = new ArrayList<>();
        stringList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        stringList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        stringList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        stringList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        stringList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        stringList.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        section = new ListSection(stringList);
        resume.setSection(SectionType.ACHIEVEMENT, section);
//----------------------------------------------------------
        stringList = new ArrayList<>();
        stringList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        stringList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        stringList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        stringList.add("MySQL, SQLite, MS SQL, HSQLDB");
        stringList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        stringList.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        stringList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        stringList.add("Python: Django.");
        stringList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        stringList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        stringList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        stringList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        stringList.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        stringList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        stringList.add("Родной русский, английский \"upper intermediate\"");
        section = new ListSection(stringList);
        resume.setSection(SectionType.QUALIFICATIONS, section);
//----------------------------------------------------------
        organizationList = new ArrayList<>();
        prepareOrganizationList(new String[]{"2013-10"}, new String[]{"0"}, new String[]{"Автор проекта"},
                new String[] {"Создание, организация и проведение Java онлайн проектов и стажировок"},
                "Java Online Projects", "http://javaops.ru/");
        prepareOrganizationList(new String[]{"2014-10"}, new String[]{"2016-01"}, new String[]{"Старший разработчик (backend)"},
                new String[] {"Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."},
                "Wrike", "https://www.wrike.com");
        prepareOrganizationList(new String[]{"2012-04"}, new String[]{"2014-10"}, new String[]{"Java архитектор"},
                new String[] {"Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."},
                "RIT Center", "");
        prepareOrganizationList(new String[]{"2010-12"}, new String[]{"2012-04"}, new String[]{"Ведущий программист"},
                new String[] {"Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."},
                "Luxoft (Deutsche Bank)", "http://www.luxoft.ru/");
        prepareOrganizationList(new String[]{"2008-06"}, new String[]{"2010-12"}, new String[]{"Ведущий специалист"},
                new String[] {"Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"},
                "Yota", "https://www.yota.ru/");
        prepareOrganizationList(new String[]{"2007-03"}, new String[]{"2008-06"}, new String[]{"Разработчик ПО"},
                new String[] {"Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."},
                "Enkata", "http://enkata.com/");
        prepareOrganizationList(new String[]{"2005-01", "2006-01"}, new String[]{"2007-02", "2016-12"}, new String[]{"Разработчик ПО", "Разработчик ПО"},
                new String[] {"Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).",
                              "Та же работа в разные периоды: Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."},
                "Siemens AG","https://www.siemens.com/ru/ru/home.html");
        prepareOrganizationList(new String[]{"1997-09"}, new String[]{"2005-01"}, new String[]{"Инженер по аппаратному и программному тестированию"},
                new String[] {"Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."},
                "Alcatel", "http://www.alcatel.ru/");
        section = new OrganizationSection(organizationList);
        resume.setSection(SectionType.EXPIRIENCE, section);
//----------------------------------------------------------
        organizationList = new ArrayList<>();
        prepareOrganizationList(new String[]{"1993-09", "1987-09"}, new String[]{"1997-09", "1993-09"},
                new String[]{"Аспирантура (программист С, С++)", "Инженер (программист Fortran, C)"},
                new String[] {"jobDescription Аспирантура (программист С, С++)", "jobDescription Инженер (программист Fortran, C)"},
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru");
        prepareOrganizationList(new String[]{"1984-09",}, new String[]{"1987-06",},
                new String[]{"Закончил с отличием"},
                new String[] {"Описание учебы", "лорлорро"}, //  <- second param
                "Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru");
        section = new OrganizationSection(organizationList);
        resume.setSection(SectionType.EDUCATION, section);
        return resume;
    }
//----------------------------------------------------------
    private static void prepareOrganizationList(String[] startDate, String[] endDate, String[] jobName,
                                                String[] description, String organ, String url) {
        DateTimeFormatter dtfBuilder = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, Long.parseLong("01"))
                .toFormatter();
        List<Organization.Job> jobList = new ArrayList<>();
        for (int i = 0; i < jobName.length; i++) {
            LocalDate start = LocalDate.parse(startDate[i], dtfBuilder);
            LocalDate end = LocalDate.ofEpochDay(0);
            if (!endDate[i].equals("0")) {
                end = LocalDate.parse(endDate[i], dtfBuilder);
            }
            jobList.add(new Organization.Job(jobName[i], start, end, description[i]));
        }
        Organization organization = new Organization(organ, url, jobList);
        organizationList.add(organization);
    }
//----------------------------------------------------------
    private static void printContacts(Resume resume) {
        System.out.println(resume.getFullName() + "\n");
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    private static void printResume(Resume resume) {
        printContacts(resume);
        for (SectionType sectionType : SectionType.values()) {
            AbstractSection section1 = resume.getSection(sectionType);
            try {
                System.out.println(sectionType.getTitle());
                System.out.println(section1);
            } catch (RuntimeException rte) {
                System.out.println("wrong section " + sectionType + " " + section1);
            }
        }
    }

}
