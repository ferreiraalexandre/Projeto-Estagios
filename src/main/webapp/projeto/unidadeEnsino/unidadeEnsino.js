app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])


app.controller('unidadeEnsinoController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', 'UnidadeEnsinoService', 'toastr',
                                function ($mdEditDialog, $q, $scope,  $timeout, $mdDialog, UnidadeEnsinoService, toastr) {
  
  $scope.selecionados = [];  
  $scope.limitOptions = [5, 10, 15];
  //$scope.items = ['Nome', 'Curso', 'Empresa'];
  $scope.selectedItem;
  $scope.unidadesModal;
  $scope.buttonAddDisabled = false;
  $scope.buttonEditDisabled = true;
  $scope.buttonRemoveDisabled = true;
    
  $scope.options = {
    rowSelection: true,
    multiSelect: true,
    autoSelect: true,
    decapitate: false,
    largeEditDialog: false,
    boundaryLinks: false,
    limitSelect: true,
    pageSelect: true
  };
  
  $scope.query = {
    order: 'name',
    limit: 10,
    page: 1
  };
  
  $scope.toggleLimitOptions = function () {
    $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
  };
        
  $scope.buttonEnable = function () {
	    $scope.buttonAddDisabled = $scope.selecionados.length > 0;
	    $scope.buttonEditDisabled = !($scope.selecionados.length == 1);
	    $scope.buttonRemoveDisabled = $scope.selecionados.length == 0;
	  };
  
  $scope.logOrder = function (order) {
    console.log('order: ', order);
  };
  
  $scope.logPagination = function (page, limit) {
    console.log('page: ', page);
    console.log('limit: ', limit);
  }

///////////////////////////////////////////Busca unidade do banco e lista na tabela
  $scope.getUnidadeEnsino = function(){;  
		UnidadeEnsinoService.getList(function (response) {
			$scope.unidades = response.data;	
		});
  }
	

  
////////////////////função de confirm pra deletar
  $scope.showConfirm = function(ev) {
	  var confirm = $mdDialog.confirm()
	  .title('EXCLUIR ')
	  .textContent('Tem certeza que deseja excluir a(s) Unidade(s) de Ensino?')
	  .targetEvent(ev)
	  .ok('SIM')
	  .cancel('NÃO');
	  
	  $mdDialog.show(confirm).then(function() {
		  $scope.deleteUnidade();
		  $scope.status = 'Deletado';
	  }, function() {
		  $scope.status = 'Deu erro ao deletar';
	  });
  };
 
//////////função de deletar
  $scope.deleteUnidade = function(){	
		var arrayId = []; 
		for (var i = 0; i < $scope.selecionados.length; i++) {
			arrayId.push($scope.selecionados[i].id);
		}
		var listId ={
				data: JSON.stringify(arrayId),
		};
		UnidadeEnsinoService.deleteUnidade(listId, function(response){
			$scope.unidades = response.data;
			toastr.success(response.message);
			$scope.selecionados = []; 
			$scope.buttonEnable();
		});
	};
	
	
//////////////////////////////////////Abrir Modal
	  $scope.abrirModal = function(event) {
		    $mdDialog.show({
		      controller: ModalController,
		      templateUrl: 'projeto/unidadeEnsino/modalUnidade.html',
		      targetEvent: event,
		      clickOutsideToClose:true,
		      locals : {
	              retornoModal : $scope
	          }
		    })
	        .then(function(novaUnidade) {
	        	$scope.selecionados = [];
	        	//$scope.getUnidadeEnsino();
	        }, function() {
	         //Adicionar mensagem de erro aqui
	        });
	  };
	  
			
///////////////////////////////////////////////////Controller da modal
		function ModalController($scope, $mdDialog,retornoModal) {
		
			if(retornoModal.selecionados.length==1){
				$scope.editar = true;
				$scope.title = "Editar Unidade de Ensino";
				$scope.unidade = angular.copy(retornoModal.selecionados[0]);
			}else{
				$scope.title = "Adicionar Unidade de Ensino";
				$scope.novo = true;
			}
		  
			$scope.hide = function() {
		      $mdDialog.hide();
		    };

		    $scope.cancel = function() {
		      $mdDialog.cancel();

		    };
	  
		   
	  $scope.novaUnidade = function (data) {
			UnidadeEnsinoService.postUnidade(data, function (response) {
				$mdDialog.hide(data);
				toastr.success(response.message);
				//toastr.RefreshTimer (toast, 500 ); pesquisar.......
				retornoModal.unidades = response.data;	
		}),
			function (error) {
				
				};
			
		};
		
/////////////////////////////////////Função de editar usuario no Banco de Dados
		$scope.editarUnidade = function (data) {
			UnidadeEnsinoService.putUnidade(data, function (response) {
			$mdDialog.hide(data);
			toastr.success(response.message);
			retornoModal.unidades = response.data;
				
			}),
				function (error) {
		
				};
		};	
	}
			
//////////////////////////////////////////////////////////////////////////////Chama função para buscar unidades de ensino
			$scope.getUnidadeEnsino();  			
  
}]);
