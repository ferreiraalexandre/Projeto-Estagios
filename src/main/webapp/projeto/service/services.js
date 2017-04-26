
app.factory('UsuarioService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/usuario/:method/:id', {}, {
		  postUsuario: { method: 'POST', params: {method: 'salva'}}, 
		  getList: 	{ method: 'GET', params: {method: 'buscar'}, isArray: false }
		   

	  })
	}]);

app.factory('UnidadeEnsinoService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/unidadeEnsino/:method/:id', {}, {
		  postUnidade: { method: 'POST', params: {method: 'salva'}}, 
		   

	  })
	}]);
