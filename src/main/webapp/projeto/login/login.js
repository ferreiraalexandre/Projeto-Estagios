var app = angular.module("projeto-estagios", ['ngMaterial', 'ngMessages','md.data.table', 'ngMdIcons', 'ngRoute', 'ui.mask', 'ngResource', 'ngAnimate', 'ngStorage', 'toastr', 'idf.br-filters']);

app.pathRest = 'rest';

app.config(function($routeProvider) {
	
	$routeProvider
	.when('/login', {
		templateUrl : 'projeto/login.html',
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
	
	
	$scope.auth = function(data){
		
		if (data.password) {
			data.password = btoa(data.password);
		}
		
		LoginService.auth(data, function (response) {
			if (response.data) {
				$localStorage.currentUser = {token: response.data.token, pemission: response.data.permission};
				//$http.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.token;
				//$scope.getData();
				$localStorage.login = true;
				window.location.href='/projeto-estagios/home.html';
				
			}
		}, function (err) {
			data.password = "";
			toastr.warning("E-mail ou senha inválido");
		})
	}
		
}]);
