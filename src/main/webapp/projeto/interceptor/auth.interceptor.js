(function(){
	'user strict';
	
	angular.module('myApp').factory('auth.interceptor', Interceptor);
	
	Interceptor.$inject = ['$localStorage'];
	
	function Interceptor($localStorage){
		return {
			'request' : function(config){
				if($localStorage.currentUser){
					config.headers.Authorization = 'Bearer ' + $localStorage.currentUser.token;
				}
				return config;
			}
		}
	}
	
	angular.module('myApp').config(HttpProvider);
	
	HttpProvider.$inject = ['$httpProvider'];
	function HttpProvider($httpProvider){
		$httpProvider.interceptors.push('auth.interceptor');
	}
})();