package ru.chernishov.webtraffic;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by promoscow on 17.07.17.
 */
public class Selectfromdb extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

       //получение данных

        String URLDB = String.valueOf(getServletContext().getAttribute("URLDB"));
        String namedbOLD = String.valueOf(getServletContext().getAttribute("namedbOLD"));
        String namedbNEW = String.valueOf(getServletContext().getAttribute("namedbNEW"));
        String nametableNEW = String.valueOf(getServletContext().getAttribute("nametableNEW"));
        String nametableOLD = String.valueOf(getServletContext().getAttribute("nametableOLD"));

        String day1 = String.valueOf(req.getParameter("day1"));
        String second1 = String.valueOf(req.getParameter("second1"));
        String day2 = String.valueOf(req.getParameter("day2"));
        String second2 = String.valueOf(req.getParameter("second2"));
        String link = String.valueOf(req.getParameter("link"));
        String abonent = String.valueOf(req.getParameter("abonent"));

        //создание дат из строк формы
        Date date1=dateRef(day1,second1);
        Date date2=dateRef(day2,second2);

       //выбор имени БД и таблицы для работы
        String  nametable=name(nametableOLD, nametableNEW);
        String  namedb = name(namedbOLD, namedbNEW);

        //формирование  запроса в бд
       String messageindb = createquery(namedb, nametable, date1, date2, link, abonent);

        //результат хождения в бд и обработки ответа
       String answer=getanswer(URLDB, messageindb, date1, date2, link);

        req.setAttribute("answer", answer);


        req.getRequestDispatcher("answer.jsp").forward(req, resp);


    }
    //выбор имени
    private String name(String nameOLD, String nameNEW) {
        String name="";
        if(nameOLD=="null"){name=nameNEW;}else {name = nameOLD;}
        return name;
    }

    //преобразование строк из формы в дату
    private Date dateRef(String day, String second) {

        String time;

        //проверка значения поля секунд
        if (Integer.parseInt(second)<10){time=day+":0"+second;}else{time=day+":"+second;}

        SimpleDateFormat formDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = formDateFormat.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }



        return date;

    }

    //формирование запроса в бд
    private String createquery(String namedb, String nametable, Date date1, Date date2, String link, String abonent) {

        // конвертация формата даты в подходящий для mysql
        SimpleDateFormat DBDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateDB1=DBDateFormat.format(date1);
        String dateDB2=DBDateFormat.format(date2);


        String querydb="SELECT " +link+ " FROM " +namedb+"."+nametable+ " WHERE date>='" +dateDB1+ "' AND date<='" +dateDB2+ "' AND abonent='" +abonent+ "'";

        return querydb;
    }




      //отправка запроса, получение ответа
    public String getanswer(String url, String message, Date date1, Date date2, String link){

        //разница дат в секундах
        long diffdate = (date2.getTime()-date1.getTime())/1000; //преобразование миллисекунд в секунды



        JDBCwork work=new JDBCwork(url);
        int trafbyte=work.selectfromdb(message, link);


        long speedbite=(8*trafbyte)/diffdate; //скорость с переводом вбиты

        String answ = "<br>"+ "Разница дат в секундах: "+diffdate+
                "<br>" + "Объем переданного трафика за период в байтах: " +trafbyte+
                "<br>" + "Средняя скорость за период в бит/сек: " +speedbite+
                "<br>" + "Запрос, отправленный в бд: <br>";
        return answ+message;
    }


}