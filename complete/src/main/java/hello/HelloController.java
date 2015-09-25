package hello;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class HelloController {
	String remoteResponse;
	
    @RequestMapping("/test")
    public String index() {
        return "Greetings from Asbury Park!";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public  String generateReport(
            HttpServletRequest request, 
            HttpServletResponse response) {

    	return "---remote Address:>"+request.getRemoteAddr() +
    			"---remote Host:>"+request.getRemoteHost() +
    			"---context:>"+request.getContextPath();
        // ...
        // Here you can use the request and response objects like:
        // response.setContentType("application/pdf");
        // response.getOutputStream().write(...);

    }
    
    
    
    @RequestMapping(value = "/reportoutbound/{url}", method = RequestMethod.GET)
    public String  generateReport3(
            @PathVariable("url") String url, 
            HttpServletRequest request, 
            HttpServletResponse response) {

    	
    	try {
			remoteResponse = HttpURLConnectionExample.call("http://"+url+"/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return remoteResponse;
        // ...
        // Here you can use the request and response objects like:
        // response.setContentType("application/pdf");
        // response.getOutputStream().write(...);

    }
}
