app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])


app.controller('instituicaoController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', 'InstituicaoService', 'toastr',
                                function ($mdEditDialog, $q, $scope,  $timeout, $mdDialog, InstituicaoService, toastr) {
  
  $scope.selecionados = [];  
  $scope.limitOptions = [5, 10, 15];
  $scope.selectedItem;
  $scope.instituicoesModal;
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
  
///////////////////////////////////////////Busca Instituicao do banco e lista na tabela
  $scope.getInstituicao = function(){;  
  InstituicaoService.getList(function (response) {
			$scope.instituicoes = response.data;
			$scope.isLoading = false;
		});
  }
	

  
////////////////////função de confirm pra deletar
  $scope.showConfirm = function(ev) {
	  var confirm = $mdDialog.confirm()
	  .title('EXCLUIR ')
	  .textContent('Tem certeza que deseja excluir a(s) Instituição?')
	  .targetEvent(ev)
	  .ok('SIM')
	  .cancel('NÃO');
	  
	  $mdDialog.show(confirm).then(function() {
		  $scope.deleteInstituicao();
	  }, function() {
		  //SE APERTAR BOTAO NÃO ENTRA AQUI 		  
	  });
  };
 
//////////função de deletar
  $scope.deleteInstituicao = function(){	
		var arrayId = []; 
		for (var i = 0; i < $scope.selecionados.length; i++) {
			arrayId.push($scope.selecionados[i].id);
		}
		var listId ={
				data: JSON.stringify(arrayId),
		};
		InstituicaoService.deleteInstituicao(listId, function(response){
			$scope.instituicoes = response.data;
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
		      templateUrl: 'projeto/instituicao/instituicaoModal.html',
		      targetEvent: event,
		      clickOutsideToClose:true,
		      locals : {
	              retornoModal : $scope
	          }
		    })
	        .then(function(novoInstituicao) {
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
				$scope.title = "Editar Instituição";
				$scope.instituicao = angular.copy(retornoModal.selecionados[0]);
			}else{
				$scope.title = "Adicionar Instituição";
				$scope.novo = true;
			}
		  
			$scope.hide = function() {
		      $mdDialog.hide();
		    };

		    $scope.cancel = function() {
		      $mdDialog.cancel();

		    };
	  
		   
	  $scope.novoInstituicao = function (data) {
		  InstituicaoService.postInstituicao(data, function (response) {
			if(response.data != undefined){
			$mdDialog.hide(data);
			toastr.success(response.message);
			retornoModal.instituicoes = response.data;				
			}else{
				toastr.warning(response.message );
			}
		}),
			function (error) {
				
				};
			
		};
		
/////////////////////////////////////Função de editar Instituicao no Banco de Dados
		$scope.editarInstituicao = function (data) {
			InstituicaoService.putInstituicao(data, function (response) {
			$mdDialog.hide(data);
			toastr.success(response.message);
			retornoModal.instituicoes = response.data;
				
			}),
				function (error) {
		
				};
		};	
	}
			
//////////////////////////////////////////////////////////////////////////////Chama função para buscar instituicoes
			$scope.getInstituicao();  			
  
}]);
