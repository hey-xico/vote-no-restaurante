(function () {
    angular
        .module('app.ballot')
        .controller('BallotController', BallotController);

    BallotController.$inject = ['BallotService', 'UserService'];

    function BallotController(BallotService, UserService) {
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
                vm.title = 'Obrigado!';
                vm.subtitle = 'Para finalizar, informe seu nome e um e-mail';
                vm.slugline = 'Prometemos não enviar spam';
                vm.registryUser = true;
            }
        };

        function getIndex(arr, value) {
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].restaurantId == value)
                    return i;
            }
        }

        // function to submit the form after all validation has occurred
        vm.submitForm = function (isValid) {
            // check to make sure the form is completely valid
            if (isValid) {
                vm.submitUser(vm.user);
            }
        };

        vm.submitUser = function (user) {
            UserService.save(user).then(
                function (data) {
                    vm.sendBallot(data, vm.ballotBox);
                    vm.savedUser = data;
                }
            );
        };

        vm.sendBallot = function (user, ballotBox) {

            vm.ballotBoxOfUser = {
                userId: user.id,
                ballotBoxList: ballotBox
            };

            BallotService.submitBallot(vm.ballotBoxOfUser).then(
                function (data) {
                    vm.successBallotSubmit = 'Voto registrado com sucesso';
                }
            );
        }

    }
})();