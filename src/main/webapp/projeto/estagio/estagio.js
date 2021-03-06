
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
  $scope.usuario = $localStorage.currentUser.user;
  
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
  
	$scope.logout = function(){
		$localStorage.$reset();
		window.location.href="/projeto-estagios/index.html";
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
	  .textContent('Tem certeza que deseja excluir o(s) Estágio(s)?')
	  .targetEvent(ev)
	  .ok('SIM')
	  .cancel('NÃO');
	  
	  $mdDialog.show(confirm).then(function() {
		  $scope.deleteEstagio();
		  $scope.status = 'Deletado';
	  }, function() {
		  $scope.status = 'Deu erro ao deletar';
	  });
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
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					estagios:response.data,
			}
			$scope.selecionados = []; 
			$scope.buttonEnable();
		});
	};  

	
	//Busca estagios do banco e lista na tabela
	$scope.getEstagio = function () {
		EstagioService.getList(function (response) {
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					estagios:response.data,
			}
			$scope.isLoading = false;
		});		
	};
	
	$scope.openRightMenu = function() {
       $mdSidenav('right').toggle();
       $scope.dataInicio = new Date();
       var dataFim = new Date();
       dataFim.setDate($scope.dataInicio.getDate() + 10);
       $scope.dataFim = dataFim;
       
       if($scope.data.estagios.length > 0){
    	   var idsCurso = [];
    	   var cursos = [];
    	   for (var int = 0; int < $scope.data.estagios.length; int++) {
    		   if(idsCurso.indexOf($scope.data.estagios[int].turma.curso.id) == -1){
    			   idsCurso.push($scope.data.estagios[int].turma.curso.id);
    			   cursos.push($scope.data.estagios[int].turma.curso);
    			   $scope.cursos = cursos;
    		   }
    	   }
    	   
    	   var idsEmpresa = [];
    	   var empresas = [];
    	   for (var int = 0; int < $scope.data.estagios.length; int++) {
    		   if(idsEmpresa.indexOf($scope.data.estagios[int].empresa.id) == -1){
    			   idsEmpresa.push($scope.data.estagios[int].empresa.id);
    			   empresas.push($scope.data.estagios[int].empresa);
    			   $scope.empresas = empresas;
    		   }
    	   }   
       }

    };
    
    $scope.closeRightMenu = function() {
    	$mdSidenav('right').close(); 
     };

   
	//Função de Aplicar Filtro 
	$scope.aplicarFiltro = function (dataInicio, dataFim, turma, empresa) {
		 var data = {
				 dataInicio : dataInicio,
				 dataFim : dataFim,
				 turma : turma != null ? turma : null,
				 empresa : empresa != null ? empresa : null
		 };
		
		EstagioService.filtroEstagio(data, function (response) {
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					estagios:response.data,
			}
			$mdSidenav('right').close();
		}),
			function (error) {
		};
	};
	
	//Limpar Filtro
	$scope.limparFiltro = function () {
		EstagioService.getList(function (response) {
			$scope.data = {
					count : response.data != undefined ? response.data.length : null,
					estagios:response.data,
			}

			$mdSidenav('right').close();
			
		});		
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
	
	//Gerar Log
	$scope.getLog = function () {
		EstagioService.getLog(function (data) {
			var sampleArr = base64ToArrayBuffer(data.data);
			saveByteArray("teste",sampleArr);	
		});		
	};
	
	function base64ToArrayBuffer(base64) {
	    var binaryString = window.atob(base64);
	    var binaryLen = binaryString.length;
	    var bytes = new Uint8Array(binaryLen);
	    for (var i = 0; i < binaryLen; i++) {
	       var ascii = binaryString.charCodeAt(i);
	       bytes[i] = ascii;
	    }
	    return bytes;
	 }

	function saveByteArray(reportName, byte) {
	    var blob = new Blob([byte]);
	    var link = document.createElement('a');
	    link.href = window.URL.createObjectURL(blob);
	    var fileName = reportName + ".txt";
	    link.download = fileName;
	    link.click();
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

