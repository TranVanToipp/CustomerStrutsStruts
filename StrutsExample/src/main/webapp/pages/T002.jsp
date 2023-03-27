<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>

<title>Search - Training</title>
</head>
<style type="text/css">
	<%@ include file="/WEB-INF/css/common.css"%>
</style>
<style type="text/css">
<%@ include file="/WEB-INF/css/T002.css"%>
</style>
<body>
	<style type="text/jsp">
  <%@include file="/WEB-INF/include/header.jsp"%>
</style>

<div class = "search-container">
	<div class = "search-container__dan">
		<div class = "search-container__text search-container__text_text">
			Login > Search Customer
		</div>
	<div class = "search-container__context">
				<div class = "search-container__logo">
				Welcome
				<logic:notEmpty name="namenamewellcom">
				    <div class="search-container__logo--wellcomname">
				        <%= " " + session.getAttribute("namenamewellcom") %>
				    </div>
				</logic:notEmpty>
				</div>
			<a href = "/Struts-blank/pages/T001.jsp" class = "search-container__logout search-container__text_text" onclick = "Logout() ">
    			Log Out
			</a>
	</div>
	
	<div class = "search-container__line"></div>
	
	
<form id = "form-Search" action ="/StrutsExample/T002.do" method = "POST" >
	<div class = "search-container__handalSearch">
		<div class = "search-container__handalSearch--margin handalSearch-customerName">
			<div class = "handalSearch-customercommon handalSearch-customerName__text search-container__text_text">Customer Name</div>
			<%if (request.getAttribute("nameSearch") != null){ %>
				<input id = "txtCustomerName" name = "CUSTOMER_NAME" value ="<%=request.getAttribute("nameSearch")%>" maxLength = "50"/>
			<%} %>
			<%if (request.getAttribute("nameSearch") == null){ %>
				<input id = "txtCustomerName" name = "CUSTOMER_NAME" maxLength = "50"/>
			<%} %>
		</div>
		
		<%if (request.getAttribute("sexSearch") == null) { %>
		<div class = "search-container__handalSearch--margin handalSearch-customerSex">
			<div class = "handalSearch-customercommon handalSearch-customerSex__text search-container__text_text">Sex</div>
			<select name="SEX" value = "1" id = "cboSex">
				<option value=""></option>
		      	<option value="0">Male</option>
		     	<option value="1">Female</option>
			</select>
		</div>
		<%} %>
		<%if (request.getAttribute("sexSearch") != null) { %>
			<div class="search-container__handalSearch--margin handalSearch-customerSex">
			  <div class="handalSearch-customercommon handalSearch-customerSex__text search-container__text_text">Sex</div>
			  <select name="SEX" id="cboSex">
			    <option value=""></option>
			    <option value="0" <% if(request.getAttribute("sexSearch")!=null && request.getAttribute("sexSearch").equals("0")){ %>selected<% } %>>Male</option>
			    <option value="1" <% if(request.getAttribute("sexSearch")!=null && request.getAttribute("sexSearch").equals("1")){ %>selected<% } %>>Female</option>
			  </select>
			</div>
		<%} %>
		
		<div class = "search-container__handalSearch--margin handalSearch-BirthdayFrom">
			<div class = "handalSearch-customercommon handalSearch-BirthdayFrom__text search-container__text_text">Birthday</div>
			
			<%if (request.getAttribute("birthdayFromSearch") != null && request.getAttribute("birthDayFromFrom") == null){%>
			<input id = "txtBirthdayForm" class = "txtCustomerValidateFROM" value="<%=request.getAttribute("birthdayFromSearch")%>" name = "txtBirthdayFromName"  maxLength = "10"/>
			<%} %>
			
			<%if (request.getAttribute("dataFrom") == null && request.getAttribute("birthDayFromFrom") == null && request.getAttribute("birthdayFromSearch") == null){%>
			<input id = "txtBirthdayForm" class = "txtCustomerValidateFROM" name = "txtBirthdayFromName"  maxLength = "10"/>
			<%} %>
			
			<%if (request.getAttribute("birthDayFromFrom") != null){%>
			<input id = "txtBirthdayForm" class = "txtCustomerValidateFROM" value="<%=request.getAttribute("birthDayFromFrom")%>" name = "txtBirthdayFromName"  maxLength = "10"/>
			<%} %>
			<lable class = "handalSearch-BirthdayFrom__ngangcach">～</lable>
			<%if (request.getAttribute("dataTo") == null && request.getAttribute("birthDayToTo") == null){%>
				<input id = "txtBirthdayTo" class = "txtCustomerValidateTO" name = "txtBirthdayToName" maxLength = "10"/>
			<%} %>
			
			<%if (request.getAttribute("dataTo") != null) { %>
				<input id = "txtBirthdayForm" class = "txtCustomerValidateFROM" value="<%=request.getAttribute("dataTo")%>" name = "txtBirthdayFromName"  maxLength = "10"/>
			<%} %>
			
			<%if (request.getAttribute("birthDayToTo") != null){%>
				<input id = "txtBirthdayForm" class = "txtCustomerValidateFROM" value="<%=request.getAttribute("birthDayToTo")%>" name = "txtBirthdayFromName"  maxLength = "10"/>
			<%} %>
		</div>
		
		<div class = "handalSearch-btnSearch">
			<input name="ACTION" type="submit" id = "btnSearch" value="Search" />
		</div>
	</div>

	
	<div class = "search-container__btnContext--chuyenhuong">
		<div class = "search-container__btnContext--start">
	<logic:empty name="ListSearch">
		<logic:empty name="ListData">
			<button id = "btnFirst" disabled style="pointer-events: none;"><a href="#"><<</a></button>
			<button id = "btnPrevious" disabled style="pointer-events: none;"><a href="#"><</a></button>
		</logic:empty>
		<logic:notEmpty name = "ListData">
			<logic:equal name="tag" value="1">
				<button id = "btnFirst" disabled style="pointer-events: none;"><a href="#"><<</a></button>
				<button id = "btnPrevious" disabled style="pointer-events: none;"><a href="#"><</a></button>
			</logic:equal>
			<%
				String tag = String.valueOf(request.getAttribute("tag"));
				String endP = String.valueOf(request.getAttribute("endP"));
			%>
			<%if (Integer.parseInt(tag) > 1 && Integer.parseInt(tag) <= Integer.parseInt(endP)) { %>
				<button id = "btnFirst"><a href="/StrutsExample/T002.do?index=<%=1 %>"><<</a></button>		
				<button id="btnPrevious"><a href="/StrutsExample/T002.do?index=<%= (Integer.parseInt(request.getAttribute("tag").toString()) - 1) %>"><</a></button>
			<%} %>
		</logic:notEmpty>
		<lable class = "search-container__btnContext--textstart search-container__text_text">Previous</lable>
	</div>
	
	<div class = "search-container__btnContext--end">
		<lable class = "search-container__btnContext--textend search-container__text_text">Next</lable>
		<logic:empty name="ListData">
			<button id = "btnNext" disabled style="pointer-events: none;"><a href="#">></a></button>
			<button id="btnPrevious" disabled style="pointer-events: none;"><a href="#">>></a></button>
		</logic:empty>
		<logic:notEmpty name="ListData">
			<%
				String enadP = String.valueOf(request.getAttribute("endP"));
				String tagg = String.valueOf(request.getAttribute("tag"));
				if (Integer.parseInt(tagg) >= Integer.parseInt(enadP)) {
					%>
					<button id = "btnNext" disabled style="pointer-events: none;"><a href="#">></a></button>
					<button id="btnPrevious" disabled style="pointer-events: none;"><a href="#">>></a></button>
				<% }%>
				
			
				<%if (Integer.parseInt(tagg) < Integer.parseInt(enadP)) {%>
					<button id="btnPrevious"><a href="/StrutsExample/T002.do?index=<%=(Integer.parseInt(request.getAttribute("tag").toString()) + 1) %>">></a></button>
				    <button id="btnNext"><a href="/StrutsExample/T002.do?index=<%=request.getAttribute("endP")%>">>></a></button>
				<%} %>
			
	</logic:notEmpty>	
</logic:empty>
<logic:notEmpty name="ListSearch">
		<logic:empty name="ListSearch">
			<button id = "btnFirst" disabled style="pointer-events: none;"><a href="#"><<1</a></button>
			<button id = "btnPrevious" disabled style="pointer-events: none;"><a href="#"><</a></button>
		</logic:empty>
		<logic:notEmpty name="ListSearch">
			<%String currentPage = String.valueOf(request.getAttribute("currentPage"));%>
			<%if (Integer.parseInt(currentPage) == 1) { %>
				<button id="btnFirst" disabled style="pointer-events: none;"><a href="#"><<</a></button>
	            <button id="btnPrevious" disabled style="pointer-events: none;"><a href="#"><</a></button>
			<%} %>
			<%if (Integer.parseInt(currentPage) > 1) { %>
				<button id="btnFirst" class="withinput-search" type="submit" onclick="sendPostRequest(1)"><<</button>
       			<button id="btnPrevious" class="withinput-search" type="submit" onclick="sendPostRequest(<%=(Integer.parseInt(request.getAttribute("currentPage").toString()) - 1)%>)"><</button>
       			<input name="pagepage" hidden value=""/>
			<%} %>
		</logic:notEmpty>
			<lable class = "search-container__btnContext--textstart search-container__text_text">Previous</lable>
		</div>
		<div class = "search-container__btnContext--end">
			<lable class = "search-container__btnContext--textend search-container__text_text">Next</lable>
			<logic:empty name="ListSearch">
				<button id = "btnNext" disabled style="pointer-events: none;"><a href="#">></a></button>
				<button id="btnPrevious" disabled style="pointer-events: none;"><a href="#">>></a></button>
			</logic:empty>
			<logic:notEmpty name="ListSearch">
				<%String currentPage = String.valueOf(request.getAttribute("currentPage"));%>
				<%String totalPages = String.valueOf(request.getAttribute("totalPages"));%>
				<%if (Integer.parseInt(currentPage) >= Integer.parseInt(totalPages)) {%>
					<button id = "btnNext" disabled style="pointer-events: none;"><a href="#">></a></button>
					<button id="btnPrevious" disabled style="pointer-events: none;"><a href="#">>></a></button>
				<%} %>
				<%if (Integer.parseInt(currentPage) < Integer.parseInt(totalPages)) {%>
					<button id="btnNext" class="withinput-search" type="submit" onclick="sendPostRequest(<%=(Integer.parseInt(request.getAttribute("currentPage").toString()) + 1)%>)">></button>
	            	<button id="btnLast" class="withinput-search" type="submit" onclick="sendPostRequest(<%=totalPages%>)">>></button>
	            	<input name="pagepage" hidden value=""/>
				<%} %>
			</logic:notEmpty>
	</logic:notEmpty>
		</div>
	</div>
	<table class = "search-container__table">
        <tr class = "search-container__table--tieude">
        	<th class = "searchtable-trcheck"><input type="checkbox" id = "checkAll" name="checkboxAll" value="" onclick = "checkBox(this)"></th>
            <th class = "searchtable-trid">Customer ID</th>
            <th class = "searchtable-trname">Customer Name</th>
            <th class = "searchtable-trsex">Sex</th>
            <th class = "searchtable-trbirthday">Birthday</th>
            <th class = "searchtable-traddress">Address</th>
        </tr>

<logic:present name="ListSearch">
    <logic:iterate id="dept" name="ListSearch">
        <tr>
            <td class="searchtable-check"><input type="checkbox" name="checkboxAll" value="<bean:write name='dept' property='CUSTOMER_ID'/>"></td>
            <td><a href="/StrutsExample/T003.do?id=<bean:write name='dept' property='CUSTOMER_ID' />" class="searchtable-id"><bean:write name='dept' property='CUSTOMER_ID' /></a></td>
            <td class="searchtable-name"><bean:write name='dept' property='CUSTOMER_NAME' /></td>
            <td class="searchtable-sex"><bean:write name='dept' property='SEX' /></td>
            <td class="searchtable-birthday"><bean:write name='dept' property='BIRTHDAY' /></td>
            <td class="searchtable-address"><bean:write name='dept' property='ADDRESS' /></td>
        </tr>
    </logic:iterate>
</logic:present>
<logic:notPresent name="ListSearch">
    <logic:iterate name="ListData" id="dept">
	        <tr>
	            <td class="searchtable-check"><input type="checkbox" name="checkboxAll" value="<bean:write name='dept' property='CUSTOMER_ID'/>"></td>
	            <td><a href="/StrutsExample/T003.do?id=<bean:write name='dept' property='CUSTOMER_ID' />" class="searchtable-id"><bean:write name='dept' property='CUSTOMER_ID' /></a></td>
	            <td class="searchtable-name"><bean:write name='dept' property='CUSTOMER_NAME' /></td>
	            <td class="searchtable-sex"><bean:write name='dept' property='SEX' /></td>
	            <td class="searchtable-birthday"><bean:write name='dept' property='BIRTHDAY' /></td>
	            <td class="searchtable-address"><bean:write name='dept' property='ADDRESS' /></td>
	        </tr>
	    </logic:iterate>
</logic:notPresent>
    </table>
    <div class = "search-btn__form">
		<button class = "search-container__nav-btnAddNew">
			<a href="/Struts-blank/pages/T003.jsp">Add new</a>
		</button>
		
	
	 <logic:notEmpty name="ListData">
	 		<button type="button"  class="search-container__delete" onclick="deleteRows()">Delete</button>
	</logic:notEmpty>
	
	<logic:notEmpty name="ListSearch">
			<button type="button" class="search-container__delete" onclick="deleteRows()">Delete</button>
	</logic:notEmpty>
	
	
	<logic:empty name="ListData">
		<button type="button" disabled style="pointer-events: none;" class="search-container__delete" onclick="deleteRows()">Delete</button>
	</logic:empty>
	
	

	<logic:notEmpty name="buttonDelete">
	    <button type="button" disabled style="pointer-events: none;" class="search-container__delete" onclick="deleteRows()">Delete</button>
	</logic:notEmpty>
	
		<logic:empty name="ListData">
	<logic:empty name="ListSearch" >
			<button type="button" disabled style="pointer-events: none;" class="search-container__delete" onclick="deleteRows()">Delete</button>
		</logic:empty>
	</logic:empty>
		
    </div>
	</form>
	</div>
	<footer class="footer">
		<div class="header-br"></div>
		<div class="footer-text__copyright">Copyright (c) 2000-2008
			FUJINET, All Rights Reserved.</div>
	</footer>
</div>
</div>

<logic:notEmpty name="dataFrom" >
<script type="text/javascript">
	window.onload = function() {
		alert('<bean:message key="MESSAGE_ERROR_FROM"/>');
	};
</script>
</logic:notEmpty>

<logic:notEmpty name="dataTo">
<script type="text/javascript">
	window.onload = function () {		
		alert('<bean:message key="MESSAGE_ERROR_TO"/>');
	};
</script>
</logic:notEmpty>

<logic:notEmpty name="birthDayFromFrom" >
	<logic:notEmpty name="birthDayToTo">
		<script type="text/javascript">
			window.onload = function () {	
				alert('<bean:message key="MESSAGE_ERROR_FROM_BIGGER_TO"/>');
			};
		</script>
	</logic:notEmpty>
</logic:notEmpty>
	<script>
		
	<%@include file="/WEB-INF/js/T002.js"%>
		
	</script>
</body>
</html>