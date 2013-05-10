package net.vxinwen.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.vxinwen.service.GetNewsService;

/**
 * 同步接口
 * 
 * @author Administrator
 * 
 */
public class GetNewsServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(GetNewsServlet.class);

    /**
     * Constructor of the object.
     */
    public GetNewsServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * 请求格式getNews?ids=101$$36$$321&tags=头条$$体育$$娱乐$$科技$$段子，需要UTF-8编码
     * 
     * This method is called when a form has its tag value method equals to get.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=UTF-8");
        String tags = request.getParameter("tags");
        String ids = request.getParameter("ids");
        logger.debug("params [tags] is [" + tags + "], [ids] is [" + ids + "].");
        String result = "{}";
        PrintWriter out = response.getWriter();
        GetNewsService updateNewsService = new GetNewsService();
        if (tags != null && tags.trim().length() > 0 && ids != null && ids.trim().length() > 0) {
            // 传入tags列表，多项以$$分割
            String[] tagslist = tags.split("\\$\\$");
            String[] idslist = ids.split("\\$\\$");
            if (tagslist.length == idslist.length) {
                logger.debug("tagslist.length is "+tagslist.length);
                long[] longIds = convertStringToLong(idslist);
                //if(isHaveJoke(tagslist))
                    result = updateNewsService.get(longIds, tagslist).toJSONString();
            }
        }
        out.print(result);
    }

    private boolean isHaveJoke(String[] tagslist) {
        for (String tag : tagslist) {
            if (tag.equals("段子"))
                return true;
        }
        return false;
    }

    private long[] convertStringToLong(String[] idslist) {
        long[] longIds = new long[idslist.length];
        for (int i = 0; i < idslist.length; i++) {
            longIds[i] = Long.parseLong(idslist[i]);
        }
        return longIds;
    }

    /**
     * The doPost method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Initialization of the servlet. <br>
     * 
     * @throws ServletException
     *             if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}
