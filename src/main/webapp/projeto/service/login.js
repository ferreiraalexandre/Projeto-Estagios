project.factory('LoginService',['$resource',  function ($resource) {
  return $resource(project.pathPrivate + '/login/:email', {}, {
    getUser: { method: 'GET', params: {email: 'email'}, isArray: false },
  })
}]);