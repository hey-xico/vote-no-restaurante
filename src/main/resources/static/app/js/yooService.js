(function () {
	'use strict';
	angular
		.module('yooApp')
		.factory('yooService', yooService);

		yooService.$inject = ['$http', '$log'];
		function yooService($http, $log) {
			return {
				getCombinations: getCombinations,
				voteRegister: voteRegister
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

			function voteRegister() {

				return 'pass';
				
			}
		}
})();