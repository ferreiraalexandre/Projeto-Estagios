angular.module('demoApp', ['ngMaterial', 'md.data.table'])

.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    
    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}])

.controller('nutritionController', ['$mdEditDialog', '$q', '$scope', '$timeout', function ($mdEditDialog, $q, $scope, $timeout) {
  'use strict';
  
  $scope.selected = [];
  $scope.limitOptions = [5, 10, 15];
  
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
  
  $scope.desserts = {
    "count": 9,
    "data": [
      {
        "nome": "Andre Felipe",
        "instituicao": "Senai",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
      }, {
        "nome": "Carlos Henrique da Silva",
        "instituicao": "IEL",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
      }, {
        "nome": "Carolina Wiest Marcelino",
        "instituicao": "Senai",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
      }, {
        "nome": "David Bento",
        "instituicao": "AC Serviços de Recrutamento e seleção LTDA ME",
        "curso": "CT em Eletrotécnica",
      }, {
        "nome": "Denis Sales de Lima",
        "instituicao": "Senai",
        "curso": "CT em Fabricação Mecânica",
      }, {
        "nome": "Elison Padilha Feliciano",
        "instituicao": "IEL",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
      }, {
        "nome": "Igor Francisco Fortes",
        "instituicao": "Senai",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
      }, {
        "nome": "Jackson Peterson Pereira Maciel",
        "instituicao": "Senai",
        "curso": "CT em Fabricação Mecânica",
      }, {
        "nome": "Wander Vinicius Capote Santos",
        "instituicao": "Senai",
        "curso": "GT em Análise e Desenvolvimento de Sistemas",
      }
    ]
  };
  
  $scope.editObs = function (event, dessert) {
    event.stopPropagation(); // in case autoselect is enabled
    
    var editDialog = {
      modelValue: dessert.obs,
      placeholder: 'Adicionar observações',
      save: function (input) {
        if(input.$modelValue === 'Donald Trump') {
          input.$invalid = true;
          return $q.reject();
        }
        if(input.$modelValue === 'Bernie Sanders') {
          return dessert.obs = 'FEEL THE BERN!'
        }
        dessert.obs = input.$modelValue;
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
}]);