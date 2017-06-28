var app = angular.module('LoginForm',['ngMaterial','ngAnimate','ngAria','ngMessages'])
.controller('Ctrl', function($scope){
  $scope.vm = {
      formData: {
        email: 'hello@patternry.com',
       	password: 'foobar'
      }
  };
  
});