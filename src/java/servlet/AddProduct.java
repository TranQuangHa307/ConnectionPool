package servlet;

import business.Product;
import dao.ProductDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProduct extends HttpServlet {
    
    private ProductDAO productDAO;
    
    public void init(){
        productDAO = new ProductDAO();
    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
         Product product = new Product("Product Code...", "Description...", 123);
         request.setAttribute("data", product);
         String url = "/add_product.jsp";
         RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
         dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        
        String codeMessage = "";
        String descriptionMessage = "";
        String priceMessage = "";
        String url = "";
        
        boolean isNumber = true;
        boolean isOk = true;
        try{
            Double temp  = Double.parseDouble(price);
        }catch(NumberFormatException e){
            isNumber = false;
        }
        
        if(code.length() == 0){
            codeMessage = "Product code cannot be empty";
            url = "/add_product.jsp";
            isOk = false;
        }
        
        if(description.length() == 0){
            descriptionMessage = "Description cannot be empty";
            url = "/add_product.jsp";
            isOk = false;
        }
        
        if(price.length() == 0 || isNumber == false || Double.parseDouble(price) == 0){
            priceMessage = "Price must be number and cannot be empty";
            price = "0";
            url = "/add_product.jsp";
            isOk = false;
        }
        
        Product product = new Product(code, description, Double.valueOf(price));  
        
        if(isOk){
            productDAO.insertProduct(product);
            url = "/displayProducts";
        }else{
            request.setAttribute("data", product);
            boolean messageTemp = true;
            request.setAttribute("messageTemp", messageTemp);
        }
        request.setAttribute("codeMessage", codeMessage);
        request.setAttribute("desMessage", descriptionMessage);
        request.setAttribute("priceMessage", priceMessage);
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
