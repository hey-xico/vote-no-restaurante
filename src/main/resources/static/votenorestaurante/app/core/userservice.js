(function () {
    'use strict';
    angular
        .module('app.core')
        .factory('UserService', UserService);

    UserService.$inject = ['$http','$log', '$q'];
    function UserService($http, $log, $q) {

        return {
            save: save
        };

        function save(user) {
            return $http.post('/vote-no-restaurante/user/save', user)
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