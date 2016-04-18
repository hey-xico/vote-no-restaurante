(function() {
    'use strict';

    angular
        .module('app.core')
        .config(routerConfig);
    
    routerConfig.$inject = ['$stateProvider', '$urlRouterProvider'];
    function routerConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('ballot', {
                url: '/',
                templateUrl: 'votenorestaurante/app/ballot/ballot.html',
                controller: 'BallotController',
                controllerAs: 'vm'
            })
            .state('user', {
                url: '/',
                templateUrl: 'votenorestaurante/app/user/user.html',
                controller: 'UserController',
                controllerAs: 'vm'
            })
            .state('ranking', {
                url: '/:id',
                templateUrl: 'votenorestaurante/app/ranking/ranking.html',
                controller: 'RankingController',
                controllerAs: 'vm'
            });

        $urlRouterProvider.otherwise('/');
    }

})();