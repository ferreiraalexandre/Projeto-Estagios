app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])


app.controller('institutoController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', 'InstitutoService', 'toastr',
                                function ($mdEditDialog, $q, $scope,  $timeout, $mdDialog, InstitutoService, toastr) {
  
  $scope.selecionados = [];  
  $scope.limitOptions = [5, 10, 15];
  $scope.selectedItem;
  $scope.institutosModal;
  $scope.buttonAddDisabled = false;
  $scope.buttonEditDisabled = true;
  $scope.buttonRemoveDisabled = true;
  $scope.isLoading = true;
    
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

///////////////////////////////////////////Busca instituto do banco e lista na tabela
  $scope.getInstituto = function(){;  
  	InstitutoService.getList(function (response) {
			$scope.institutos = response.data;
			$scope.isLoading = false;
		});
  }
	

  
////////////////////função de confirm pra deletar
  $scope.showConfirm = function(ev) {
	  var confirm = $mdDialog.confirm()
	  .title('EXCLUIR ')
	  .textContent('Tem certeza que deseja excluir o(s) institutos(s)?')
	  .targetEvent(ev)
	  .ok('SIM')
	  .cancel('NÃO');
	  
	  $mdDialog.show(confirm).then(function() {
		  $scope.deleteInstituto();
	  }, function() {
		  //SE APERTAR BOTAO NÃO ENTRA AQUI 		  
	  });
  };
 
//////////função de deletar
  $scope.deleteInstituto = function(){	
		var arrayId = []; 
		for (var i = 0; i < $scope.selecionados.length; i++) {
			arrayId.push($scope.selecionados[i].id);
		}
		var listId ={
				data: JSON.stringify(arrayId),
		};
		InstitutoService.deleteInstituto(listId, function(response){
			$scope.institutos = response.data;
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
		      templateUrl: 'projeto/instituto/institutoModal.html',
		      targetEvent: event,
		      clickOutsideToClose:true,
		      locals : {
	              retornoModal : $scope
	          }
		    })
	        .then(function(novoInstituto) {
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
				$scope.title = "Editar Instituto";
				$scope.instituto = angular.copy(retornoModal.selecionados[0]);
			}else{
				$scope.title = "Adicionar instituto";
				$scope.novo = true;
			}
		  
			$scope.hide = function() {
		      $mdDialog.hide();
		    };

		    $scope.cancel = function() {
		      $mdDialog.cancel();

		    };
	  
		   
	  $scope.novoInstituto = function (data) {
		  InstitutoService.postInstituto(data, function (response) {
				$mdDialog.hide(data);
				toastr.success(response.message);
				retornoModal.institutos = response.data;	
		}),
			function (error) {
				
				};
			
		};
		
/////////////////////////////////////Função de editar instituto no Banco de Dados
		$scope.editarInstituto = function (data) {
			InstitutoService.putInstituto(data, function (response) {
			$mdDialog.hide(data);
			toastr.success(response.message);
			retornoModal.institutos = response.data;
				
			}),
				function (error) {
		
				};
		};	
	}
			
//////////////////////////////////////////////////////////////////////////////Chama função para buscar institutos
			$scope.getInstituto();  			
  
}]);
