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
						<h1>Nuevo producto</h1>
                    </div>
                    <div class="box">
                        <form method="POST" id="form" onsubmit="return validate()">
                            <div class="field">
                                <label class="label">Nombre</label>
                                <div class="control">
                                    <input type="text" class="input" name="nombre" onfocusout="checkNombre()">
                                    <small id="errorNombre" class="is-hidden has-text-danger">Debe entregar un nombre válido</small>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Precio</label>
                                <div class="control">
                                    <input type="text" class="input" name="precio" onfocusout="checkPrecio()">
                                    <small id="errorPrecio" class="is-hidden has-text-danger">Debe ingresar un número entero positivo</small>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Categoría</label>
                                <div class="control">
                                    <div class="select is-fullwidth">
                                        <select name="categoria" onfocusout="checkCategoria()">
                                        	<option disabled selected>Selecciona una categoría</option>
                                        	<c:forEach var="categoria" items="${categorias}">
                                        		<option value="${categoria.codigo}">
                                        			<c:out value="${categoria.nombre}"/>
                                        		</option>
                                        	</c:forEach>
                                        </select>
                                    </div>
                                    <small id="errorCategoria" class="is-hidden has-text-danger">Debe seleccionar una categoría</small>
                                </div>
                            </div>
                            <div class="field">
                                <div class="control">
                                    <button class="button is-primary is-fullwidth">
                                        Registrar Producto
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="field">
                        <div class="control">
                            <a href="./" class="button is-light is-fullwidth">
                                Cancelar
                            </a>
                        </div>
                    </div>
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