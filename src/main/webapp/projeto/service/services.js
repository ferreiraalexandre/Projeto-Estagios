
app.factory('UsuarioService',['$resource',  function ($resource) {
	
	  return $resource(app.pathRest + '/usuario/:method/:data', {}, {
		  postUsuario: { method: 'POST', params: {method: 'salva'}}, 
		  getList: 	{ method: 'GET', params: {method: 'buscar'}, isArray: false },
<<<<<<< HEAD
		  putUsuario: { method: 'PUT', params: {method: 'editar'}},
=======
>>>>>>> branch 'master' of https://github.com/ferreiraalexandre/Projeto-Estagios.git
		  deleteUsuario: { method: 'DELETE', params: {method: 'deletar'}, isArray: true}

	  })
	}]);

app.factory('UnidadeEnsinoService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/unidadeEnsino/:method/:data', {}, {
		  postUnidade: { method: 'POST', params: {method: 'salva'}}, 
		  getList: {method: 'GET', params: {method: 'buscar'}, isArray: false},
		  deleteUnidade: { method: 'DELETE', params: {method: 'deletar'}, isArray: true}
		   

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