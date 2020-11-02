
package servlet;

import business.Product;
import dao.ProductDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProduct extends HttpServlet {
    private ProductDAO productDAO;
    
    public void init(){
        productDAO = new ProductDAO();
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String code = request.getParameter("productCode");
        
        Product product = productDAO.getProductByCode(code);
        request.setAttribute("data", product);
        
        String url = "/edit_product.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String code = request.getParameter("productCode");
        
        ArrayList<Product> listProduct = productDAO.getAllProduct();
    
        String newCode = request.getParameter("code");
        String newDescription = request.getParameter("description");
        String newPrice = request.getParameter("price");
        
        String codeMessage = "";
        String descriptionMessage = "";
        String priceMessage = "";
        String url = "";
        
        boolean isNumber = true;
        try{
            Double price  = Double.parseDouble(newPrice);
        }catch(NumberFormatException e){
            isNumber = false;
        }
        
        boolean isOk = true;
        
        if(newCode.length() == 0){
            codeMessage = "Product code cannot be empty";
            url = "/edit_product.jsp";
            isOk = false;
        }
        
        if(newDescription.length() == 0){
            descriptionMessage = "Description cannot be empty";
            url = "/edit_product.jsp";
            isOk = false;
        }
        
        if(newPrice.length() == 0 || isNumber == false || newPrice.equals("0.0")){
            priceMessage = "Price must be number and cannot be empty";
            newPrice = "0";
            url = "/edit_product.jsp";
            isOk = false;
        }
        
        Product product = new Product(newCode, newDescription, Double.parseDouble(newPrice));
        
        if(isOk){
            for(Product p : listProduct){
                if(p.getCode().equals(code)){
                    p.setCode(newCode);
                    p.setDescription(newDescription);
                    p.setPrice(Double.parseDouble(newPrice));
                    break;
                }
            }
            productDAO.updateProductByCode(product);
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
