(function () {
    angular
        .module('app.ranking')
        .controller('RankingController', RankingController);

    RankingController.$inject = ['$stateParams', 'RankingService'];
    function RankingController($stateParams, RankingService) {
        var vm = this;
        vm.title = 'Veja a classificação';

        activate();

        function activate() {
            loadUserRanking($stateParams.id);
            loadOverallRanking();
        }

        function loadUserRanking(id) {
            return RankingService.getUserRanking(id).then(function (data) {
                vm.userRankingData = data;
            });
        }
        
        function loadOverallRanking() {
            return RankingService.getOverallRanking().then(function (data) {
                vm.overallRankingData = data;
            });
        }
        

    }
})();