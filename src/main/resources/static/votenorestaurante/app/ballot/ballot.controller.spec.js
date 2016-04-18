describe('BallotController', function () {

    var combinationFixture =
        [
            [
                {
                    "id": 1,
                    "name": "A Figueira Rubaiyat",
                    "pathImage": "images/rubaiyat.png",
                    "userVotes": []
                },
                {
                    "id": 5,
                    "name": "Vento Haragano",
                    "pathImage": "images/vento-haragano.png",
                    "userVotes": []
                }
            ],
            [
                {
                    "id": 5,
                    "name": "Vento Haragano",
                    "pathImage": "images/vento-haragano.png",
                    "userVotes": []
                },
                {
                    "id": 2,
                    "name": "Barbacoa",
                    "pathImage": "images/barbacoa.jpg",
                    "userVotes": []
                }
            ]
        ];

    var userFixture = {
        name: 'Frank Underwood',
        email: 'fu@wh.com'
    };

    var ballotItemFixture = {
        "id": 1,
        "name": "A Figueira Rubaiyat",
        "pathImage": "images/rubaiyat.png",
        "userVotes": []
    };

    beforeEach(function () {
        bard.appModule('app.ballot');
        bard.inject('$controller', '$q', '$rootScope', 'BallotService', 'UserService');
    });

    beforeEach(function () {
        sinon.stub(BallotService, 'getCombinations').returns($q.when(combinationFixture));
        sinon.stub(BallotService, 'submitBallot').returns($q.when(ballotItemFixture));
        sinon.stub(UserService, 'save').returns($q.when(userFixture));

        BallotController = $controller('BallotController');
        $rootScope.$apply();
    });

    it('Services must be injected', function () {
        expect(BallotService).to.exist;
        expect(UserService).to.exist;
    });


    describe('After Initialized', function () {
        it('The controller must to call the service and retrieve the data', function () {
            expect(BallotService.getCombinations()).to.have.been.call;
            expect(BallotController.combinations).to.have.length.above(0);
            expect(BallotController.combinations).to.be.equal(combinationFixture);
        });


        it('After retrieve the combinations, must return a new pair', function () {
            expect(BallotController.setNext).to.be.defined;
            expect(BallotController.pair.length).to.equal(2);
        });

        it('Voting in a item return a new pair', function () {

            var currentPair = BallotController.pair;

            expect(currentPair).to.be.equal(BallotController.pair);

            BallotController.getNextPair(currentPair);

            expect(currentPair).not.to.be.equal(BallotController.pair);

        });

        it('Given a selected ballot, must compute the vote and increase the ballot box', function () {
            expect(BallotController.ballotBox).to.be.empty;
            BallotController.voteRegister(ballotItemFixture);
            expect(BallotController.ballotBox).to.have.length.above(0);
            expect(BallotController.ballotBox[0].restaurantId).to.equal(1);
        });


        it('Given a selected ballot n times, the BallotBox must store only one and increase the vote', function () {

            expect(BallotController.ballotBox.length).to.equal(0);

            BallotController.voteRegister(ballotItemFixture);

            expect(BallotController.ballotBox[0].total).to.equal(1);

            BallotController.voteRegister(ballotItemFixture);
            BallotController.voteRegister(ballotItemFixture);
            BallotController.voteRegister(ballotItemFixture);

            expect(BallotController.ballotBox.length).to.equal(1);

            expect(BallotController.ballotBox[0].total).to.equal(4);


        });


        it('Given a selected ballot twice, vote in the BallotBox must be increased', function () {

            expect(BallotController.ballotBox.length).to.equal(0);

            BallotController.voteRegister(ballotItemFixture);

            expect(BallotController.ballotBox[0].total).to.equal(1);
            expect(BallotController.ballotBox.length).to.equal(1);

            BallotController.voteRegister(ballotItemFixture);

            expect(BallotController.ballotBox[0].total).to.equal(2);

        });

        it('While combinations are consumed, the nextfunction must keep the User registry as false', function () {
            BallotController.pairindex = 2;

            var combinations = {length: 5};

            BallotController.setNext(combinations);

            expect(BallotController.registryUser).to.equal(false);
        });


    });

    describe('After consume the combinations', function () {

        beforeEach(function () {
            bard.inject(this, '$state');
            $state.go =
                function (state) {
                    return state;
                };
            sinon.spy($state, 'go');

        });
        it('Should registry the ballot box at the service', function () {
            expect(BallotService.getBallot()).to.be.undefined;
            expect(BallotService.storeBallot(ballotItemFixture)).to.be.undefined;
            expect(BallotService.getBallot()).to.be.equal(ballotItemFixture);
        });

        it('Redirect to user state', function () {

            BallotController.pairindex = 5;
            var combinations = {length: 5};
            BallotController.setNext(combinations);

            expect($state.go).to.have.been.calledWith('user');
        });

    });

});

