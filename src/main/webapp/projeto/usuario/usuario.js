
app.config(['$mdThemingProvider', '$mdIconProvider' , function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])



app.controller('usuarioController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$mdDialog', function ($mdEditDialog, $q, $scope, $timeout, $mdDialog) {
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
  
  $scope.estagios = {
    "count": 9,
    "data": [
      {
        "nome": "Andre Felipe",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
        "empresa" : "TOTVS",
      	"dataInicio" : "10/05/2015",
      	"dataFim" : "12/06/2016",
      	"rescisao" : "",
      	"aditivo" : "18/10/2016",
      	"visita" : "02/03/2015",
      	"orientador" : "Ademir",
      	"situacao" : "Matriculado",
      	"sgn" : "Sim",
      	"instituicao": "Senai",
      	"obrigatorio": "Não",
      	
      }, {
        "nome": "Carlos Henrique da Silva",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
        "empresa" : "TOTVS",
      	"dataInicio" : "10/05/2015",
      	"dataFim" : "12/06/2016",
      	"rescisao" : "",
      	"aditivo" : "18/10/2016",
      	"visita" : "02/03/2015",
      	"orientador" : "Ademir",
      	"situacao" : "Matriculado",
      	"sgn" : "Sim",
      	"instituicao": "Senai",
      	"obrigatorio": "Não",
      }, {
        "nome": "Carolina Wiest Marcelino",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
        "empresa" : "TOTVS",
      	"dataInicio" : "10/05/2015",
      	"dataFim" : "12/06/2016",
      	"rescisao" : "",
      	"aditivo" : "18/10/2016",
      	"visita" : "02/03/2015",
      	"orientador" : "Ademir",
      	"situacao" : "Matriculado",
      	"sgn" : "Sim",
      	"instituicao": "Senai",
      	"obrigatorio": "Não",
      }, {
        "nome": "David Bento",
        "curso": "CT em Eletrotécnica",
        "empresa" : "TOTVS",
      	"dataInicio" : "10/05/2015",
      	"dataFim" : "12/06/2016",
      	"rescisao" : "",
      	"aditivo" : "18/10/2016",
      	"visita" : "02/03/2015",
      	"orientador" : "Ademir",
      	"situacao" : "Matriculado",
      	"sgn" : "Sim",
      	"instituicao": "Senai",
      	"obrigatorio": "Não",
      }, {
        "nome": "Denis Sales de Lima",
        "curso": "CT em Fabricação Mecânica",
        "empresa" : "TOTVS",
      	"dataInicio" : "10/05/2015",
      	"dataFim" : "12/06/2016",
      	"rescisao" : "",
      	"aditivo" : "18/10/2016",
      	"visita" : "02/03/2015",
      	"orientador" : "Ademir",
      	"situacao" : "Matriculado",
      	"sgn" : "Sim",
      	"instituicao": "Senai",
      	"obrigatorio": "Não",
      }, {
        "nome": "Elison Padilha Feliciano",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
        "empresa" : "TOTVS",
      	"dataInicio" : "10/05/2015",
      	"dataFim" : "12/06/2016",
      	"rescisao" : "",
      	"aditivo" : "18/10/2016",
      	"visita" : "02/03/2015",
      	"orientador" : "Ademir",
      	"situacao" : "Matriculado",
      	"sgn" : "Sim",
      	"instituicao": "Senai",
      	"obrigatorio": "Não",
      }, {
        "nome": "Igor Francisco Fortes",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
        "empresa" : "TOTVS",
      	"dataInicio" : "10/05/2015",
      	"dataFim" : "12/06/2016",
      	"rescisao" : "",
      	"aditivo" : "18/10/2016",
      	"visita" : "02/03/2015",
      	"orientador" : "Ademir",
      	"situacao" : "Matriculado",
      	"sgn" : "Sim",
      	"instituicao": "Senai",
      	"obrigatorio": "Não",
      }, {
        "nome": "Jackson Peterson Pereira Maciel",
        "curso": "CT em Fabricação Mecânica",
        "empresa" : "TOTVS",
      	"dataInicio" : "10/05/2015",
      	"dataFim" : "12/06/2016",
      	"rescisao" : "",
      	"aditivo" : "18/10/2016",
      	"visita" : "02/03/2015",
      	"orientador" : "Ademir",
      	"situacao" : "Matriculado",
      	"sgn" : "Sim",
      	"instituicao": "Senai",
      	"obrigatorio": "Não",
      }, {
        "nome": "Wander Vinicius Capote Santos",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
        "empresa" : "TOTVS",
      	"dataInicio" : "10/05/2015",
      	"dataFim" : "12/06/2016",
      	"rescisao" : "",
      	"aditivo" : "18/10/2016",
      	"visita" : "02/03/2015",
      	"orientador" : "Ademir",
      	"situacao" : "Matriculado",
      	"sgn" : "Sim",
      	"instituicao": "Senai",
      	"obrigatorio": "Não",
      }
    ]
  };
  
  $scope.editObs = function (event, estagio) {
    event.stopPropagation(); // in case autoselect is enabled
    
    var editDialog = {
      modelValue: estagio.obs,
      placeholder: 'Adicionar observações',
      save: function (input) {
        if(input.$modelValue === 'Donald Trump') {
          input.$invalid = true;
          return $q.reject();
        }
        if(input.$modelValue === 'Bernie Sanders') {
          return estagio.obs = 'FEEL THE BERN!'
        }
        estagio.obs = input.$modelValue;
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
	      templateUrl: 'projeto/estagio/modalEstagio.html',
	    }).then($scope.getDesserts);
	  };

  
  
}]);
