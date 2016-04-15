(function () {
    'use strict';
    angular
        .module('yooApp')
        .factory('yooUserService', yooUserService);

    yooUserService.$inject = ['$http'];
    function yooUserService($http) {

        return {
            save: save
        };

        function save(user) {
            return $http.post('/vote-no-restaurante/user/save', user).then(function (response) {
                return response.data;
            });
        }

    }
})();