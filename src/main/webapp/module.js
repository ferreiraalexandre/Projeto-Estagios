var app = angular.module("projeto-estagios", ['ngMaterial', 'md.data.table', 'ngMdIcons', 'ngRoute', 'ui.mask']);

app.config(function($routeProvider) {

	$routeProvider
	.when('/', {
		templateUrl : 'tabela.html',
	})

	$routeProvider
	.when('/unidadeEnsino', {
		templateUrl : 'projeto/unidadeEnsino/unidadeEnsino.html',
		controller : 'unidadeEnsinoController'
	})

	
	$routeProvider
	.when('/usuario', {
		templateUrl : 'projeto/usuario/usuario.html',
		controller : 'usuarioController'
	})

});

