app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])


app.controller('cursoController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', 'CursoService', 'toastr', '$localStorage',
                                function ($mdEditDialog, $q, $scope,  $timeout, $mdDialog, CursoService, toastr, $localStorage) {
  
  $scope.selecionados = [];  
  $scope.limitOptions = [5, 10, 15];
  $scope.selectedItem;
  $scope.cursosModal;
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
  
///////////////////////////////////////////Busca curso do banco e lista na tabela
  $scope.getCurso = function(){;  
		CursoService.getList(function (response) {
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					cursos: response.data,
			}
			$scope.isLoading = false;
		});
  }
  
////////////////////função de confirm pra deletar
  $scope.showConfirm = function(ev) {
	  var confirm = $mdDialog.confirm()
	  .title('EXCLUIR ')
	  .textContent('Tem certeza que deseja excluir o(s) Cursos(s)?')
	  .targetEvent(ev)
	  .ok('SIM')
	  .cancel('NÃO');
	  
	  $mdDialog.show(confirm).then(function() {
		  $scope.deleteCurso();
	  }, function() {
		  //SE APERTAR BOTAO NÃO ENTRA AQUI 		  
	  });
  };
 
//////////função de deletar
  $scope.deleteCurso = function(){	
		var arrayId = []; 
		for (var i = 0; i < $scope.selecionados.length; i++) {
			arrayId.push($scope.selecionados[i].id);
		}
		var listId ={
				data: JSON.stringify(arrayId),
		};
		CursoService.deleteCurso(listId, function(response){
			if(response.description != null){
				toastr.warning(response.description, response.message );
			}else{
				toastr.success(response.message);				
			}
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					cursos: response.data,
			}
			$scope.selecionados = []; 
			$scope.buttonEnable();					
		});		
	};
	
	
//////////////////////////////////////Abrir Modal
	  $scope.abrirModal = function(event) {
		    $mdDialog.show({
		      controller: ModalController,
		      templateUrl: 'projeto/curso/modalCurso.html',
		      targetEvent: event,
		      clickOutsideToClose:true,
		      locals : {
	              retornoModal : $scope
	          }
		    })
	        .then(function(novoCurso) {
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
				$scope.title = "Editar Curso";
				$scope.curso = angular.copy(retornoModal.selecionados[0]);
			}else{
				$scope.title = "Adicionar Curso";
				$scope.novo = true;
			}
		  
			$scope.hide = function() {
		      $mdDialog.hide();
		    };

		    $scope.cancel = function() {
		      $mdDialog.cancel();

		    };
	  
		   
	  $scope.novoCurso = function (data) {
			CursoService.postCurso(data, function (response) {
				if(response.data != undefined){
					$mdDialog.hide(data);
					toastr.success(response.message);
					retornoModal.data = {
							count : response.data != undefined ? response.data.length : null,
							cursos: response.data,
					}				
				}else{
					toastr.warning(response.message );
				}
		}),
			function (error) {
				
				};
			
		};
		
/////////////////////////////////////Função de editar usuario no Banco de Dados
		$scope.editarCurso = function (data) {
			CursoService.putCurso(data, function (response) {
				if(response.data != undefined){
					$mdDialog.hide(data);
					toastr.success(response.message);
					retornoModal.data = {
							count : response.data != undefined ? response.data.length : null,
							cursos: response.data,
					}				
				}else{
					toastr.warning(response.message );
				}
			}),
				function (error) {
		
				};
		};	
	}
			
//////////////////////////////////////////////////////////////////////////////Chama função para buscar cursos
			$scope.getCurso();  			
  
}]);
