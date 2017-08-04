(function(){
	'user strict';
	
	angular.module('projeto-estagios').factory('auth.interceptor', Interceptor);
	
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
	
	angular.module('projeto-estagios').config(HttpProvider);
	
	HttpProvider.$inject = ['$httpProvider'];
	function HttpProvider($httpProvider){
		$httpProvider.interceptors.push('auth.interceptor');
	}
})();