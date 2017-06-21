var app = angular.module("projeto-estagios", ['ngMaterial', 'ngMessages','md.data.table', 'ngMdIcons', 'ngRoute', 'ui.mask', 'ngResource', 'ngAnimate', 'toastr', 'idf.br-filters']);

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
	.when('/curso', {
		templateUrl : 'projeto/curso/curso.html',
		controller : 'cursoController'
	})
	

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
	
	$routeProvider
	.when('/estagio', {
		templateUrl : 'index.html',
	})
	
	$routeProvider
	.when('/instituto', {
		templateUrl : 'projeto/instituto/instituto.html',
		controller : 'institutoController'
	})

});

// deixa o calendario em 'pt'
app.config(function($mdDateLocaleProvider) {
	
    $mdDateLocaleProvider.months = ['janeiro', 'fevereiro', 'março', 'abril', 'maio', 'junho', 'julho', 'agosto', 'setembro', 'outubro', 'novembro', 'dezembro'];
    $mdDateLocaleProvider.shortMonths = ['jan', 'fev', 'mar', 'abr', 'mai', 'jun', 'jul', 'ago', 'set', 'out', 'nov', 'dez'];
    $mdDateLocaleProvider.days = ['domingo', 'segunda', 'terça', 'quarta', 'quinta', 'sexta', 'sábado'];
    $mdDateLocaleProvider.shortDays = ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'];
    
    $mdDateLocaleProvider.formatDate = function(date) {
    	return moment(date).format('DD/MM/YYYY');
    };
    
});

