(function () {
	'use strict';
	angular
		.module('yooApp')
		.factory('yooService', yooService);

		yooService.$inject = ['$http', '$log'];
		function yooService($http, $log) {
			return {
				getCombinations: getCombinations,
				submitBallot: submitBallot
			}

			function getCombinations() {

				return $http.get('ballot/get-combinations')
		            .then(onComplete)
		            .catch(onError);

		        function onComplete(response) {
		            return response.data;
		        }

		        function onError(error) {
		            $log.error('XHR Failed for combinations.' + error.data);
		        }
				
			}

			function submitBallot(ballotBox) {

				return $http.post('/vote-no-restaurante/ballot/submit', ballotBox).then(function (response) {
					return response.data;
				});
				
			}
		}
})();