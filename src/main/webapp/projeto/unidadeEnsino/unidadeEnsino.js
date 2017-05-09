app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])

app.controller('unidadeEnsinoController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', 'UnidadeEnsinoService',
                                           function ($mdEditDialog, $q, $scope, $timeout, $mdDialog, UnidadeEnsinoService) {
  'use strict';
  
  $scope.selected = [];  
  $scope.limitOptions = [5, 10, 15];
  $scope.items = ['Nome', 'Curso', 'Empresa'];
  $scope.selectedItem;
  
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
    limit: 5,
    page: 1
  };
  
  
  $scope.editObs = function (event, unidadeEnsino) {
    event.stopPropagation(); // in case autoselect is enabled
    
    var editDialog = {
      modelValue: unidadeEnsino.obs,
      placeholder: 'Adicionar observações',
      save: function (input) {
        if(input.$modelValue === 'Donald Trump') {
          input.$invalid = true;
          return $q.reject();
        }
        if(input.$modelValue === 'Bernie Sanders') {
          return unidadeEnsino.obs = 'FEEL THE BERN!'
        }
        unidadeEnsino.obs = input.$modelValue;
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
  
  $scope.logItem = function (item) {
    console.log(item.name, 'was selected');
  };
  
  $scope.logOrder = function (order) {
    console.log('order: ', order);
  };
  
  $scope.logPagination = function (page, limit) {
    console.log('page: ', page);
    console.log('limit: ', limit);
  }
  
  
  $scope.addItem = function (event) {
	    $mdDialog.show({
	      clickOutsideToClose: true,
	      controllerAs: 'ctrl',
	      focusOnOpen: false,
	      targetEvent: event,
	      templateUrl: 'projeto/unidadeEnsino/modalUnidade.html',
	    }).then($scope.getDesserts);
	  };
	  
	  
	  $scope.novaUnidade = function (data) {
			UnidadeEnsinoService.postUnidade(data, function (response) {
				$mdDialog.cancel();
//				location.reload();
				$scope.getUnidadeEnsino();
				ToastService.alert('Unidadde adicionada com sucesso!', undefined, 'bottom left', 3000);
		}),
			function (error) {
				
				};
			
		};
		
	
		
	//Busca unidades de ensino do banco e lista na tabela
	$scope.getUnidadeEnsino = function () {
		UnidadeEnsinoService.getList(function (response) {
			$scope.unidadeEnsino = response.data;	
		});
	};
	
	
	//Fechar modal no botão cancelar
	$scope.cancel = function () {
		$mdDialog.cancel();
	};
	
	$scope.deleteUnidade = function(){	// Ver aqui!!!!!
		var arrayId = []; 
		for (var i = 0; i < $scope.selected.length; i++) {
			arrayId.push($scope.selected[i].id);
		} 
		 
		 		var listId ={
		 				data: JSON.stringify(arrayId),
			     };
			
		 
		 UnidadeEnsinoService.deleteUnidade(listId, function(response){
			 ToastService.alert('Unidadde adicionada com sucesso!', undefined, 'top right', 3000);
		 });
			
		
	};
		
	

	//Chama função para buscar unidades de ensino
	$scope.getUnidadeEnsino();
  
  
}]);
