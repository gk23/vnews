package net.vxinwen.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vxinwen.db.dao.NewsDao;

/**
 * 同步接口
 * 
 * @author Administrator
 * 
 */
public class GetNewsServlet extends HttpServlet {

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
	 * The doGet method of the servlet. <br>
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 传入tags列表，多项以##分割
		String tags = request.getParameter("tags");
		String ids = request.getParameter("ids");
		String result = "{}";
		PrintWriter out = response.getWriter();
		NewsDao newsDao = null;
		if (tags != null && tags.trim().length() > 0 && ids != null
				&& ids.trim().length() > 0) {
			// 添加最新news，
			String[] tagslist = tags.split("##");
			String[] idslist = ids.split("##");
			if (tagslist.length == idslist.length) {
				long[] longIds= convertStringToLong(idslist);
				newsDao.getLastNewsBatch(longIds, tagslist);
			}
		}
		out.print(result);

	}

	private long[] convertStringToLong(String[] idslist) {
		long[] longIds = new long[idslist.length];
		for(int i=0;i<idslist.length;i++){
			longIds[i]=Long.parseLong(idslist[i]);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
