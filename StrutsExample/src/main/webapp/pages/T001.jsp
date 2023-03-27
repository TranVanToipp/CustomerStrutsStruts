<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
  <%@include file="/WEB-INF/css/T001.css"%>
</style>
<title>Login - Training</title>
</head>
<body>
	
		<div class="content">
			<div class="content-text">Login</div>
			<div class="content-login">
				<form action="./T001.do" name="frmLogin" method="POST">
					<div class="content-login__header">
						<h3>LOGIN</h3>
						<label id="lblErrorMessage">
							Thông báo
						
						</label>
					</div>
					<div class="content-login__container">

						<div class="form-group">
							<label for="fullname" class="form-label form-label__userID">User
								Id: </label> <input id="txtUserID" name="USERID" maxlength="8"
								type="text" class="form-control">
						</div>
						<div class="form-group__password">
							<label for="password" class="form-label">Password: </label> <input
								id="txtPassword" name="PASSWORD" type="password" maxlength="8"
								class="form-control">
						</div>
						<div class="form-group__btn">
							<button type="submit" id="btnLogin" class="form-submit">Login</button>
							<button type="button" id="btnClear" class="form-submit">Clear</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<footer class="footer">
		<div class="header-br"></div>
		<div class="footer-text__copyright">Copyright (c) 2000-2008
			FUJINET, All Rights Reserved.</div>
		</footer>
	</div>
</body>

</html>