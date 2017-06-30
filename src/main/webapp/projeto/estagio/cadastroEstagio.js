//
//app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
//    'use strict';
//    
//    $mdThemingProvider.theme('default')
//      .primaryPalette('blue');
//}])
//
//app.controller('cadastroEstagioController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', '$location', 'EstagioService', 'toastr',
//                             function ($mdEditDialog,  $q,   $scope,   $timeout,   $mdDialog,   $location,   EstagioService,   toastr) {
//  'use strict';
//  $scope.title = "Adicionar Estudade"
//  $scope.adicionarEstudante = true;
//  $scope.rodape = true;
//  $scope.links = true;	  
//  $scope.selected = [];
//  $scope.limitOptions = [5, 10, 15];
//  $scope.items = ['Nome', 'Curso', 'Empresa'];
//  $scope.selectedItem;
//  $scope.paginaAtualizado = false;
//
//  $scope.cadastrarEstagio = function (event) {
//	  var  link = "/cadastroEstagio";
//      
//	  $scope.rodape = false;
//	  $scope.links = false;
//	  $scope.cardCadastroEstagio = {"margin-top" : "60px"}
//	  
//	  EstagioService.getListSelect(function (response) {
//		  $scope.empresas = response.data.empresa;
//		  $scope.instituicoes = response.data.instituicao;
//		  $scope.turmas = response.data.turma;
//		  $scope.estudantes = response.data.estudante;
//		  $scope.paginaAtualizado = true;
//				
//		}),
//		function (error) {
//			
//		};
//		$location.path(link);
//
//  };
// 
//  $scope.menuEstudante = function (link) {
//	  if(link == "adicionar"){
//		  $scope.adicionarEstudante = true;
//		  $scope.listarEstudante = false;
//		  $scope.editarEstudante = false;
//		  $scope.title = "Adicionar Estudade"
//	  }
//	  if(link == "listar"){
//		  $scope.listarEstudante = true;
//		  $scope.adicionarEstudante = false;
//		  $scope.editarEstudante = false;
//		  $scope.title = "Lista de Estudade"
//	  }
//	  if(link == "editar"){
//		  $scope.editarEstudante = true;
//		  $scope.adicionarEstudante = false;
//		  $scope.listarEstudante = false;
//		  $scope.title = "Editar de Estudade"
//	  }
//
//	  
//  };
//  
//	//Função de adicionar novo estagio no Banco de Dados
//	$scope.adicionarEstagio = function (data) {
//		data.dataInicio = moment(data.dataInicio).format('YYYY-MM-DD');
//		data.dataFim = moment(data.dataFim).format('YYYY-MM-DD');
//		data.dataVisitaEmpresa = moment(data.dataVisitaEmpresa).format('YYYY-MM-DD');
//		EstagioService.postEstagio(data, function (response) {
//		toastr.success(response.message);
//		$scope.$parent.estagios = response.data;
//	  
//		$scope.$parent.rodape = true;
//		$scope.$parent.links = true;	  
//
//		
//		var  link = "/";
//		$location.path(link);
//
//			
//		}),
//			function (error) {
//	
//			};
//	};
//	
//	//Busca estagios do banco e lista na tabela
//	$scope.getEstagio = function () {
//		EstagioService.getList(function (response) {
//			$scope.estagios = response.data;
//			$scope.isLoading = false;
//			
//			  var path = $location.path();
//			  
//			  if(path == "/cadastroEstagio"){
//				  $scope.rodape = false;
//				  $scope.links = false;	  
//				  $scope.cardCadastroEstagio = {"margin-top" : "60px"}
//
//			  }
//
//		});		
//	};
//
//
//	//Chama função para buscar estagios
//	$scope.getEstagio();
//
//	//Chama função no reload da pagina para buscar os select no cadastro do estagio
//	var path = $location.path();
//	if(path == "/cadastroEstagio" && $scope.paginaAtualizado == false){
//		$scope.cadastrarEstagio(path);
//	}
//
//	
//}]);
