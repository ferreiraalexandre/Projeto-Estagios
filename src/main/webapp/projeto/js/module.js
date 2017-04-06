var app = angular.module("projeto-estagios", ['ngMaterial', 'md.data.table', 'ngMdIcons']);

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
