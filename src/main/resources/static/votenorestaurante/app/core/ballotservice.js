(function () {
    'use strict';
    angular
        .module('app.core')
        .factory('BallotService', BallotService);

    BallotService.$inject = ['$http', '$log', '$q'];
    function BallotService($http, $log, $q) {

        var storedBallot;

        return {
            getCombinations: getCombinations,
            submitBallot: submitBallot,
            storeBallot: storeBallot,
            getBallot: getBallot
        };

        function getCombinations() {

            return $http.get('ballot/get-combinations')
                .then(onComplete)
                .catch(onError);
        }

        function submitBallot(ballotBox) {

            return $http.post('/vote-no-restaurante/ballot/submit', ballotBox).then(onComplete)
                .catch(onError);

        }

        function onComplete(response) {
            return response.data;
        }

        function onError(error) {
            $log.error('XHR Failed for combinations.' + error.data);
            return $q.reject(error.data.description)
        }

        function storeBallot(ballot) {
            storedBallot = ballot;
        }

        function getBallot() {
            return storedBallot;
        }
    }
})();