package ui.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DomainException;
import domain.db.Woordenlijst;
import domain.model.Woord;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Woordenlijst lijst = new Woordenlijst(false);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String command = "home";
    	if (request.getParameter("command") != null) {
    		command = request.getParameter("command");
    	}
    	String destination = "";
    	switch(command) {
    		case "overview":
    			destination = overview(request, response);
    			break;
    		case "add":
    			destination = add(request, response);
    			break;
    		case "download":
    			destination = download(request, response);
    		default:
    			destination = home(request, response);
    			break;
    	}
    	
    	RequestDispatcher view=request.getRequestDispatcher(destination);
		view.forward(request, response);	
    	}
	
	private String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filename = "hangman.txt";
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		PrintWriter out = response.getWriter();
		for (Woord woord : this.lijst.getAll()) {
			out.println(woord.getWoord());
		}
		out.close();
		return "overzicht.jsp";
	}
	
    private String overview (HttpServletRequest request, HttpServletResponse response) {
    	String filter = "";
    	if (request.getParameter("filter") != null) {
    		filter = request.getParameter("filter");
    	}
    	switch(filter) {
    		case "expert":
    			request.setAttribute("lijst", this.lijst.getLijstFilterExpert());
    			break;
    		case "beginner":
    			request.setAttribute("lijst", this.lijst.getLijstFilterBeginner());
    			break;
    		default:
    			request.setAttribute("lijst", this.lijst);
    			break;
    	}
    	return "overzicht.jsp";
    }
    
    private String home (HttpServletRequest request, HttpServletResponse response) {
    	request.setAttribute("lijst", this.lijst);
    	return "index.jsp";
    }

	
    private String add (HttpServletRequest request, HttpServletResponse response) {
    	String woordP = request.getParameter("woord").toString();
		String niveauP = request.getParameter("niveau").toString().toLowerCase();
		if (niveauP.equals("null")) niveauP = null;
		if (woordP == null || woordP.trim().isEmpty())  {
			return "formulier.jsp";
		}
			try {this.lijst.voegWoordToe(new Woord(woordP, niveauP));
			} catch (DomainException e) {
				System.out.println(e.getMessage());
				return "formulier.jsp";
			}
			request.setAttribute("lijst", this.lijst);
			return "overzicht.jsp";
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


}
