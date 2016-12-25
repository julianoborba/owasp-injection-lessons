<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Injection</title>
		<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<h2><a href="https://www.owasp.org/index.php/SQL_Injection_Prevention_Cheat_Sheet">https://www.owasp.org</a> - SQL Injection</h2>
			<p>SQL Injection flaws are introduced when software developers create dynamic database queries that include user supplied input. To avoid SQL injection flaws is simple. Developers need to either: a) stop writing dynamic queries; and/or b) prevent user supplied input which contains malicious SQL from affecting the logic of the executed query.</p>
			<p>Primary Defenses:
				<ul>
				    <li>Option #1: Use of Prepared Statements (Parameterized Queries)</li>
				    <li>Option #2: Use of Stored Procedures</li>
				    <li>Option #3: Escaping all User Supplied Input</li>
				</ul>
			</p>
			<p>Additional Defenses:
			    <ul>
				    <li>Also Enforce: Least Privilege. To minimize the potential damage of a successful SQL injection attack, you should minimize the privileges assigned to every database account in your environment.</li>
			    	<li>Also Perform: White List Input Validation</li>
			    </ul>
			</p>
			<hr>
			<p>Your HQL query:</p>
			<p>${query}</p>
			<p>And your SQL query result:</p>
			<p>
				<c:forEach var="login" items="${logins}">
					${login.email}, ${login.password}</br>
				</c:forEach>
			</p>
			<hr>
			<p>Try to catch me - <a href="http://tools.kali.org/tools-listing">http://tools.kali.org/tools-listing</a></p>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="/resources/js/bootstrap.min.js"></script>
	</body>
</html>
