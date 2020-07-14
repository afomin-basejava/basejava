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
    private static Map<ContactType, String> RESUME_CONTACTS;
    private static Map<SectionType, AbstractSection> RESUME_SECTIONS;

    private static final String UUID_1 = "uuid_1";
    private static final String FULL_NAME_1 = "Grigory Kislin";
    static final Resume RESUME_GRIGORY_KISLIN;

    private static final String UUID_2 = "uuid_2";
    private static final String FULL_NAME_2 = "FULL_NAME_2";
    static final Resume RESUME_2;

    private static final String UUID_3 = "uuid_3";
    private static final String FULL_NAME_3 = "FULL_NAME_3";
    static final Resume RESUME_3;

    static {
        RESUME_CONTACTS = new EnumMap<>(ContactType.class);
        RESUME_SECTIONS = new EnumMap<>(SectionType.class);

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
                                                        "")
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
                                                        "Описание учебы")
                                        )
                                )
                        )
                )
        );
        RESUME_SECTIONS.put(SectionType.EXPIRIENCE, new OrganizationSection(
                Arrays.asList(
                        new Organization(
                                "Java Online Projects", "http://javaops.ru/",
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
        RESUME_GRIGORY_KISLIN = ResumeTestData.createResume(UUID_1, FULL_NAME_1, RESUME_CONTACTS, RESUME_SECTIONS);

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
        RESUME_2 = ResumeTestData.createResume(UUID_2, FULL_NAME_2, RESUME_CONTACTS, RESUME_SECTIONS);

        RESUME_CONTACTS = new EnumMap<>(ContactType.class);
        RESUME_SECTIONS = new EnumMap<>(SectionType.class);
        RESUME_CONTACTS.put(ContactType.EMAIL, "mailto:uuid3@yandex.ru");
        RESUME_CONTACTS.put(ContactType.RESIDENCE, "Moscow");
        RESUME_CONTACTS.put(ContactType.PHONE, "+7(495)123-45-67");
        RESUME_3 = ResumeTestData.createResume(UUID_3, FULL_NAME_3, RESUME_CONTACTS);

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
//
//    public Resume createResumeWithSections(String uuid, String fullName) {
//        Resume resume = new Resume(uuid, fullName);
//        resume.setContact(RESIDENCE, "Saint-Petersburg");
//        resume.setContact(PHONE, "+7(921) 855-0482");
//        resume.setContact(EMAIL, "mailto:gkislin@yandex.ru");
//        resume.setContact(SKYPE, "skype:grigory.kislin");
//        resume.setContact(LINKEDIN, "https://www.linkedin.com/in/gkislin");
//        resume.setContact(GITHUB, "https://github.com/gkislin");
//        resume.setContact(STACKOVERFLOW, "https://stackoverflow.com/users/548473");
//        resume.setContact(HOMEPAGE, "http://gkislin.ru/");
////----------------------------------------------------------
//        AbstractSection section = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
//        resume.setSection(SectionType.OBJECTIVE, section);
//        section = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
//        resume.setSection(SectionType.PERSONAL, section);
////----------------------------------------------------------
//        List<String> stringList = new ArrayList<>();
//        stringList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
//        stringList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
//        stringList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
//        stringList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
//        stringList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
//        stringList.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
//        section = new ListSection(stringList);
//        resume.setSection(SectionType.ACHIEVEMENT, section);
////----------------------------------------------------------
//        stringList = new ArrayList<>();
//        stringList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
//        stringList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
//        stringList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
//        stringList.add("MySQL, SQLite, MS SQL, HSQLDB");
//        stringList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
//        stringList.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
//        stringList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
//        stringList.add("Python: Django.");
//        stringList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
//        stringList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
//        stringList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
//        stringList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
//        stringList.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
//        stringList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
//        stringList.add("Родной русский, английский \"upper intermediate\"");
//        section = new ListSection(stringList);
//        resume.setSection(SectionType.QUALIFICATIONS, section);
////----------------------------------------------------------
//        organizationList = new ArrayList<>();
//        prepareOrganizationList(new String[]{"2013-10"}, new String[]{"0"}, new String[]{"Автор проекта"},
//                new String[] {"Создание, организация и проведение Java онлайн проектов и стажировок"},
//                "Java Online Projects", "http://javaops.ru/");
//        prepareOrganizationList(new String[]{"2014-10"}, new String[]{"2016-01"}, new String[]{"Старший разработчик (backend)"},
//                new String[] {"Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."},
//                "Wrike", "https://www.wrike.com");
//        prepareOrganizationList(new String[]{"2012-04"}, new String[]{"2014-10"}, new String[]{"Java архитектор"},
//                new String[] {"Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."},
//                "RIT Center", "");
//        prepareOrganizationList(new String[]{"2010-12"}, new String[]{"2012-04"}, new String[]{"Ведущий программист"},
//                new String[] {"Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."},
//                "Luxoft (Deutsche Bank)", "http://www.luxoft.ru/");
//        prepareOrganizationList(new String[]{"2008-06"}, new String[]{"2010-12"}, new String[]{"Ведущий специалист"},
//                new String[] {"Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"},
//                "Yota", "https://www.yota.ru/");
//        prepareOrganizationList(new String[]{"2007-03"}, new String[]{"2008-06"}, new String[]{"Разработчик ПО"},
//                new String[] {"Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."},
//                "Enkata", "http://enkata.com/");
//        prepareOrganizationList(new String[]{"2005-01", "2006-01"}, new String[]{"2007-02", "2016-12"}, new String[]{"Разработчик ПО", "Разработчик ПО"},
//                new String[] {"Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).",
//                              "Та же работа в разные периоды: Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."},
//                "Siemens AG","https://www.siemens.com/ru/ru/home.html");
//        prepareOrganizationList(new String[]{"1997-09"}, new String[]{"2005-01"}, new String[]{"Инженер по аппаратному и программному тестированию"},
//                new String[] {"Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."},
//                "Alcatel", "http://www.alcatel.ru/");
//        section = new OrganizationSection(organizationList);
//        resume.setSection(SectionType.EXPIRIENCE, section);
////----------------------------------------------------------
//        organizationList = new ArrayList<>();
//        prepareOrganizationList(new String[]{"1993-09", "1987-09"}, new String[]{"1997-09", "1993-09"},
//                new String[]{"Аспирантура (программист С, С++)", "Инженер (программист Fortran, C)"},
//                new String[] {"jobDescription Аспирантура (программист С, С++)", "jobDescription Инженер (программист Fortran, C)"},
//                "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
//                "http://www.ifmo.ru");
//        prepareOrganizationList(new String[]{"1984-09",}, new String[]{"1987-06",},
//                new String[]{"Закончил с отличием"},
//                new String[] {"Описание учебы", "лорлорро"}, //  <- second param
//                "Заочная физико-техническая школа при МФТИ",
//                "http://www.school.mipt.ru");
//        section = new OrganizationSection(organizationList);
//        resume.setSection(SectionType.EDUCATION, section);
//        return resume;
//    }
////----------------------------------------------------------
//    private static void prepareOrganizationList(String[] startDate, String[] endDate, String[] jobName,
//                                                String[] description, String organ, String url) {
//        DateTimeFormatter dtfBuilder = new DateTimeFormatterBuilder()
//                .appendPattern("yyyy-MM")
//                .parseDefaulting(ChronoField.DAY_OF_MONTH, Long.parseLong("01"))
//                .toFormatter();
//        List<Organization.Job> jobList = new ArrayList<>();
//        for (int i = 0; i < jobName.length; i++) {
//            LocalDate start = LocalDate.parse(startDate[i], dtfBuilder);
//            LocalDate end = DateUtil.NOW;
//            if (!endDate[i].equals("0")) {
//                end = LocalDate.parse(endDate[i], dtfBuilder);
//            }
//            jobList.add(new Organization.Job(jobName[i], start, end, description[i]));
//        }
//        Organization organization = new Organization(organ, url, jobList);
//        organizationList.add(organization);
//    }
//----------------------------------------------------------