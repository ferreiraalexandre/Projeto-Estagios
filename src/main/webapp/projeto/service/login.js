app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])



app.controller('LoginController', ['$mdEditDialog', '$q', '$scope', '$timeout', 'LoginService', '$mdDialog', 'toastr',
    function ($mdEditDialog, $q, $scope,  $timeout, LoginService, $mdDialog, toastr) {
	
	
	$scope.auth = function(data){
		
		LoginService.auth(function (response) {
			console.log(response);
			
		});		
	}
	
}]);
