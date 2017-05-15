
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
