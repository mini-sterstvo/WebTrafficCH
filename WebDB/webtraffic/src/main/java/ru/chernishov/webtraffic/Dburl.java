package ru.chernishov.webtraffic;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by promoscow on 17.07.17.
 */
public class Dburl extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//получаем данные подключения из формы, и записываем в servletcontext. загружает файл createdb.jsp на страницу /createdb

        String URLB = String.valueOf(req.getParameter("url"));
        String LOGDB = String.valueOf(req.getParameter("log"));
        String PASDB = String.valueOf(req.getParameter("pas"));
        String namedb = String.valueOf(req.getParameter("namedb"));
        String nametbOLD = String.valueOf(req.getParameter("nametb"));



        String URLDB = "jdbc:mysql://"+URLB+"?user="+LOGDB+"&password="+PASDB+"&useSSL=false";

        req.getServletContext().setAttribute("URLDB", URLDB );
        req.getServletContext().setAttribute("namedbOLD", namedb );
        req.getServletContext().setAttribute("nametableOLD", nametbOLD );


        req.setAttribute("url", URLDB);

        req.getRequestDispatcher("createdb.jsp").forward(req, resp);
        

    }


}