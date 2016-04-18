(function () {
    angular
        .module('app.ballot')
        .controller('BallotController', BallotController);

    BallotController.$inject = ['BallotService', 'UserService', '$state'];

    function BallotController(BallotService, UserService, $state) {
        var vm = this;

        vm.pair = {};
        vm.combinations = [];
        vm.ballotBox = [];
        vm.registryUser = false;
        vm.getCombinations = getCombinations;
        vm.pairindex = 0;

        vm.title = 'Escolha seu favorito';

        activate();

        function activate() {
            vm.getCombinations();

        }

        function getCombinations() {
            return BallotService.getCombinations().then(function (data) {
                vm.combinations = data;
                vm.getNextPair();
            });
        }

        vm.getNextPair = function (ballot) {
            if (ballot)
                vm.voteRegister(ballot);
            vm.pair = vm.setNext(vm.combinations);
            vm.pairindex++;
        };

        vm.voteRegister = function (ballot) {

            var index = getIndex(vm.ballotBox, ballot.id);

            if (index >= 0) {
                vm.ballotBox[index].total++;
            } else {
                vm.ballotBox.push(new Ballot(ballot.id, 1));
            }
        };

        vm.setNext = function (combinations) {
            if (vm.pairindex < combinations.length) {
                return combinations[vm.pairindex];
            } else {
                BallotService.storeBallot(vm.ballotBox);
                $state.go('user');
            }
        };

        function getIndex(arr, value) {
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].restaurantId == value)
                    return i;
            }
        }
    }
})();