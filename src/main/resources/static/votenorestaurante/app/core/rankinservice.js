(function () {
    'use strict';
    angular
        .module('app.core')
        .factory('RankingService', RankingService);

    RankingService.$inject = ['$http','$log', '$q'];
    function RankingService($http, $log, $q) {

        return {
            getUserRanking: getUserRanking,
            getRankingGlobal: getRankingGlobal
        };

        function getUserRanking(user) {
            return $http.get('/vote-no-restaurante/ranking/user', user)
                .then(onComplete)
                .catch(onError);
        }

        function getRankingGlobal() {
            return $http.get('/vote-no-restaurante/ranking/global')
                .then(onComplete)
                .catch(onError);
        }

        function onComplete(response) {
            return response.data;
        }

        function onError(error) {
            $log.error('XHR Failed for combinations.' + error.data);
            return $q.reject(error.data.description)
        }

    }
})();