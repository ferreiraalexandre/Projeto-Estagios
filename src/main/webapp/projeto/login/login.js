var app = angular.module("projeto-estagios", ['ngMaterial', 'ngMessages','md.data.table', 'ngMdIcons', 'ngRoute', 'ui.mask', 'ngResource', 'ngAnimate', 'ngStorage', 'toastr', 'idf.br-filters']);

app.pathRest = 'rest';

app.config(function($routeProvider) {
	
	$routeProvider
	.when('/login', {
		templateUrl : 'projeto/login',
		controller : 'LoginController'
	})


});
app.config(['$mdThemingProvider', '$mdIconProvider', function ($mdThemingProvider, $mdIconProvider) {
    'use strict';
   
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])



app.controller('LoginController', ['$mdEditDialog', '$q', '$scope', '$timeout', 'LoginService', '$localStorage', '$mdDialog', 'toastr','$location',
    function ($mdEditDialog, $q, $scope,  $timeout, LoginService, $localStorage,  $mdDialog, toastr,$location) {
	
	$scope.isLoading = false;
	
	$scope.auth = function(data){
		$scope.isLoading = true;
		if (data.password) {
			data.password = btoa(data.password);
		}
		
		LoginService.auth(data, function (response) {
			$scope.isLoading = false;
			if (response.data) {
				$localStorage.currentUser = {token: response.data.token, pemission: response.data.permission, user: response.data.nome, tipo: response.data.tipo};
				//$http.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.token;
				//$scope.getData();
				$localStorage.login = true;
				window.location.href='/projeto-estagios/home.html';
				//$location.path('/home');
				
			}
		}, function (err) {
			data.password = "";
			toastr.warning("E-mail ou senha inválido");
			$scope.isLoading = false;
		})
	}
		
}]);
