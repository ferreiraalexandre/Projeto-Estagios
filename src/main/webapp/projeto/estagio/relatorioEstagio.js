
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
	
}]);
