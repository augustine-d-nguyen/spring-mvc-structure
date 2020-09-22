<%@include file="./common/taglibs.jsp" %>
<%@include file="./common/header.jsp" %>
<%@ page session="true" %>
<html>
    <head>
        <title>Shopping Cart</title>
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
                <div class="w-row">
                    <div class="w-col w-col-9">
                        <h1>Checkout</h1>
                    </div>
                    <div class="w-col w-col-3">
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <div>
                                <p>${pageContext.request.userPrincipal.name} | <a href="javascript:submitForm('logoutForm')"> logout</a></p>
                            </div>
                        </c:if>
                    </div>
                </div>
                
                <!-- Error block -->
                <spring:hasBindErrors name="shoppingCart" htmlEscape="true">
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
                
                <!-- Title row -->
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
                    <div class="w-col w-col-2">
                        <div>
                            <strong>Price</strong>
                        </div>
                    </div>
                    <div class="w-col w-col-2">
                        <div>
                            <strong>Quantity<br></strong>
                        </div>
                    </div>
                    <div class="w-col w-col-2">
                        <div>
                            <strong>Total Price</strong>
                        </div>
                    </div>
                </div>
                
                <!-- Product rows -->
                <c:forEach var="orderDetail" items="${ order.orderDetails }" varStatus="idx">
                    <div class="w-row">
                        <div class="w-col w-col-1">
                            <div>${idx.index + 1}</div>
                        </div>
                        <div class="w-col w-col-5">
                            <div>${orderDetail.product.name}</div>
                        </div>
                        <div class="w-col w-col-2">
                            <div>${orderDetail.product.salePrice}$</div>
                        </div>
                        <div class="w-col w-col-2">
                            <div>${orderDetail.quantity}</div>
                        </div>
                        <div class="w-col w-col-2">
                            <div>${orderDetail.totalPrice}$</div>
                        </div>
                    </div>
                </c:forEach>
                <div class="w-row">
                    
                </div>
                
                <div class="w-row">
                    <div class="w-col w-col-1"></div>
                    <div class="w-col w-col-5"></div>
                    <div class="w-col w-col-2"></div>
                    <div class="w-col w-col-2">
                        <div>
                            <strong>Total Price:</strong>
                        </div>
                    </div>
                    <div class="w-col w-col-2">
                        <div>
                            <strong>${order.totalPayment}$</strong>
                        </div>
                    </div>
                </div>
                <div class="w-row">
                    <div class="w-col w-col-9">
                        <div class="w-form">
                            <form:form commandName="coupon" method="post" htmlEscape="true">
                                <div>Please enter coupon if needed</div>
                                <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <form:input class="w-input" id="coupon-2"  type="text" placeholder="Coupon code" name="code" path="code" data-name="Coupon 2"></form:input>
                                <form:errors cssClass="error" path="code" element="div"></form:errors>
                                <input class="w-button" type="submit" value="Check Coupon" data-wait="Please wait..." name="checkCoupon">
                            </form:form>
                        </div>
                    </div>
                    <div class="w-col w-col-3" vertical-align="true">
    
                    </div>
                </div>
                <div class="w-row">
                    <div class="w-col w-col-9"></div>
                    <div class="w-col w-col-3" vertical-align="true">
    
                    </div>
                </div>
                <div class="w-row">
                    <div class="w-col w-col-9">
                        <div class="w-form">
                            <form:form id="addressForm" commandName="address" method="post" htmlEscape="true">
                                <div>Enter address information&nbsp;(*)</div>
                                <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <form:textarea class="w-input" placeholder="Enter your address" name="content" path="content"></form:textarea>
                                <form:errors cssClass="error" path="content"></form:errors>
                            </form:form>
                        </div>
                    </div>
                    <div class="w-col w-col-3"></div>
                </div>
    
                <div class="w-row">
                    <div class="w-col w-col-9"></div>
                    <div class="w-col w-col-3">
                        <div class="w-form">
                                <div class="w-row">
                                    <div class="w-col w-col-8">
                                        <a href="./products">    <input class="w-button" type="button" value="Continue Shopping" data-wait="Please wait..."></a>
                                    </div>
                                    <div class="w-col w-col-4">
                                        <input class="w-button" type="submit" form="addressForm" name="register"
                                            value="Register Order" data-wait="Please wait..." style="background-color: blue" >
                                    </div>
                                </div>
                        </div>
                    </div>
                </div>
    
            </div>
    
        </section>
    </body>
</html>