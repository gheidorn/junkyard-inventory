<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="page-header">
	<h1>Current Inventory</h1>
</div>
<div class="row">
	<div class="span14">
		<table class="zebra-striped">
			<thead>
				<tr>
					<th>Item ID</th>
					<th>Name</th>
					<th>Type</th>
					<th>Description</th>
					<th>Inventory Date</th>
				</tr>
			</thead>
			<tbody>
		<c:forEach items="${pagedItemList.pageList}" var="item">
			<tr>
				<td><a href="/inventory/view/${item.id}"><c:out value="${item.id}" /></a></td>
				<td><a href="/inventory/view/${item.id}"><c:out value="${item.name}" /></a></td>
				<td><c:out value="${item.type}" /></td>
				<td><c:out value="${item.description}" /></td>
				<td><c:out value="${item.created}" /></td>
			</tr>
		</c:forEach>
			</tbody>
		</table>
		<div class="pagination">
		  <ul>
			<li class="prev <c:if test="${0 == pagedItemList.page}">disabled</c:if>"><a href="/inventory/list?page=previous">&larr; Previous</a></li>
		<c:forEach begin="${pagedItemList.firstLinkedPage}" end="${pagedItemList.lastLinkedPage}" var="ctr">
				<li <c:if test="${ctr == pagedItemList.page}">class="active"</c:if>><a href="/inventory/list?page=${ctr+1}">${ctr+1}</a></li>	
		</c:forEach>
		    <li class="next <c:if test="${pagedItemList.lastLinkedPage == pagedItemList.page}">disabled</c:if>"><a href="/inventory/list?page=next">Next &rarr;</a></li>
		  </ul>
		</div>
		<div class="well">
			<a href="/inventory/add" class="btn primary">Add New Item</a>
			<!-- <a href="#" class="btn info" id="inventoryDateHeader" rel="popover" data-content="This is the date that the item was entered into the system." data-original-title="Inventory Date">Inventory Date</a> -->
		</div>
	</div>
</div>