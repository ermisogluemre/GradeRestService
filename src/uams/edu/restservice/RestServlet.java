package uams.edu.restservice;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coh.cabig.calaegs.control.LabGradingException;
import coh.cabig.calaegs.control.LabGradingFacade;


public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RestServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().print("The get was called");
	}

/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		response.getWriter().print("The post was called");
		
		// TODO Auto-generated method stub
		int i = 0;
		StringBuffer message = new StringBuffer();
		while( (i = request.getInputStream().read()) != -1) {
			message.append((char)i);
		}
		
		//System.out.println(message.toString());
		
		LabGradingFacade labGradingFacade = new LabGradingFacade();
		String responseString = "Is this working.";
		try {
			responseString = labGradingFacade.grade(message.toString());
		} catch (LabGradingException e) {
			e.printStackTrace();
		}
		
		//response.getWriter().print(responseString);
		//response.getOutputStream().print(responseString);
		
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
        
        writer.write(responseString);
        writer.flush();
        writer.close();

	}
*/	
	private static String message = "Error during Servlet processing";
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
    		int i = 0;
    		StringBuffer message = new StringBuffer();
    		while( (i = req.getInputStream().read()) != -1) {
    			message.append((char)i);
    		}
    		//String inString = new String(message.toString());
    		String inString = message.toString();
    		//System.out.println(inString);
               		
    		LabGradingFacade labGradingFacade = new LabGradingFacade();
    		
    		String responseString = "";
    		
    		try {
    			responseString = labGradingFacade.grade(inString);
    		} catch (LabGradingException e) {
    			System.out.println("ERROR:" + e.getMessage());
    		} catch (Exception e) {
    			System.out.println("ERROR: " + e.getMessage());
    		}
    		
    		//System.out.println("Response:" + responseString);
            
            // set the response code and write the response data
            resp.setStatus(HttpServletResponse.SC_OK);
            OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream());
            
            writer.write(responseString);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            try{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().print(e.getMessage());
                resp.getWriter().close();
            } catch (IOException ioe) {
            }
        }
        
    }  

}
