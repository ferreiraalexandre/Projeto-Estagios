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
	
<<<<<<< HEAD
	$routeProvider
	.when('/curso', {
		templateUrl : 'projeto/curso/curso.html',
		controller : 'cursoController'
	})
	
=======
>>>>>>> branch 'master' of https://github.com/ferreiraalexandre/Projeto-Estagios.git
	$routeProvider
	.when('/turma', {
		templateUrl : 'projeto/turma/turma.html',
		controller : 'turmaController'
	})
	
	$routeProvider
	.when('/empresa', {
		templateUrl : 'projeto/empresa/empresa.html',
		controller : 'empresaController'
	})

});

