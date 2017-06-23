
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

app.factory('EmpresaService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/empresa/:method/:data', {}, {
		  postEmpresa: { method: 'POST', params: {method: 'salva'}}, 
		  getList: {method: 'GET', params: {method: 'buscar'}, isArray: false},
		  putEmpresa: { method: 'PUT', params: {method: 'editar'}},
		  deleteEmpresa: { method: 'DELETE', params: {method: 'deletar'}, isArray: false, interceptor: {responseError : resultError}}
		   

	  })
}]);

app.factory('TurmaService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/turma/:method/:data', {}, {
		  postTurma: { method: 'POST', params: {method: 'salva'}}, 
		  getList: {method: 'GET', params: {method: 'buscar'}, isArray: false},
		  putTurma: { method: 'PUT', params: {method: 'editar'}},
		  deleteTurma: { method: 'DELETE', params: {method: 'deletar'}, isArray: false, interceptor: {responseError : resultError}}
	  })
}]);


app.factory('CursoService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/curso/:method/:data', {}, {
		  postCurso: { method: 'POST', params: {method: 'salva'}}, 
		  getList: {method: 'GET', params: {method: 'buscar'}, isArray: false},
		  putCurso: { method: 'PUT', params: {method: 'editar'}},
		  deleteCurso: { method: 'DELETE', params: {method: 'deletar'}, isArray: false, interceptor: {responseError : resultError}}
		   

	  })
}]);

app.factory('InstituicaoService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/instituicao/:method/:data', {}, {
		  postInstituicao: { method: 'POST', params: {method: 'salva'}}, 
		  getList: {method: 'GET', params: {method: 'buscar'}, isArray: false},
		  putInstituicao: { method: 'PUT', params: {method: 'editar'}},
		  deleteInstituicao: { method: 'DELETE', params: {method: 'deletar'}, isArray: false, interceptor: {responseError : resultError}}
		   

	  })
}]);

 function resultError(response) {
	alert(response.data.message + "\n" + response.data.description);
};
