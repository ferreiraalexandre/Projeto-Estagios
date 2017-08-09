
app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])

app.controller('estagioController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', '$mdSidenav', '$mdUtil', '$location', 'EstagioService', 'toastr', 'Scopes', '$rootScope', '$location', '$localStorage',
                             function ($mdEditDialog,  $q,   $scope,   $timeout,   $mdDialog,  $mdSidenav, $mdUtil,   $location,   EstagioService,   toastr ,  Scopes, $rootScope, $location, $localStorage) {
  
  Scopes.store('estagioController', $scope);//Armazena o scope no service para se utilizado por outra controller
  
  app.run($rootScope, $location, $localStorage);
  
  
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
  $scope.estagioVencendo;
  
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
  

	$scope.logout = function(){
		$localStorage.$reset();
		window.location.href="/projeto-estagios/login.html";
	}
  
  $scope.query = {
    order: 'name',
    limit: 10,
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
		});
	};  

	
	//Busca estagios do banco e lista na tabela
	$scope.getEstagio = function () {
		EstagioService.getList(function (response) {
			$scope.estagios = response.data;
			$scope.isLoading = false;
			
		});		
	};
	
	$scope.openRightMenu = function() {
       $mdSidenav('right').toggle();
       $scope.dataInicio = new Date();
       var dataFim = new Date();
       dataFim.setDate($scope.dataInicio.getDate() + 10);
       $scope.dataFim = dataFim;
       
       var turma = [];
       $scope.turmas = turma.push($scope.estagios[0].turma);
       for (var int = 0; int < $scope.estagios.length; int++) {
		
    	   for (var i = 0; i < turma.length; i++) {
			
    		   if(!($scope.estagios[int].turma.id == turma[i].id)){
    			   turma.push($scope.estagios[int].turma);
    			   $scope.turmas = turma;
    		   }	   
    	   }
       }
    };
    
    $scope.closeRightMenu = function() {
    	$mdSidenav('right').close(); 
     };

   
	//Função de Aplicar Filtro 
	$scope.aplicarFiltro = function (dataInicio, dataFim, turma) {
		 var data = {
				 dataInicio : dataInicio,
				 dataFim : dataFim,
				 turma : turma != null ? turma : null
		 };
		
		EstagioService.filtroEstagio(data, function (response) {
			$scope.estagios = response.data;
			$mdSidenav('right').close();
		}),
			function (error) {
		};
	};

	//Busca estagios Vencendo
	$scope.getEstagioVencendo = function () {
		EstagioService.getEstagioVencendo(function (response) {
			if(response.data){
				$scope.estagioVencendo = response.data;
				$scope.abrirModal();
			}
			
		});		
	};

//////////////////////////////////////Abrir Modal
	  $scope.abrirModal = function(event) {
		    $mdDialog.show({
		      controller: ModalController,
		      templateUrl: 'projeto/estagio/modalEstagioVencendo.html',
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
			console.log(retornoModal);
			$scope.estagiosVencendo = retornoModal.estagioVencendo;
			
			$scope.hide = function() {
		      $mdDialog.hide();
		    };

		    $scope.cancel = function() {
		      $mdDialog.cancel();

		    };	
	}
	
	//Chama função para buscar estagios
	$scope.getEstagio();

	//Chama função para buscar estagios vencendo
	if($localStorage.login == true){
		$localStorage.login = false;
		$scope.getEstagioVencendo();
	}
	
}]);

