<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.03.2021
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--https://docs.oracle.com/javaee/5/jstl/1.1/docs/tlddocs/--%>
<%--https://docs.oracle.com/javaee/5/tutorial/doc/p1.html--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-eqiv = "Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
  </head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <section>
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Имя</th>
                <th>Email</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${basejava_Web_exploded}" var = "resume">
                <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
                <tr>
                    <td>
                        <a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName} </a>
                    </td>
                    <td>
                        ${resume.contacts.get(ContactType.EMAIL)}
                    </td>
                    <td>
                        <a href="resume?uuid=${resume.uuid}&action=edit">Edit</a>
                    </td>
                    <td>
                        <a href="resume?uuid=${resume.uuid}&action=delete">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </section>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
