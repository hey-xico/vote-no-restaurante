(function () {
    angular
        .module('app.ranking')
        .controller('RankingController', RankingController);

    RankingController.$inject = [];
    function RankingController() {
        var vm = this;
        vm.title = 'Veja a classificação';


    }
})();