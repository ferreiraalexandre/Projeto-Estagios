app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])


app.controller('empresaController', ['$mdEditDialog', '$q','$scope', '$timeout', '$mdDialog', 'EmpresaService', 'UsuarioService' , 'toastr', '$location', '$localStorage',  
                            function ($mdEditDialog,   $q,  $scope,   $timeout,   $mdDialog,   EmpresaService,   UsuarioService,    toastr, $location, $localStorage) {
	
	$scope.selecionados = [];
	$scope.limitOptions = [5, 10, 15];
	$scope.items = ['Empresa'];
	$scope.selectedItem;
	$scope.empresaModal;
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
	  	  
	////////////////////função de confirm pra deletar
	$scope.showConfirm = function(ev) {
		var confirm = $mdDialog.confirm()
		.title('EXCLUIR ')
		.textContent('Tem certeza que deseja excluir essa empresa?')
		.targetEvent(ev)
		.ok('SIM')
		.cancel('NÃO');
		  
		$mdDialog.show(confirm).then(function() {
			$scope.deleteEmpresa();
			$scope.status = 'Deletado';
		}, function() {
			$scope.status = 'Erro ao deletar';
		});
	};
	 
	//////////função de deletar
	$scope.deleteEmpresa = function(){	
		var arrayId = []; 
		for (var i = 0; i < $scope.selecionados.length; i++) {
			arrayId.push($scope.selecionados[i].id);
		}
		var listId ={
				data: JSON.stringify(arrayId),
		};
		EmpresaService.deleteEmpresa(listId, function(response){
			if(response.description != null){
				toastr.warning(response.description, response.message );
			}else{
				toastr.success(response.message);				
			}
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					empresas: response.data,
			}
			$scope.selecionados = []; 
			$scope.buttonEnable();
		
					
		});	
	};

	//Abrir Modal
	$scope.abrirModal = function(event) {
		$mdDialog.show({
			controller: ModalController,
		    templateUrl: 'projeto/empresa/modalEmpresa.html',
		    targetEvent: event,
		    clickOutsideToClose:true,
		    locals : {
		    	retornoModal : $scope
	        }
		 })
	     .then(function(novoEmpresa) {
	    	 $scope.selecionados = [];
	    	 $scope.buttonEnable();
	        }, function() {
	        //Adicionar mensagem de erro aqui
	        });
	};
	    	
	//Busca empresas do banco e lista na tabela
	$scope.getEmpresa = function () {
		EmpresaService.getList(function (response) {
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					empresas: response.data,
			}
			$scope.isLoading = false;
		});
		UsuarioService.getList(function (response) {
			$scope.usuarios = response.data;
		});
	};
			
	//Controller da modal
	function ModalController($scope, $mdDialog,retornoModal) {
		if(retornoModal.selecionados.length==1){
			$scope.editar = true;
			$scope.title = "Editar Empresa";
			$scope.empresa = angular.copy(retornoModal.selecionados[0]);
			$scope.empresa.data =  retornoModal.selecionados[0].data ? new Date(retornoModal.selecionados[0].data) : null;
			$scope.usuarios = retornoModal.usuarios;
		}else{
			$scope.title = "Adicionar Empresa";
			$scope.novo = true;
			$scope.usuarios = retornoModal.usuarios;
		}
		  
		$scope.hide = function() {
		     $mdDialog.hide();
		};

		$scope.cancel = function() {
			$mdDialog.cancel();
		};
			    
		//Função de adicionar novas empresas no Banco de Dados
		$scope.novaEmpresa = function (data) {
			EmpresaService.postEmpresa(data, function (response) {
				if(response.data != undefined){
					$mdDialog.hide(data);
					toastr.success(response.message);
					retornoModal.data = {
							count : response.data != undefined ? response.data.length : null,
							empresas: response.data,
					}
				}else{
					toastr.warning(response.message );
				}
			}),
			function (error) {
	
			};
		};
			
		//Função de editar empresa no Banco de Dados
		$scope.editarEmpresa = function (data) {
			EmpresaService.putEmpresa(data, function (response) {
				if(response.data != undefined){
					$mdDialog.hide(data);
					toastr.success(response.message);
					retornoModal.data = {
							count : response.data != undefined ? response.data.length : null,
							empresas: response.data,
					}
				}else{
					toastr.warning(response.message );
				}
			}),
			function (error) {

			};
		};

	}

	//Chama função para buscar usuarios
	$scope.getEmpresa();
	
}]);
