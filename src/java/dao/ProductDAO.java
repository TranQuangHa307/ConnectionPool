
package dao;

import business.Product;
import data.ConnectionPool;
import data.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/productdb?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Tranquangha123@@";
    
    private static final String INSERT_PRODUCT_SQL = "insert into information "
            + "(ProductCode,ProductDescription,ProductPrice) values (?,?,?);";
    
    private static final String UPDATE_PRODUCT_BY_ID_SQL = "update information set "
            + "ProductCode = ?, ProductDescription = ?, ProductPrice = ? where id = ?;";
    
    private static final String UPDATE_PRODUCT_BY_CODE_SQL = "update information set"
            + "ProductCode = ?, ProductDescription = ?, ProductPrice = ? where ProductCode = ? ";
    
    private static final String DELETE_PRODUCT_BY_ID_SQL = "delete from information where id = ?";
    
    private static final String DELETE_PRODUCT_BY_CODE_SQL = "delete from information where ProductCode = ?";
    
    private static final String SELECT_PRODUCT_SQL = "select id,ProductCode,ProductDescription,ProductPrice "
            + "from information where id = ?";
    
    private static final String SELECT_ALL_PRODUCT_SQL = "select * from information";
    
    private static final String SELECT_PRODUCT_BY_CODE_SQL = "select id,ProductCode,ProductDescription,ProductPrice "
            + "from information where ProductCode = ?;";
    
//    
//    protected Connection getConnection(){
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return connection;
//    }
    
    public int insertProduct(Product product){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        try{
            ps = connection.prepareStatement(INSERT_PRODUCT_SQL,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setString(3, String.valueOf(product.getPrice()));
            System.out.println(ps);
            int affectedRows = ps.executeUpdate();
            int id = -1;
            if(affectedRows != 0){
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        id = (int) generatedKeys.getLong(1);
                    }
                }
            }
            return id;
        } catch (SQLException ex) {
            printSQLException(ex);
            return -1;
        } finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }      
    }
    
    public void updateProductByID(Product product){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        try{
            ps = connection.prepareStatement(UPDATE_PRODUCT_BY_ID_SQL);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setFloat(3, (float) product.getPrice());
            ps.setInt(4, product.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            printSQLException(ex);
        } finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }   
    }
    
    public void updateProductByCode(Product product){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        try{
            ps = connection.prepareStatement(UPDATE_PRODUCT_BY_CODE_SQL);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setFloat(3, (float) product.getPrice());
            ps.setString(4, product.getCode());
            ps.executeUpdate();
        } catch (SQLException ex) {
            printSQLException(ex);
        } finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }   
    }
    
    public void deleteProductByID(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        try{
            ps = connection.prepareStatement(DELETE_PRODUCT_BY_ID_SQL);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            printSQLException(ex);
        } finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        } 
    }
    
    public void deleteProductByCode(String code){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        
        try{
            ps = connection.prepareStatement(DELETE_PRODUCT_BY_CODE_SQL);
            ps.setString(1, code);
            ps.executeUpdate();
        } catch (SQLException ex) {
            printSQLException(ex);
        } finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        } 
    }
    
    public Product getProductByID(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Product product = null;
        try{
            ps = connection.prepareStatement(SELECT_PRODUCT_SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                String code = rs.getString(2);
                String des = rs.getString(3);
                Double price = rs.getDouble(4);
                product = new Product(id, code, des, price);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        } finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        } 
        return product;
    }
    
    public Product getProductByCode(String code){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Product product = null;
        try{
            ps = connection.prepareStatement(SELECT_PRODUCT_BY_CODE_SQL);
            ps.setString(1, code);
            System.out.println(ps);
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String des = rs.getString(3);
                Double price = rs.getDouble(4);
                product = new Product(id, code, des, price);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        } finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        } 
        return product;
    }
    
    public ArrayList<Product> getAllProduct(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<Product> allProducts = new ArrayList<>();
        try{
            ps = connection.prepareStatement(SELECT_ALL_PRODUCT_SQL);
            System.out.println(ps);
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String code = rs.getString(2);
                String des = rs.getString(3);
                Double price = rs.getDouble(4);
                allProducts.add(new Product(id, code, des, price));
            }
            
        } catch (SQLException ex) {
            printSQLException(ex);
        } finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        } 
        return allProducts;
    }
    
    private void printSQLException(SQLException ex){
        for(Throwable e : ex){
            if(e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState: "+ ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while(t != null){
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
