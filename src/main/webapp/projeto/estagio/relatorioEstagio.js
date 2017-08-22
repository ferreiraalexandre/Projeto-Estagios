
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
  
////////////////////FUNÇÃO DE CONFIGURAÇÃO DA TABELA   
  $scope.options = {
    rowSelection: true,
    multiSelect: true,
    autoSelect: true,
    decapitate: false,
    largeEditDialog: false,
    boundaryLinks: false,
    limitSelect: true,
    pageSelect: false
  };
  
  $scope.query = {
    order: 'name',
    limit: 10,
    page: 1
  };
      
  $scope.toggleLimitOptions = function () {
    $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
  };
  
  $scope.getOpcao = function () {
	    return ['Sim', 'Não'];
	  };
    
  
  $scope.logOrder = function (order) {
    console.log('order: ', order);
  };
  
  $scope.logPagination = function (page, limit) {
    console.log('page: ', page);
    console.log('limit: ', limit);
  }

  
	//Gerar relatorio 
	$scope.getRelatorio = function () {
		EstagioService.getRelatorio(function (response) {
			var data = JSON.parse(response.data);
			$scope.relatorioEmpresa = {
					count : data.empresa != undefined ? data.empresa.length : null,
					empresas: data.empresa,
			}
			$scope.relatorioTurma = {
					count : data.turma != undefined ? data.turma.length : null,
					turmas: data.turma,
			}
			$scope.relatorioRescisao = data.rescisao;
		});		
	};
	
	// Função do botão "VOLTAR"
	$scope.voltar = function () {
		window.location.href="/projeto-estagios/home.html";
		
	};

	
  $scope.getRelatorio();
	
}]);
