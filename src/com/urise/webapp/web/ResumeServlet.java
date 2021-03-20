package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {
    private static Storage storage; //= Config.getINSTANCE().getSqlStorage();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        storage = Config.getINSTANCE().getSqlStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer writer = response.getWriter();
        writer.write("<html>\n" +
                "<head>\n" +
                "<meta htpp-equiv=\"Content_Type\" content=\"text/html; charset=UTF-8\">\n" +
                "<link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                "<title>Список резюме</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<section>\n" +
                "<table border=\"1\" cellpadding=\"8\" cellspacing=\"0\">\n" +
                "   <tr>\n" +
                "       <th>Имя</th>\n" +
                "       <th>Почта</th>\n" +
                "   </tr>\n"
        );
        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>\n" +
                            "<td><a href=\"resume?uuid=" + resume.getUuid() + "\">" +
                                resume.getFullName() +"</a>" +
                            "</td>\n" +
                            "<td>" + resume.getContacts().get(ContactType.EMAIL) + "</td>\n"+
                         "</tr>\n"
            );

        }
        writer.write(
                "</table>" +
                        "</section>" +
                        "</body>" +
                        "</html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
