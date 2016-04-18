describe('UserController', function () {

    beforeEach(function () {
        bard.appModule('app.user');
        bard.inject('$controller', '$q', '$rootScope', 'UserService', 'BallotService');
    });

    beforeEach(function () {
        UserController = $controller('UserController');
        $rootScope.$apply();
    });


    describe('When hits the user stats: ', function () {
        it('Load the controller', function () {
            expect(UserController.title).to.equal('Obrigado!');
        });
    });
    describe('When submit the form', function () {
        var userFixture = {
            id: 1,
            name: 'Frank Underwood',
            email: 'fu@wh.com'
        };

        var ballotsFixture = [
            {
                restaurant_id: 1,
                total: 2
            },
            {
                restaurant_id: 2,
                total: 3
            },
            {
                restaurant_id: 3,
                total: 1
            },
            {
                restaurant_id: 4,
                total: 1
            },
            {
                restaurant_id: 5,
                total: 3
            }
        ];

        beforeEach(function () {
            bard.inject(this, '$state');

            $state.go =
                function (state) {
                    return state;
                };
            sinon.spy($state, 'go');

        });

        beforeEach(function () {
            sinon.stub(UserService, 'save').returns($q.when(userFixture));
            sinon.stub(BallotService, 'submitBallot').returns($q.when($state.go()));
        });

        it('hist the api', function () {
            UserController.submitUser(userFixture);
            expect(UserService.save).to.have.been.calledWith(userFixture);

        });

        it('After submit the user, must to trigger the ballots on the service and submit the UserBallotBox', function () {
            UserController.sendBallot(userFixture, ballotsFixture);
            expect(UserController.ballotBoxOfUser.userId).to.equal(userFixture.id);
            expect(BallotService.submitBallot).to.have.been.called;
        });

        it('Redirect to ranking state', function () {
            UserController.sendBallot(userFixture, ballotsFixture);
            expect($state.go).to.have.been.called;
        });
    });



});

