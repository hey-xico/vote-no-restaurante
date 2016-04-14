(function () {
    angular
        .module('yooApp')
        .controller('yooController', yooController);

    yooController.$inject = ['$scope', 'yooService'];

    function yooController($scope, yooService) {
        var vm = this;
        
        vm.pair = {};
        vm.combinations = [];
        vm.ballotBox = [];

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
        
        function voteRegister(ballot) {
            
            var index = getIndex(vm.ballotBox, ballot.id);
            
            if (index >=0 ) {
                vm.ballotBox[index].total++;
            } else {
                vm.ballotBox.push(new Ballot(ballot.id, 1));
            }
        }

        vm.setNext = function setNext(combinations) {
            if (combinations.length > vm.pairindex) {
                vm.pairindex++;
                return combinations[vm.pairindex];
            } else {
                return "hue";
            }
        };

        function computeVote() {
            vm.next = function (ballot) {
                if(ballot) 
                    voteRegister(ballot);
                vm.pair = vm.setNext(vm.combinations);
                console.log(vm.pair);
            };
            vm.next();
        }
        function getIndex(arr, value) {
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].restaurant_id == value)
                    return i;
            }
        }


    }
})();