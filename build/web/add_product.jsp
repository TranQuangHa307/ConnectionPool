<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "/WEB-INF/tlds/mytags.tld" prefix = "e" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Maintenance</title>
    </head>
    <body>
        <h1>Product</h1>
        <c:if test ="${messageTemp == true}">
            <i>You must enter all required fields.</i>
            <br>
            <br>
            <i>* marks required fields</i>
            <p></p>
        </c:if>
            <form action= "<c:url value ="addProduct" />" method = "post">
        <table>
            <tr>
                <td>Product Code:</td>
                <td><input type ="text" value= "${data.code}" name = "code"></td>
                <td><e:Error field ="${data.code}" /></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type ="text" size = "50px" value="${data.description}" name = "description"></td>
                <td><e:Error field ="${data.description}" /></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type ="text" value="${data.price}" name = "price"></td>
                <c:if test ="${data.price == 0.0}">
                    <td><e:Error field ="${null}" /></td>
                </c:if>
            </tr>
            <tr>
                <td><button type="Submit">Add Product</button></td>
                <td><button><a href="<c:url value="displayProducts" />" style = "text-decoration: none; color: black">View Products</a></button></td>
            </tr>
        </table>
        </form>
    </body>
</html>
