(function () {
    angular
        .module('yooApp')
        .controller('yooController', yooController);

    yooController.$inject = ['$scope', 'yooService'];

    function yooController($scope, yooService, yooUserService) {
        var vm = this;
        
        vm.pair = {};
        vm.combinations = [];
        vm.ballotBox = [];
        vm.registryUser = false;

        vm.pairindex = 0;

        vm.title = 'Escolha seu favorito';

        activate();

        function activate() {
            getCombinations();

        }

        function getCombinations() {
            return yooService.getCombinations().then(function (data) {
                vm.combinations = data;
                computeVote();
            });
        }
        
        vm.voteRegister = function(ballot) {
            
            var index = getIndex(vm.ballotBox, ballot.id);

            if (index >=0 ) {
                vm.ballotBox[index].total++;
            } else {
                vm.ballotBox.push(new Ballot(ballot.id, 1));
            }
        };

        vm.setNext = function(combinations) {
            if (vm.pairindex < combinations.length) {
                return combinations[vm.pairindex];
            } else {
                vm.title = 'Obrigado!';
                vm.subtitle = 'Para finalizar, informe seu nome e um e-mail';
                vm.slugline = 'Prometemos não manda spam';
                vm.registryUser = true;
            }
        };

        function computeVote() {
            vm.next = function (ballot) {
                if(ballot) 
                    vm.voteRegister(ballot);
                vm.pair = vm.setNext(vm.combinations);
                vm.pairindex++;
            };
            vm.next();
        }
        function getIndex(arr, value) {
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].restaurant_id == value)
                    return i;
            }
        }

        // function to submit the form after all validation has occurred
        vm.submitForm = function(isValid) {
            // check to make sure the form is completely valid
            if (isValid) {
                submitUser(vm.user);
            }
        };
        vm.submitUser = function(user){
            userService.save(user).then();
        }

    }
})();