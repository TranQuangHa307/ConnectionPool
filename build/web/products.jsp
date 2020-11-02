<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Maintenance</title>
    </head>
    <body>
        <%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
        <h1>Products</h1>
            
        <table border = 1>
            <tr>
                <td>Code</td>
                <td>Description</td>
                <td>Price</td>
            </tr>
            
            <c:forEach var = "p" items = "${data}" >
                <tr>
                    <td>${p.code}</td>
                    <td>${p.description}</td>
                    <td>${p.price}</td>
                    <td> <a href = "<c:url value="editProduct?productCode=${p.code}" />">Edit</a> </td>
                    <td> <a href = "<c:url value="deleteProduct?productCode=${p.code}" />">Delete</a> </td>
                </tr>
            </c:forEach>
       </table>
       
       <br>
       
       <button><a href=" <c:url value = "addProduct" /> " style = "text-decoration: none; color: black" >Add Product</a></button>
       
    </body>
</html>
