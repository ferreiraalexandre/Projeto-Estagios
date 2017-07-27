app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])



app.controller('LoginController', ['$mdEditDialog', '$q', '$scope', '$timeout', 'LoginService', '$mdDialog', 'toastr',
    function ($mdEditDialog, $q, $scope,  $timeout, LoginService, $mdDialog, toastr) {
	
	
	$scope.auth = function(data){
		
		if (data.password) {
			data.password = btoa(data.password);
		}
		console.log(data);
		LoginService.auth(data, function (response) {
			if (response.data) {
				//$localStorage.currentUser = { email: User.email, token: response.data.token, permission: response.data.permission, adm: response.data.adm };
				//$scope.getData();
				console.log(response);
				
			}
		}, function (err) {
			data.password = "";
			toastr.info(err.data.message);
		})
	}
		
}]);
