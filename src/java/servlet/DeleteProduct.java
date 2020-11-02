
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

public class DeleteProduct extends HttpServlet {
    private ProductDAO productDAO;
    
    public void init(){
        productDAO = new ProductDAO();
    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
           String code  = request.getParameter("productCode");
           
           ArrayList<Product> listProduct = productDAO.getAllProduct();
           for(Product p : listProduct){
               if(p.getCode().equals(code)){
                   request.setAttribute("data", p);
                   break;
               }
           }
           String url = "/delete_product.jsp";
           RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
           dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            String code = request.getParameter("productCode");
            
            productDAO.deleteProductByCode(code);
            
            String url = "/displayProducts";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
    }
}
