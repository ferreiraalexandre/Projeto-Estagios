
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
  
  
	//Gerar relatorio 
	$scope.getRelatorio = function () {
		EstagioService.getRelatorio(function (response) {
			var data = JSON.parse(response.data);
			$scope.relatorioEmpresa = data.empresa;
			$scope.relatorioRescisao = data.rescisao;
			$scope.relatorioTurma = data.turma;
			
		});		
	};
	
	// Função do botão "VOLTAR"
	$scope.voltar = function () {
		window.location.href="/projeto-estagios/home.html";
		
	};

	
  $scope.getRelatorio();
	
}]);
