var app = angular.module("Desafio", []);

app.factory('service', function() {
	var valor = {};

	function set(data) {
		valor = data;
	}
	function get() {
		return valor;
	}

	return {
		set : set,
		get : get
	}

});
