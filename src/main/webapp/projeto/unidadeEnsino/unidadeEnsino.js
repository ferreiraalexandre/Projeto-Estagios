app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])


app.controller('unidadeEnsinoController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', 'UnidadeEnsinoService', 'toastr', '$localStorage',
                                function ($mdEditDialog, $q, $scope,  $timeout, $mdDialog, UnidadeEnsinoService, toastr, $localStorage) {
  
  $scope.selecionados = [];  
  $scope.limitOptions = [5, 10, 15];
  //$scope.items = ['Nome', 'Curso', 'Empresa'];
  $scope.selectedItem;
  $scope.unidadesModal;
  $scope.buttonAddDisabled = false;
  $scope.buttonEditDisabled = true;
  $scope.buttonRemoveDisabled = true;
  $scope.isLoading = true;
  
////////////////////FUNÇÃO DE CONFIGURAÇÃO DA TABELA   
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

////////////////////Controla Permisão de Cada Tipo de Usuario
  if($localStorage.currentUser.tipo == "Orientador") {
	  $scope.autorizacao = true;
  }
        
  $scope.buttonEnable = function () {
	    $scope.buttonAddDisabled = $scope.selecionados.length > 0;
	    $scope.buttonEditDisabled = !($scope.selecionados.length == 1);
	    $scope.buttonRemoveDisabled = $scope.selecionados.length == 0;
	  };
  

//////////////////////Busca unidade do banco e lista na tabela
  $scope.getUnidadeEnsino = function(){;  
		UnidadeEnsinoService.getList(function (response) {
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					unidades: response.data,
			}
			$scope.isLoading = false;
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
	  }, function() {
		  //SE APERTAR BOTAO NÃO ENTRA AQUI 		  
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
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					unidades: response.data,
			}
			if(response.description != null){
				toastr.warning(response.description, response.message );
			}else{
				toastr.success(response.message);				
			}
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
	        	$scope.buttonEnable();
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
			if(response.data != undefined){
				$mdDialog.hide(data);
				toastr.success(response.message);
				retornoModal.data = {
						count : response.data != undefined ? response.data.length : null,
						unidades: response.data,
				}
			}else{
				toastr.warning(response.message );
			}
		}),
			function (error) {
				
				};
			
		};
		
/////////////////////////////////////Função de editar usuario no Banco de Dados
		$scope.editarUnidade = function (data) {
			UnidadeEnsinoService.putUnidade(data, function (response) {				
				if(response.data != undefined){
					$mdDialog.hide(data);
					toastr.success(response.message);
					retornoModal.data = {
							count : response.data != undefined ? response.data.length : null,
							unidades: response.data,
					}
				}else{
					toastr.warning(response.message );
				}
			}),
				function (error) {
		
				};
		};	
	}
			
///////////////////////////////////Chama função para buscar unidades de ensino
	$scope.getUnidadeEnsino();  			
  
}]);
