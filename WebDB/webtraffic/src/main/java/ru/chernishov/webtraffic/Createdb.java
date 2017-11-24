package ru.chernishov.webtraffic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeSet;


public class Createdb extends HttpServlet {


    @Override
    //переход к форме выбора без создания бд
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //вызов списка абонентов и переход к выбору
        String urldb = String.valueOf(getServletContext().getAttribute("URLDB"));
        String namedb = String.valueOf(getServletContext().getAttribute("namedbOLD"));
        String nametableOLD= String.valueOf(getServletContext().getAttribute("nametableOLD"));


        String Abon=selectabon(urldb, namedb, nametableOLD);


        req.setAttribute("abonent", Abon);
        req.getRequestDispatcher("selectfromdb.jsp").forward(req, resp);
    }


    //обработка формы создания бд и таблицы
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {

        //получение имен бд и таблицы, и url
        String urldb = String.valueOf(getServletContext().getAttribute("URLDB"));
        String namedb = String.valueOf(req.getParameter("namedb"));
        String nametableNEW= String.valueOf(req.getParameter("nametable"));


        //вызов методов создания бд и таблицы
        try {
            createdb(namedb, urldb);
            createtable(namedb, nametableNEW, urldb);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //запись имен в servletcontext
        req.getServletContext().setAttribute("nametableNEW", nametableNEW );
        req.getServletContext().setAttribute("namedbNEW", namedb );


        //заполнения списка выбора абонентов
        String Abon=selectabon(urldb, namedb, nametableNEW);
        req.setAttribute("abonent", Abon);



        //загрузка файла selectfromdb.jsp на страницу selectfromdb
        req.getRequestDispatcher("selectfromdb.jsp").forward(req, resp);


    }


    //создание тегов списка абонентов
    public String selectabon(String url, String nameDB, String nametb){
             String Abon="";

             JDBCwork work = new JDBCwork(url);
             TreeSet<String> list = work.selectAbon(nameDB, nametb);

             for(String el: list){
                 Abon= Abon + "<option value=\"" +el+ "\">" +el+ "</option>";

             }

        return Abon;

    }

    //создание бд
    public void createdb(String name, String url) throws SQLException {
        JDBCwork work=new JDBCwork(url);
        work.createschema(name);

    }
    //создание таблицы
    public void createtable(String namedb, String nametb, String url) throws SQLException {
        JDBCwork work=new JDBCwork(url);
        work.createtable(namedb, nametb);


    }

}