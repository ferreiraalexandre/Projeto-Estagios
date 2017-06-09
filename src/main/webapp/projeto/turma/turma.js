app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])

app.controller('turmaController', ['$mdEditDialog', '$q','$scope', '$timeout', '$mdDialog', 'TurmaService', 'CursoService', 'toastr',  
                          function ($mdEditDialog,   $q,  $scope,   $timeout,   $mdDialog,   TurmaService,   CursoService,   toastr) {

  $scope.selecionados = [];
  $scope.limitOptions = [5, 10, 15];
  $scope.items = ['Nome', 'Curso', 'Empresa'];
  $scope.selectedItem;
  $scope.turmasModal;
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
  
  $scope.getOpcao = function () {
	    return ['Sim', 'Não'];
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
  
////////////////////função de confirm pra deletar
  $scope.showConfirm = function(ev) {
	  var confirm = $mdDialog.confirm()
	  .title('EXCLUIR ')
	  .textContent('Tem certeza que deseja excluir a(s) Turma(s)?')
	  .targetEvent(ev)
	  .ok('SIM')
	  .cancel('NÃO');
	  
	  $mdDialog.show(confirm).then(function() {
		  $scope.deleteTurma();
		  $scope.status = 'Deletado';
	  }, function() {
		  $scope.status = 'Deu erro ao deletar';
	  });
  };
 
//////////função de deletar
  $scope.deleteTurma = function(){	
		var arrayId = []; 
		for (var i = 0; i < $scope.selecionados.length; i++) {
			arrayId.push($scope.selecionados[i].id);
		}
		var listId ={
				data: JSON.stringify(arrayId),
		};
			TurmaService.deleteTurma(listId, function(response){
			$scope.turmas = response.data;
			$scope.selecionados = []; 
			$scope.buttonEnable();
			toastr.success(response.message);
		});
	};
	

   
 //Abrir Modal
  $scope.abrirModal = function(event) {
	    $mdDialog.show({
	      controller: ModalController,
	      templateUrl: 'projeto/turma/modalTurma.html',
	      targetEvent: event,
	      clickOutsideToClose:true,
	      locals : {
              retornoModal : $scope
          }
	    })
        .then(function(novaTurma) {
        	$scope.selecionados = [];
        	$scope.buttonEnable();
        }, function() {
         //Adicionar mensagem de erro aqui
        });
  };
    	
	//Busca usuários do banco e lista na tabela
  $scope.getTurma = function () {
		TurmaService.getList(function (response) {
			$scope.turmas = response.data;
			$scope.isLoading = false;
		});
		CursoService.getList(function (response) {
			$scope.cursos = response.data;	
		});
		
	};
		
	//Controller da modal
	function ModalController($scope, $mdDialog,retornoModal) {
		if(retornoModal.selecionados.length==1){
			$scope.editar = true;
			$scope.title = "Editar Turma";
			$scope.turma = angular.copy(retornoModal.selecionados[0]);
			$scope.cursos = retornoModal.cursos;
			$scope.selectRequired = false;
		}else{
			$scope.selectRequired = true;
			$scope.title = "Adicionar Turma";
			$scope.novo = true;
			$scope.cursos = retornoModal.cursos;
		}
	  
		$scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.cancel = function() {
	      $mdDialog.cancel();
	    };
		    
		//Função de adicionar novas turma no Banco de Dados
		$scope.novaTurma = function (data) {
			TurmaService.postTurma(data, function (response) {
			$mdDialog.hide(data);
			toastr.success(response.message);
			retornoModal.turmas = response.data;
				
			}),
				function (error) {
		
				};
		};
		
		//Função de editar turma no Banco de Dados
		$scope.editarTurma = function (data) {
			
			TurmaService.putTurma(data, function (response) {
			$mdDialog.hide(data);
			toastr.success(response.message);
			retornoModal.turmas = response.data;
				
			}),
				function (error) {
		
				};
		};

	  }

	//Chama função para buscar turmas
	$scope.getTurma();

}]);
