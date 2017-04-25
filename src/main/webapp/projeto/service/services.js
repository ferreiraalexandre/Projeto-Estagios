
app.factory('UsuarioService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/usuario/:method/:id', {}, {
		  postUsuario: { method: 'POST', params: {method: 'salva'}}, 
		   

	  })
	}]);

app.factory('UnidadeEnsinoService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/unidadeEnsino/:method/:id', {}, {
		  postUnidade: { method: 'POST', params: {method: 'salva'}}, 
		   

	  })
	}]);
