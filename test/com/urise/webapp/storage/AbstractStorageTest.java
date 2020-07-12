package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.util.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import static com.urise.webapp.util.DateUtil.NOW;
import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final String RESUME_STORAGE_DIRECTORY = "D:\\basejava\\storage";

    private final Storage storage;

    private static Map<ContactType, String> RESUME_CONTACTS;
    private static Map<SectionType, AbstractSection> RESUME_SECTIONS;

    private static final String UUID_1 = "uuid_1";
    private static final String FULL_NAME_1 = "Grigory Kislin";
    private static final Resume RESUME_GRIGORY_KISLIN;

    private static final String UUID_2 = "uuid_2";
    private static final String FULL_NAME_2 = "FULL_NAME_2";
    private static final Resume RESUME_2;

    private static final String UUID_3 = "uuid_3";
    private static final String FULL_NAME_3 = "FULL_NAME_3";
    private static final Resume RESUME_3;

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
                                                        "Аспирантура (программист С, С++)")
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
        RESUME_CONTACTS.put(ContactType.EMAIL, "mailto:uuid2@yandex.ru");
        RESUME_CONTACTS.put(ContactType.RESIDENCE, "Moscow");
        RESUME_CONTACTS.put(ContactType.PHONE, "+7(495)123-45-67");
        RESUME_3 = ResumeTestData.createResume(UUID_3, FULL_NAME_3, RESUME_CONTACTS);

    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void fillStorage() {
        storage.clear();
        storage.save(RESUME_GRIGORY_KISLIN);
        storage.save(RESUME_2);
    }

    @After
    public void clearStorage() {
//        storage.clear();
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_3);
        assertSize(3);
        assertGet(RESUME_3);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingResume() {
        storage.save(RESUME_GRIGORY_KISLIN);
    }

    @Test
    public void get() {
        assertGet(RESUME_GRIGORY_KISLIN);
        assertGet(RESUME_2);
        storage.save(RESUME_3);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResume() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws NotExistStorageException {
        storage.delete(RESUME_GRIGORY_KISLIN.getUuid());
        assertSize(1);
        storage.delete(RESUME_2.getUuid());
        assertSize(0);
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResume() throws NotExistStorageException {
        storage.delete("dummy");
        assertSize(2);
    }

    @Test
    public void update() {
        Resume resume = ResumeTestData.createResume(UUID_1, "FULL_NAME_1", RESUME_CONTACTS, RESUME_SECTIONS);
        RESUME_CONTACTS.put(ContactType.LINKEDIN, "LINKEDIN");
        storage.update(resume);
        assertGet(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() throws NotExistStorageException {
        storage.update(ResumeTestData.createResume("DUMMY", "FULL_NAME_DUMMY"));
    }

    @Test
    public void getAllSorted() {
        assertEquals(storage.getAllSorted(), Arrays.asList(RESUME_2, RESUME_GRIGORY_KISLIN));
    }

    @Test
    public void size() {
        assertSize(2);
    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

}