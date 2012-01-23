<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="page-header">
	<h1>Inventory</h1>
</div>
<div class="row">
	<div class="span14">
	<c:if test="${!empty success}">
		<div class="alert-message success"><c:out value="${success}" /></div>
	</c:if>
	<sf:form method="POST" modelAttribute="inventoryItemForm">
		<fieldset>
		<legend>Add Inventory Item</legend>
	<spring:hasBindErrors name="inventoryItemForm">
		<div class="alert-message error">Please correct the errors on the form and re-submit.</div>
	</spring:hasBindErrors>
		<div class="clearfix">
			<label>Item ID</label>
			<div class="input"><sf:input path="inventoryItemId" cssClass="medium" /><span class="help-inline"><sf:errors path="inventoryItemId"></sf:errors></span></div>
		</div>
		<div class="clearfix">
			<label>Name</label>
			<div class="input"><sf:input path="name" /><span class="help-inline"><sf:errors path="name"></sf:errors></span></div>
		</div>
		<div class="clearfix">
			<label>Type</label>
			<div class="input"><sf:select path="type" items="${inventoryItemForm.types}"></sf:select><span class="help-inline"><sf:errors path="type"></sf:errors></span></div>
		</div>
		<div class="clearfix">
			<label>Description</label>
			<div class="input"><sf:textarea path="description" rows="3" cssClass="xxlarge"/><span class="help-inline"><sf:errors path="description"></sf:errors></span></div>
		</div>
	    <div class="well">
	    	<input type="submit" class="btn primary" value="Add Item" />
	    	<a href="/inventory/list" class="btn">Return to Inventory</a>
		</div>
		</fieldset>
	</sf:form>
  	</div>
<!--   	<div class="span4">
    	<h3>Secondary content</h3>
  	</div> -->
</div>