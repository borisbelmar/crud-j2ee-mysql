<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Productos</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">
</head>
<body>
	<section class="section">
		<div class="container">
			<div class="columns">
				<div class="column is-8 is-offset-2">
					<div class="content">
						<h1>Productos</h1>
						<form id="form" onsubmit="return checkSearch()">
							<div class="field has-addons">
								<div class="control">
									<span class="select">
									<select name="type">
										<c:choose>
											<c:when test="${ param['type'].equals('keyword') }">
												<option>Selecciona el tipo</option>
												<option value="keyword" selected>Término</option>
												<option value="code">Código</option>
											</c:when>
											<c:when test="${ param['type'].equals('code') }">
												<option>Selecciona el tipo</option>
												<option value="keyword">Término</option>
												<option value="code" selected>Código</option>
											</c:when>
											<c:otherwise>
												<option selected>Selecciona el tipo</option>
												<option value="keyword">Término</option>
												<option value="code">Código</option>
											</c:otherwise>
										</c:choose>
									</select>
									</span>
								</div>
								<div class="control">
									<input class="input" type="text" placeholder="Búsqueda" name="search" value="${param['search']}">
								</div>
								<div class="control">
									<button class="button is-primary">Buscar</button>
								</div>
							</div>
						</form>
					</div>
					<div class="box">
						<table class="table is-fullwidth">
							<thead>
								<tr>
									<th>Código</th>
									<th>Nombre</th>
									<th>Precio</th>
									<th>Categoría</th>
									<th colspan=2>Acciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="producto" items="${productos}">
									<tr>
										<th><c:out value="${producto.codigo}"/></th>
										<td><c:out value="${producto.nombre}"/></td>
										<td>$<c:out value="${producto.precio}"/></td>
										<td><c:out value="${producto.nom_categoria}"/></td>
										<td>
											<a class="button is-small is-warning is-fullwidth" href="edit?code=<c:out value="${producto.codigo}" />">Editar</a>
										</td>
										<td>
											<form method="POST" action="delete">
												<input class="is-hidden" name="code" value="${producto.codigo}">
												<input type="submit" class="button is-small is-danger is-fullwidth" value="Eliminar">
											</form>
										</td>				
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<a class="button is-primary is-fullwidth is-medium" href="register">Registrar un nuevo producto</a>
				</div>
			</div>
		</div>
	</section>
	<script>
	
		function getElement(id) {
			return document.getElementById(id);
		}
	
		function validate() {
			if(checkNombre() && checkPrecio() && checkCategoria()) {
				return true;
			} else {
				return false;
			}
		}
	
		function checkNombre() {
			var element = getElement('form').nombre;
			if (element.value.trim() != "") {
				getElement('errorNombre').classList.add('is-hidden');
				element.classList.remove('is-danger');
				return true;
			} else {
				getElement('errorNombre').classList.remove('is-hidden');
				element.classList.add('is-danger');
				return false;
			}
		}
	
		function checkPrecio() {
			var element = getElement('form').precio;
			if (element.value != "" && !isNaN(element.value) && element.value >= 0) {
				getElement('errorPrecio').classList.add('is-hidden');
				element.classList.remove('is-danger');
				return true;
			} else {
				getElement('errorPrecio').classList.remove('is-hidden');
				element.classList.add('is-danger');
				return false;
			}
		}
	
		function checkCategoria() {
			var element = getElement('form').categoria;
			if (element.value > 0 && !isNaN(element.value)) {
				getElement('errorCategoria').classList.add('is-hidden');
				element.classList.remove('is-danger');
				return true;
			} else {
				getElement('errorCategoria').classList.remove('is-hidden');
				element.classList.add('is-danger');
				return false;
			}
		}
	
		function checkSearch() {
		    var type = getElement('form').type;
		    var search = getElement('form').search;
		    if(type.value == "code") {
		        if(!isNaN(search.value) && search.value > 0) {
		            return true;
		        } else {
		            search.value = 0;
		            return false;
		        }
		    } else {
		        return true;
		    }
		}
	</script>
</body>
</html>