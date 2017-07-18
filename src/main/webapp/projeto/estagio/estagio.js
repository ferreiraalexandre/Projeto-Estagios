
app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])

app.controller('estagioController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', '$location', 'EstagioService', 'toastr', 'Scopes',
                             function ($mdEditDialog,  $q,   $scope,   $timeout,   $mdDialog,   $location,   EstagioService,   toastr ,  Scopes) {
  
  Scopes.store('estagioController', $scope);//Armazena o scope no service para se utilizado por outra controller
  
  'use strict';
  $scope.title = "Adicionar Estudade"
  $scope.selecionados = [];
  $scope.limitOptions = [5, 10, 15];
  $scope.items = ['Nome', 'Curso', 'Empresa'];
  $scope.selectedItem;
  $scope.buttonAddDisabled = false;
  $scope.buttonEditDisabled = true;
  $scope.buttonRemoveDisabled = true;
  $scope.isLoading = true;
  $scope.$parent.rodape = true;
  $scope.$parent.links = true;
    
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
    
  $scope.toggleLimitOptions = function () {
    $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
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
  
  $scope.buttonEnable = function () {
	$scope.buttonAddDisabled = $scope.selecionados.length > 0;
	$scope.buttonEditDisabled = !($scope.selecionados.length == 1);
	$scope.buttonRemoveDisabled = $scope.selecionados.length == 0;
  };

  
//Função para mudar telas conforme menu 
  $scope.menuClick = function (link) {
	$location.path(link);
  };
	
//Função para mudar telas conforme menu 
  $scope.iconEstagio = function (icon) {
	  if(icon == "editar"){
		 $scope.icon = icon;
		 $location.path("/cadastroEstagio");
		  
	  }
  };
  
//////////função de deletar
	$scope.deleteEstagio = function(){	
		var arrayId = []; 
		for (var i = 0; i < $scope.selecionados.length; i++) {
			arrayId.push($scope.selecionados[i].id);
		}
		var listId ={
			data: JSON.stringify(arrayId),
		};
		EstagioService.deleteEstagio(listId, function(response){
			$scope.estagios = response.data;
			$scope.selecionados = []; 
			$scope.buttonEnable();
			toastr.success(response.message);
		});
	};

	//Busca estagios do banco e lista na tabela
	$scope.getEstagio = function () {
		EstagioService.getList(function (response) {
			$scope.estagios = response.data;
			$scope.isLoading = false;
			
		});		
	};


	//Chama função para buscar estagios
	$scope.getEstagio();
	
}]);
