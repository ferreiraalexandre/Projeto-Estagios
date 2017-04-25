
app.factory('UsuarioService',['$resource',  function ($resource) {
	  return $resource(app.pathRest + '/usuario', {}, {
		  postUsuario: { method: 'POST', params: {method: 'salva' , id: '@id'}}, 
		   

	  })
	}]);
