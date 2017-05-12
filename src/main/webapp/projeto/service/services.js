
app.factory('UsuarioService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/usuario/:method/:data', {}, {
		  postUsuario: { method: 'POST', params: {method: 'salva'}}, 
		  getList: 	{ method: 'GET', params: {method: 'buscar'}, isArray: false },
		  putUsuario: { method: 'PUT', params: {method: 'editar'}},
		  deleteUsuario: { method: 'DELETE', params: {method: 'deletar'}, isArray: false}

	  })
	}]);

app.factory('UnidadeEnsinoService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/unidadeEnsino/:method/:data', {}, {
		  postUnidade: { method: 'POST', params: {method: 'salva'}}, 
		  getList: {method: 'GET', params: {method: 'buscar'}, isArray: false},
		  putUnidade: { method: 'PUT', params: {method: 'editar'}},
		  deleteUnidade: { method: 'DELETE', params: {method: 'deletar'}, isArray: false}
		   

	  })
	}]);

app.service('ToastService',function($mdToast){
	  this.alert = function(text,action,position,delay){

	    var toast = $mdToast.simple()
	    .textContent(text)
	    .position(position)
	    .hideDelay(delay);

	    if(action){ toast.action(action); }

	    return $mdToast.show(toast);
	  };

});

app.service('ArrayService',function(){

	  this.add = function(array, data){

	    if(array === null || array === undefined || array.length <= 0){
	      array = [];
	    };
	    array.push(data);
	    return array;
	  };

	  this.edit = function(index, data, array){
	    array[index] = data;
	    return array;
	  };

	  this.remove = function(index, array){

	    if(index === null || index === undefined || array === null || array === undefined){
	      return null;
	    }else{
	      array.splice(index, 1);
	      return array;
	    };
	  };
	});