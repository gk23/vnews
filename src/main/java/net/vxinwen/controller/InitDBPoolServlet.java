package net.vxinwen.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import net.vxinwen.db.DataSourceFactory;

public class InitDBPoolServlet extends HttpServlet {

    @Override
    public void init() throws ServletException{
        super.init();
        System.out.println("初始化数据库连接池");
        DataSourceFactory.init();
    }
}
