(function () {
    'use strict';
    angular
        .module('yooApp')
        .config(['$routeProvider', function($routeProvider) {
            $routeProvider
                .when('/', {
                    'templateUrl' : '',
                    'controller' : ''
                })
                .when('/login', {
                    'templateUrl' : '',
                    'controller' : ''
                })
                .otherwise({
                    'redirectTo' : '/'
                });
        }]);
})();