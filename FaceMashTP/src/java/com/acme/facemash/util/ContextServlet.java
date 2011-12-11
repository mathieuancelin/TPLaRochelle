package com.acme.facemash.util;

import com.acme.facemash.ApplicationBootstrap;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet trick to get img folder real path and give it to bootstrap
 * 
 * @author mathieuancelin
 */
@WebServlet(name = "ContextServlet", urlPatterns = {"/ContextServletDoNotUse"}, loadOnStartup=1)
public class ContextServlet extends HttpServlet {

    @EJB ApplicationBootstrap bootstrap;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bootstrap.setImgPath(getServletContext().getRealPath("img"));
    }
}
