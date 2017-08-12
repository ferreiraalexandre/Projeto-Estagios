
app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])

app.controller('relatorioEstagioController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', '$location', 'EstagioService', 'toastr', 'Scopes',
                                    function ($mdEditDialog,   $q,   $scope,   $timeout,   $mdDialog,   $location,   EstagioService,   toastr, Scopes) {
  'use strict';


  $scope.$parent.rodape = false;
  $scope.$parent.links = false;
  $scope.cardCadastroEstagio = {"margin-top" : "60px"};
  
  
	 var data = [ {
	      "empresa" : 32,
	      "total" : "rrr",
	    
	  	},
	  	{"empresa" : 32,
	      "total" : "rrr",
	    
	  	}, 
	  	{"empresa" : 32,
		      "total" : "rrr",
		    
		  	}, 
		  	{"empresa" : 32,
			      "total" : "rrr",
			    
			  	}, 
			  	{"empresa" : 32,
				      "total" : "rrr",
				    
				  	}, 

	 ]
	
  $scope.estagiosVencendo = data;
	
}]);
