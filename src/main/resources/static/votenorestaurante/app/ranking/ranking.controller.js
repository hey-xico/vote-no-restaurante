(function () {
    angular
        .module('app.ranking')
        .controller('RankingController', RankingController);

    RankingController.$inject = ['$stateParams', 'RankingService'];
    function RankingController($stateParams, RankingService) {
        var vm = this;
        var descriptionMessage = "Veja o resumo do seu voto e da votação geral";
        activate();

        function activate() {
            vm.title = $stateParams.id ? 'Obrigado(a)' : 'Ranking Geral';
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