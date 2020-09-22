<%@include file="./common/taglibs.jsp" %>
<%@include file="./common/header.jsp" %>
<%@ page session="true" %>
<html>
    <head>
        <title>Products</title>
    </head>
    <body>
        <!-- logout section START -->
        <c:url value="/j_spring_security_logout" var="logoutUrl" ></c:url>
 
        <form action="${logoutUrl}" method="post" id="logoutForm">
          <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <!-- logout section E.N.D -->
        
        <section class="container">
            <div class="w-container">
                <!-- row 1 : header -->
                <div class="w-row">
                    <div class="w-col w-col-9">
                        <h1>List of product</h1>
                    </div>
                    <div class="w-col w-col-3">
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <div>
                                <p>${pageContext.request.userPrincipal.name} | <a href="javascript:submitForm('logoutForm')"> logout</a></p>
                            </div>
                        </c:if>
                    </div>
                </div>
                <!-- errors block -->
                <spring:hasBindErrors name="productCart" htmlEscape="true">
                    <c:if test="${errors.errorCount gt 0}">
                        <div class="w-form-fail">
                            <c:forEach items="${errors.allErrors}"
                                var="error">
                                <spring:message code="${error.code}"
                                    arguments="${error.arguments}"
                                    text="${error.defaultMessage}" />
                                <br />
                            </c:forEach>
                        </div>
                    </c:if>
                </spring:hasBindErrors>
                <!-- row 2 : column name -->
                <div class="w-row">
                    <div class="w-col w-col-1">
                        <div>
                            <strong>No</strong>
                        </div>
                    </div>
                    <div class="w-col w-col-5">
                        <div>
                            <strong>Product</strong>
                        </div>
                    </div>
                    <div class="w-col w-col-3">
                        <div>
                            <strong>Price</strong>
                        </div>
                    </div>
                    <div class="w-col w-col-3">
                        <div>
                            <strong>Quantity</strong>
                        </div>
                    </div>
                </div>
    
                <!-- row i : product detail -->
                <c:forEach var="product" items="${ products }" varStatus="idx">
                    <div class="w-row">
                        <div class="w-col w-col-1">
                            <div>${idx.index + 1}</div>
                        </div>
                        <div class="w-col w-col-5">
                            <div>${ product.name }</div>
                        </div>
                        <div class="w-col w-col-3">
                            <div>${ product.salePrice }</div>
                        </div>
                        <div class="w-col w-col-3">
                            <div class="w-form">
                                <form:form id="rows${product.id}" commandName="productCart" method="post" htmlEscape="true">
                                    <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <input type="hidden" name="productId" value="${product.id}" />
                                    <input class="w-input" name="quantity" type="text" placeholder="Enter product's quantity" data-name="pro1_quantity" style="width: 180px;" />
                                    <input class="w-button" type="submit" value="Add to Cart" data-wait="Please wait..." />
                                </form:form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                
                <!-- row i+1 : checkout -->
                <div class="w-row">
                    <div class="w-col w-col-9"></div>
                    <div class="w-col w-col-3">
                        <div class="w-form">
                                <a href="./cart"><input class="w-button" type="button" value="Checkout" data-wait="Please wait..." style="background-color: blue"></a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>