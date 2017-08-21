app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])

app.controller('usuarioController', ['$mdEditDialog', '$q','$scope', '$timeout', '$mdDialog', 'UsuarioService', 'UnidadeEnsinoService', 'toastr', '$localStorage',  
                            function ($mdEditDialog,   $q,  $scope,   $timeout,   $mdDialog,   UsuarioService,   UnidadeEnsinoService,   toastr, $localStorage) {

  $scope.selecionados = [];
  $scope.limitOptions = [5, 10, 15];
  $scope.items = ['Nome', 'Curso', 'Empresa'];
  $scope.selectedItem;
  $scope.usuariosModal;
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

////////////////////Função para Desabilitar botões 
  $scope.buttonEnable = function () {
	  $scope.buttonAddDisabled = $scope.selecionados.length > 0;
	  $scope.buttonEditDisabled = !($scope.selecionados.length == 1);
	  $scope.buttonRemoveDisabled = $scope.selecionados.length == 0;
  };

  
////////////////////função de confirm pra deletar
  $scope.showConfirm = function(ev) {
	  var confirm = $mdDialog.confirm()
	  .title('EXCLUIR ')
	  .textContent('Tem certeza que deseja excluir o(s) Usuário(s)?')
	  .targetEvent(ev)
	  .ok('SIM')
	  .cancel('NÃO');
	  
	  $mdDialog.show(confirm).then(function() {
		  $scope.deleteUsuario();
		  $scope.status = 'Deletado';
	  }, function() {
		  $scope.status = 'Deu erro ao deletar';
	  });
  };
 
////////////////////função de deletar
  $scope.deleteUsuario = function(){	
		var arrayId = []; 
		for (var i = 0; i < $scope.selecionados.length; i++) {
			arrayId.push($scope.selecionados[i].id);
		}
		var listId ={
				data: JSON.stringify(arrayId),
		};
		UsuarioService.deleteUsuario(listId, function(response){
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					usuarios:response.data,
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

////////////////////Abrir Modal
  $scope.abrirModal = function(event) {
	    $mdDialog.show({
	      controller: ModalController,
	      templateUrl: 'projeto/usuario/modalUsuario.html',
	      targetEvent: event,
	      clickOutsideToClose:true,
	      locals : {
              retornoModal : $scope
          }
	    })
        .then(function(novoUsuario) {
        	$scope.selecionados = [];
        	$scope.buttonEnable();
        }, function() {
         //Adicionar mensagem de erro aqui
        });
  };
    	
////////////////////Busca usuários do banco e lista na tabela
	$scope.getUsuario = function () {
		UsuarioService.getList(function (response) {
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					usuarios:response.data,
			}
			$scope.isLoading = false;
		});
		UnidadeEnsinoService.getList(function (response) {
			$scope.unidades = response.data;	
		});
		
	};
		
////////////////////Controller da modal
	function ModalController($scope, $mdDialog,retornoModal) {
		if(retornoModal.selecionados.length==1){
			$scope.editar = true;
			$scope.title = "Editar Usuário";
			$scope.usuario = angular.copy(retornoModal.selecionados[0]);
			$scope.unidades = retornoModal.unidades;
			$scope.selectRequired = false;
		}else{
			$scope.selectRequired = true;
			$scope.title = "Adicionar Usuário";
			$scope.novo = true;
			$scope.unidades = retornoModal.unidades;
		}
	  
		$scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.cancel = function() {
	      $mdDialog.cancel();
	    };
		    
////////////////////Função de adicionar novos usuario no Banco de Dados
		$scope.novoUsuario = function (data) {
			data.senha = btoa(data.senha);
			UsuarioService.postUsuario(data, function (response) {
				if(response.cause != undefined){
					toastr.warning(response.message );
					
				}else{
					$mdDialog.hide(data);
					toastr.success(response.message);
					retornoModal.data = {
							count : response.data != undefined ? response.data.length : null,
									usuarios: response.data,
					}
				}
				
			}),
				function (error) {
		
				};
		};
		
////////////////////Função de editar usuario no Banco de Dados
		$scope.editarUsuario = function (data) {
			data.senha = btoa(data.senha);
			UsuarioService.putUsuario(data, function (response) {
				if(response.data != undefined){
					$mdDialog.hide(data);
					toastr.success(response.message);
					retornoModal.data = {
							count : response.data != undefined ? response.data.length : null,
							usuarios: response.data,
					}
				}else{
					toastr.warning(response.message );
				}
					
				}),
				function (error) {
		
				};
		};

	  }

////////////////////Chama função para buscar usuarios
	$scope.getUsuario();
	

}]);
