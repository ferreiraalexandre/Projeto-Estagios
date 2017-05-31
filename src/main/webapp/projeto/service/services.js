
app.factory('UsuarioService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/usuario/:method/:data', {}, {
		  postUsuario: { method: 'POST', params: {method: 'salva'}}, 
		  getList: 	{ method: 'GET', params: {method: 'buscar'}, isArray: false },
		  putUsuario: { method: 'PUT', params: {method: 'editar'}},
		  deleteUsuario: { method: 'DELETE', params: {method: 'deletar'}, isArray: false, interceptor: {responseError : resultError}}

	  })
	}]);

app.factory('UnidadeEnsinoService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/unidadeEnsino/:method/:data', {}, {
		  postUnidade: { method: 'POST', params: {method: 'salva'}}, 
		  getList: {method: 'GET', params: {method: 'buscar'}, isArray: false},
		  putUnidade: { method: 'PUT', params: {method: 'editar'}},
		  deleteUnidade: { method: 'DELETE', params: {method: 'deletar'}, isArray: false, interceptor: {responseError : resultError}}
		   

	  })
}]);

 function resultError(response) {
	alert(response.data.message + "\n" + response.data.description);
};
