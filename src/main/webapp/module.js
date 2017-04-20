var app = angular.module("projeto-estagios", ['ngMaterial', 'md.data.table', 'ngMdIcons', 'ngRoute']);

app.config(function($routeProvider) {

	$routeProvider
	.when('/', {
		templateUrl : 'tabela.html',
	})

	$routeProvider
	.when('/usuario', {
		templateUrl : 'projeto/usuario/usuario.html',
	})

});



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
