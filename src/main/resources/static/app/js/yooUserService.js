(function(){
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
            return $http({
                cache: true,
                url:'user/save',
                headers: {
                    'Content-Type': 'application/json'
                },
                responseType:'json',
                method:'POST',
                data:{
                    user : user
                }
            }).then(function(response) {
                return response.data;
            });
        }

    }
})();