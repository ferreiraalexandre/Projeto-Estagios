var app = angular.module("projeto-estagios", ['ngMaterial', 'ngMessages','md.data.table', 'ngMdIcons', 'ngRoute', 'ui.mask', 'ngResource', 'ngAnimate', 'toastr', 'brasil.filters']);

app.pathRest = 'rest';

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
	
	$routeProvider
	.when('/empresa', {
		templateUrl : 'projeto/empresa/empresa.html',
		controller : 'empresaController'
	})

});

