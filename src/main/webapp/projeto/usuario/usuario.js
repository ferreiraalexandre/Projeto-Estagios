app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])

app.controller('usuarioController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', 'UsuarioService', 'UnidadeEnsinoService', 'ToastService',  
                            function ($mdEditDialog,   $q,   $scope,   $timeout,   $mdDialog,   UsuarioService,   UnidadeEnsinoService,   ToastService) {

  $scope.selecionados = [];
  $scope.limitOptions = [5, 10, 15];
  $scope.items = ['Nome', 'Curso', 'Empresa'];
  $scope.selectedItem;
  $scope.usuariosModal;
  $scope.buttonAddDisabled = false;
  $scope.buttonEditDisabled = true;
  $scope.buttonRemoveDisabled = true;
  
  $scope.getSelectedText = function() {
      if ($scope.selectedItem !== undefined) {
        return "Buscar por " + $scope.selectedItem;
      } else {
        return "Buscar por Nome";
      }
    };
  
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
    
  $scope.editObs = function (event, usuario) {
    event.stopPropagation(); // in case autoselect is enabled
    
    var editDialog = {
      modelValue: usuario.obs,
      placeholder: 'Adicionar observações',
      save: function (input) {
        if(input.$modelValue === 'Donald Trump') {
          input.$invalid = true;
          return $q.reject();
        }
        if(input.$modelValue === 'Bernie Sanders') {
          return usuario.obs = 'FEEL THE BERN!'
        }
        usuario.obs = input.$modelValue;
      },
      targetEvent: event,
      title: 'Adicionar observações',
      validators: {
        'md-maxlength': 30
      }
    };
    
    var promise;
    
    if($scope.options.largeEditDialog) {
      promise = $mdEditDialog.large(editDialog);
    } else {
      promise = $mdEditDialog.small(editDialog);
    }
    
    promise.then(function (ctrl) {
      var input = ctrl.getInput();
      
      input.$viewChangeListeners.push(function () {
        input.$setValidity('test', input.$modelValue !== 'test');
      });
    });
  };
  
  $scope.toggleLimitOptions = function () {
    $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
  };
  
  $scope.getInstituicaoes = function () {
    return ['AC Serviços de Recrutamento e seleção LTDA ME', 'Senai', 'IEL'];
  };
  
  $scope.getOpcao = function () {
	    return ['Sim', 'Não'];
	  };
  
  $scope.loadStuff = function () {
    $scope.promise = $timeout(function () {
      // loading
    }, 2000);
  }
  
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
 
//////////função de deletar
  $scope.deleteUsuario = function(){	
		var arrayId = []; 
		for (var i = 0; i < $scope.selecionados.length; i++) {
			arrayId.push($scope.selecionados[i].id);
		}
		var listId ={
				data: JSON.stringify(arrayId),
		};
			UsuarioService.deleteUsuario(listId, function(response){
			ToastService.alert(response.message, undefined, 'bottom right', 3000);
			$scope.usuarios = response.data;
			$scope.selecionados = []; 
			$scope.buttonEnable();
		});
	};
	

   
 //Abrir Modal
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
        	$scope.getUsuario();
        }, function() {
         //Adicionar mensagem de erro aqui
        });
  };
    	
	//Busca usuários do banco e lista na tabela
	$scope.getUsuario = function () {
		UsuarioService.getList(function (response) {
			$scope.usuarios = response.data;	
		});
		
		UnidadeEnsinoService.getList(function (response) {
			$scope.unidades = response.data;	
		});

	};
		
	//Controller da modal
	function ModalController($scope, $mdDialog,retornoModal) {
		if(retornoModal.selecionados.length==1){
			$scope.editar = true;
			$scope.title = "Editar Usuário";
			$scope.usuario = angular.copy(retornoModal.selecionados[0]);
			$scope.unidades = retornoModal.unidades;
		}else{
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
		    
		//Função de adicionar novos usuario no Banco de Dados
		$scope.novoUsuario = function (data) {
			UsuarioService.postUsuario(data, function (response) {
			$mdDialog.hide(data);
			ToastService.alert(response.message, undefined, 'bottom right', 3000);
			retornoModal.usuarios = response.data;
				
			}),
				function (error) {
		
				};
		};
		
		//Função de editar usuario no Banco de Dados
		$scope.editarUsuario = function (data) {
			UsuarioService.putUsuario(data, function (response) {
			$mdDialog.hide(data);
			ToastService.alert(response.message, undefined, 'bottom right', 3000);
			retornoModal.unidades = response.data;
				
			}),
				function (error) {
		
				};
		};

	  }

	//Chama função para buscar usuarios
	$scope.getUsuario();

}]);
