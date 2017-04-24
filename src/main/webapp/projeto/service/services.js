app.factory('UsuarioService', function ($resource) {
    return $resource(pathRest + '/usuario', {}, {
        postUsuario: { method: 'POST', params: {data: 'data'}, isArray: false},
    })
});
