<%@include file="./common/taglibs.jsp" %>
<%@include file="./common/header.jsp" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <div class="w-container">
        <div class="w-form">
            <form id="wf-form-Login-Form" name="wf-form-Login-Form" data-name="Login Form" action="<c:url value='j_spring_security_check' />" method="post">
                <div class="w-nav" data-collapse="medium" data-animation="default" data-duration="400" data-contain="1">
                    <div class="w-container">
                        <a class="w-nav-brand" href="#"></a>
                        <nav class="w-nav-menu" role="navigation">
                            <a class="w-nav-link" href="index.html">Home</a>
                        </nav>
                        <div class="w-nav-button">
                            <div class="w-icon-nav-menu"></div>
                        </div>
                    </div>
                </div>
                
                <h1>Login</h1>
                <label for="UserName">Username:</label> 
                <input class="w-input" id="UserName" type="text" placeholder="Enter your username" name="username" data-name="UserName" required="required"> 
                <label for="Password">Password:</label> 
                <input class="w-input" id="Password" type="password" placeholder="Enter your password" name="password" data-name="Password" required="required">
                <div class="w-row">
                    <div class="w-col w-col-6"></div>
                    <div class="w-col w-col-6">
                        <input class="w-button" type="submit" name="submit" value="Login" data-wait="Please wait...">
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
            <c:if test="${not empty error}">
                <div class="w-form-fail">
                    <p>${error}</p>
                </div>
            </c:if>
            <c:if test="${not empty timeout}">
                <div class="w-form-fail">
                    <p>${timeout}</p>
                </div>
            </c:if>
            <c:if test="${not empty logout}">
                <div class="w-form-done">
                    <p>${logout}</p>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>