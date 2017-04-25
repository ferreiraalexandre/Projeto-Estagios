
app.factory('UsuarioService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/usuario/:method/:id', {}, {
		  postUsuario: { method: 'POST', params: {method: 'salva'}}, 
		   

	  })
	}]);
