package JSP_Custom_Tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class ErrorTag extends TagSupport {
    private String field;
    
    public void setField(String field){
        this.field = field;
    }
    
  public int doStartTag() throws JspException{
    try{
        JspWriter out = pageContext.getOut();
        if(field == null || field.length() == 0){
            out.print("*");
        }
    }
    catch(IOException ioe){
        ioe.printStackTrace();
    }
    return SKIP_BODY;
  }
}