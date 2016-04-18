(function () {
    angular
        .module('app.user')
        .controller('UserController', UserController);

    UserController.$inject = ['UserService', 'BallotService', '$state'];

    function UserController(UserService, BallotService, $state) {
        var vm = this;

        vm.title = 'Obrigado!';
        vm.subtitle = 'Para finalizar, informe seu nome e um e-mail';
        vm.slugline = 'Prometemos não enviar spam';

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
                    vm.sendBallot(data, BallotService.getBallot());
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
                    console.log("oi");
                    $state.go('ranking');
                }
            );
        }


    }
})();