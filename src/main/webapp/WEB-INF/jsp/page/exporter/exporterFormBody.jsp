<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="formAction" value="/export" scope="page" />

<div class="section">
	<form:form modelAttribute="exporterForm" action="${formAction}" method="post">
		<div class="globalErrorsContainer">
			<form:errors cssClass="formError" />
		</div>

		<div>
			<label for="driverClassName">Driver Class Name</label>
			<form:input path="driverClassName" id="driverClassName" cssClass="inputField" size="50" />
			<form:errors path="driverClassName" cssClass="formError" />
		</div>

		<div>
			<label for="url">URL</label>
			<form:input path="url" id="url" cssClass="inputField" size="50" />
			<form:errors path="url" cssClass="formError" />
		</div>

		<div>
			<label for="username">Username</label>
			<form:input path="username" id="username" cssClass="inputField" size="50" />
			<form:errors path="username" cssClass="formError" />
		</div>

		<div>
			<label for="password">Password</label>
			<form:input path="password" id="password" cssClass="inputField" size="50" />
			<form:errors path="password" cssClass="formError" />
		</div>

		<div>
			<label for="tableNames">Table Names</label>
			<form:input path="tableNames" id="tableNames" cssClass="inputField" size="50" />
			<form:errors path="tableNames" cssClass="formError" />
		</div>

		<div>
			<label for="query">Query</label>
			<form:input path="query" id="query" cssClass="inputField" size="50" />
			<form:errors path="query" cssClass="formError" />
		</div>

		<div>
			<label for="exportFilename">Export Filename</label>
			<form:input path="exportFilename" id="exportFilename" cssClass="inputField" size="50" />
			<form:errors path="exportFilename" cssClass="formError" />
		</div>

		<div class="buttons">
			<input type="submit" value="Submit" />
		</div>

	</form:form>

</div>
