console.log("Hola");

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
	if (element.value != "" && !isNaN(element.value)) {
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
	if (element.value > 1 && !isNaN(element.value)) {
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
    if(type.value == "codigo") {
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