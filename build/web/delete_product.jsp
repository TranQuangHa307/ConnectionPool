<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Maintenance</title>
    </head>
    <body>
        <h1>Are you sure want to delete this product?</h1>
        <form action = "<c:url value ="deleteProduct?productCode=${data.code}" />" method= "post" >
        <table>
            <tr>
                <td>Product Code:</td>
                <td><input type ="text" value="${data.code}" name = "code" readonly 
                           style = "border: none; border-color: transparent;">
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type ="text" size = "50px" value="${data.description}" name = "description" readonly
                           style = "border: none; border-color: transparent;">
                </td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type ="text" value="${data.price}" name = "price" readonly
                           style = "border: none;border-color: transparent;" >
                </td>
            </tr>
            <tr>
                <td><button type="Submit">Yes</button></td>
                <td><button><a href="<c:url value="displayProducts" />" style = "text-decoration: none; color: black">No</a></button></td>
            </tr>
        </table>
        </form>
    </body>
</html>
