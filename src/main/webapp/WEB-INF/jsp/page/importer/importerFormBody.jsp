<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="formAction" value="/importer" scope="page" />

<div class="section">
	<form:form modelAttribute="importerForm" action="${formAction}" method="post" enctype="multipart/form-data">
		<form:hidden path="importFilePath" />

		<div class="globalErrorsContainer">
			<form:errors cssClass="formError" />
		</div>

		<div>
			<label for="driverClassName">Driver Class Name</label>
			<form:input path="driverClassName" id="driverClassName" cssClass="inputField" />
			<form:errors path="driverClassName" cssClass="formError" />
		</div>

		<div>
			<label for="url">URL</label>
			<form:input path="url" id="url" cssClass="inputField" />
			<form:errors path="url" cssClass="formError" />
		</div>

		<div>
			<label for="username">Username</label>
			<form:input path="username" id="username" cssClass="inputField" />
			<form:errors path="username" cssClass="formError" />
		</div>

		<div>
			<label for="password">Password</label>
			<form:input path="password" id="password" cssClass="inputField" />
			<form:errors path="password" cssClass="formError" />
		</div>

		<div>
			<label for="importFile">Password</label>
			<input type="file" id="importFile" name="importFile" size="40" class="inputField" />
			<form:errors path="importFilePath" cssClass="formError" />
		</div>

		<div class="buttons">
			<input type="submit" value="Submit" />
		</div>

	</form:form>

</div>
