
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

public class DisplayProducts extends HttpServlet {
    private ProductDAO productDAO;
    
    public void init(){
        productDAO = new ProductDAO();
    }    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> listProduct = new ArrayList<>();
        listProduct = productDAO.getAllProduct();
        request.setAttribute("data", listProduct);
        
        String url = "/products.jsp";
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);        
    }
}

