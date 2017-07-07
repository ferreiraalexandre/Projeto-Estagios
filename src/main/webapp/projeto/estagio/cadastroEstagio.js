
app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])

app.controller('cadastroEstagioController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', '$location', 'EstagioService', 'toastr', 'Scopes',
                                    function ($mdEditDialog,   $q,   $scope,   $timeout,   $mdDialog,   $location,   EstagioService,   toastr, Scopes) {
  'use strict';
  $scope.title = "Adicionar Estudade"
  $scope.adicionarEstudante = true;	  
  $scope.items = ['Nome', 'Curso', 'Empresa'];
  $scope.estagio = {};
  $scope.estagio.cadastroSGN = true;
  $scope.estagio.estagioObrigatorio = true;
  $scope.buttonEditDisabled = true;
  $scope.$parent.rodape = false;
  $scope.$parent.links = false;
  $scope.cardCadastroEstagio = {"margin-top" : "60px"}
  $scope.$parent.icon;
  console.log($scope.teste);
  $scope.selectRequired = true;
  $scope.selecionados = [];
  
  $scope.getListSelect = function () {
	  EstagioService.getListSelect(function (response) {
		  $scope.empresas = response.data.empresa;			
		  $scope.instituicoes = response.data.instituicao;
		  $scope.turmas = response.data.turma;
		  $scope.estudantes = response.data.estudante;

		  $scope.selecionados = Scopes.get('estagioController').selecionados;
		  if(Scopes.get('estagioController').icon == "editar"){
			  $scope.editarEstudante = true;
			  $scope.adicionarEstudante = false;
			  $scope.listarEstudante = false;
			  $scope.title = "Editar de Estudade"
				  
			  $scope.estagio = $scope.selecionados[0];
			  $scope.estagio.editEstudante = $scope.selecionados[0].estudante;
			  $scope.estagio.editCpf = $scope.selecionados[0].estudante.cpf;
			  $scope.turmaEditar = $scope.selecionados[0].turma.id;
			  $scope.estagio.dataInicio = new Date($scope.selecionados[0].dataInicio);
			  
			  new Date(Date.UTC(96, 11, 1, 0, 0, 0));
			  //$scope.estagio.dataInicio = new Date("2014-01-01 11:00:00 UTC");
		  }
		  if(Scopes.get('estagioController').icon == "remover"){
			  
		  }

 	  
	  }),
		function (error) {
			
		};
  };
  
  $scope.menuEstudante = function (link, data) {
	  if(link == "adicionar"){
		  $scope.adicionarEstudante = true;
		  $scope.listarEstudante = false;
		  $scope.editarEstudante = false;
		  $scope.title = "Adicionar Estudade"
	  }
	  if(link == "listar"){
		  $scope.listarEstudante = true;
		  $scope.adicionarEstudante = false;
		  $scope.editarEstudante = false;
		  $scope.title = "Lista de Estudade"
	  }
	  if(link == "editar"){
		  $scope.editarEstudante = true;
		  $scope.adicionarEstudante = false;
		  $scope.listarEstudante = false;
		  $scope.title = "Editar de Estudade"
		  $scope.estagio.editEstudante = data;
		  $scope.estagio.editCpf = data.cpf;
		  $scope.turmaEditar = data.turma.id;
	  }
	  
  };
  
	//Função de adicionar novo estagio no Banco de Dados
	$scope.adicionarEstagio = function (data) {
		data.dataInicio = moment(data.dataInicio).format('YYYY-MM-DD');
		data.dataFim = moment(data.dataFim).format('YYYY-MM-DD');
		data.dataVisitaEmpresa = moment(data.dataVisitaEmpresa).format('YYYY-MM-DD');
		EstagioService.postEstagio(data, function (response) {
		toastr.success(response.message);
		console.log("go", path);
		var  link = "/";
		$location.path(link);
	
		}),
			function (error) {
		};
	};
	
	// Função do botão "Cancelar"do "CadastroEstagio.html"
	$scope.go = function ( path ) {
		$location.path(path);
	};
	
	//Chama função para buscar estagios
	$scope.getListSelect();
	
}]);
