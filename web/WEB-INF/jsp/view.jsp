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
    <meta http-eqiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>
        ${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit">Edit</a>
    </h2>
    <p>
        <c:forEach items="${resume.contacts}" var="contactTypeEntry" >
            <jsp:useBean id="contactTypeEntry" type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>" />
            <%=contactTypeEntry.getKey().getTitle()%> : <%=contactTypeEntry.getValue()%>  <br/>
        </c:forEach>
    </p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
