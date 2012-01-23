<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="page-header">
	<h1>Current Inventory</h1>
</div>
<div class="row">
	<div class="span14">
		<table>
			<tbody>
			<tr>
				<td><strong>Created</strong></td>
				<td><c:out value="${inventoryItem.created}" /></td>
			</tr>
			<tr>
				<td><strong>Item ID</strong></td>
				<td><c:out value="${inventoryItem.id}" /></a></td>
			</tr>
			<tr>
				<td><strong>Name</strong></td>
				<td><c:out value="${inventoryItem.name}" /></a></td>
			</tr>
			<tr>
				<td><strong>Type</strong></td>
				<td><c:out value="${inventoryItem.type}" /></td>
			</tr>
			<tr>
				<td><strong>Description</strong></td>
				<td><c:out value="${inventoryItem.description}" /></td>
			</tr>
			</tbody>
		</table>
		
		<div class="well">
			<a href="/inventory/add" class="btn primary">Add New Item</a>
			<a href="/inventory/edit/${inventoryItem.id}" class="btn info">Edit This Item</a>
			<a href="/inventory/list" class="btn">Return to Inventory</a>
		</div>
	</div>
</div>