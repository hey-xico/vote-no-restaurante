describe('yooController', function () {

    beforeEach(module('yooApp'));

    var yooService;
    var yooUserService;
    var yooController;

    // Mock services and spy on methods
    beforeEach(inject(function ($q, _yooService_, _yooUserService_) {

        deferred = $q.defer();

        yooService = _yooService_;

        yooUserService = _yooUserService_;

        //spyOn(yooService, 'getCombinations').and.callThrough();

        spyOn(yooService, 'getCombinations').and.returnValue(deferred.promise);
        spyOn(yooService, 'submitBallot').and.returnValue(deferred.promise);
        spyOn(yooUserService, 'save').and.returnValue(deferred.promise);

    }));
    beforeEach(inject(function ($rootScope, $controller) {
        scope = $rootScope.$new();

        yooController = $controller('yooController', {
            $scope: scope,
            yooService: yooService
        });
    }));


    it('Should call the GetCombinations in order to get the list of Combination', function () {
        expect(yooService.getCombinations).toHaveBeenCalled();
        expect(yooService.getCombinations.calls.count()).toBe(1);
    });

    it('After initialized, the controller must to call the service and retrieve the data', function () {
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

        deferred.resolve(combinationFixture); // Resolve the promise.
        scope.$digest();

        expect(yooService.getCombinations).toHaveBeenCalled();
        expect(yooController.combinations).toBe(combinationFixture);
    });

    it('Given a List of combinations, must return a new pair if the counter is less then its length', function () {
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

        expect(yooController.pairindex).toBe(0);

        expect(yooController.setNext).toBeDefined();
        result = yooController.setNext(combinationFixture);

        expect(result.length).toBe(2);

    })

    it('Given a selected ballot, must compute the vote and increase the ballot box', function () {
        var ballotItemFixture = {
            "id": 1,
            "name": "A Figueira Rubaiyat",
            "pathImage": "images/rubaiyat.png",
            "userVotes": []
        };

        expect(yooController.ballotBox.length).toBe(0);

        yooController.voteRegister(ballotItemFixture);

        expect(yooController.ballotBox.length).toBe(1);
        expect(yooController.ballotBox[0].restaurant_id).toBe(1);
    });

    it('Given a selected ballot n times, the BallotBox must store only one and increase the vote', function () {
        var ballotItemFixture = {
            "id": 1,
            "name": "A Figueira Rubaiyat",
            "pathImage": "images/rubaiyat.png",
            "userVotes": []
        };

        expect(yooController.ballotBox.length).toBe(0);

        yooController.voteRegister(ballotItemFixture);

        expect(yooController.ballotBox[0].total).toBe(1);

        yooController.voteRegister(ballotItemFixture);
        yooController.voteRegister(ballotItemFixture);
        yooController.voteRegister(ballotItemFixture);

        expect(yooController.ballotBox.length).toBe(1);

        expect(yooController.ballotBox[0].total).toBe(4);


    });

    it('Given a selected ballot twice, vote in the BallotBox must be increased', function () {
        var ballotItemFixture = {
            "id": 1,
            "name": "A Figueira Rubaiyat",
            "pathImage": "images/rubaiyat.png",
            "userVotes": []
        };

        expect(yooController.ballotBox.length).toBe(0);

        yooController.voteRegister(ballotItemFixture);

        expect(yooController.ballotBox[0].total).toBe(1);
        expect(yooController.ballotBox.length).toBe(1);

        yooController.voteRegister(ballotItemFixture);

        expect(yooController.ballotBox[0].total).toBe(2);

    });

    it('When the combinations finish, the nextfunction must set the User registry as true', function () {
        //Given
        yooController.pairindex = 5;
        var combinations = {length: 5};

        //When
        var result = yooController.setNext(combinations);

        expect(yooController.registryUser).toBeTruthy();
        //expect(result).toBe('hue');
    });

    it('Given a valid user and email, must to submit to the API', function () {

        var user = {
            name: 'Frank Underwood',
            email: 'fu@wh.com'
        };

        yooController.submitUser(user);

        expect(yooUserService.save).toHaveBeenCalled();
        expect(yooUserService.save.calls.count()).toBe(1);

        deferred.resolve(user); // Resolve the promise.
        scope.$digest();

        expect(yooController.savedUser).toBeDefined();
        expect(yooController.savedUser).toBe(user);
    });


    it('After successfully submit a User, must to call the sendBallot methos', function () {

        var user = {
            name: 'Frank Underwood',
            email: 'fu@wh.com'
        };

        yooController.submitUser(user);

        expect(yooUserService.save).toHaveBeenCalled();
        expect(yooUserService.save.calls.count()).toBe(1);

        deferred.resolve(user); // Resolve the promise.
        scope.$digest();

        //Call the submitBallot service
        expect(yooService.submitBallot).toHaveBeenCalled();
        expect(yooService.submitBallot.calls.count()).toBe(1)

        expect(yooController.savedUser).toBeDefined();
        expect(yooController.savedUser).toBe(user);
    });

    it('To submit a ballot, must to pass a user and the ballots of that session.', function () {
        var user = {
            id: 1,
            name: 'Frank Underwood',
            email: 'fu@wh.com'
        };
        var ballotBox = {
            user_id: 1,
            ballot: [
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
            ]
        }

        yooController.sendBallot(user, ballotBox);

        expect(yooController.ballotBoxOfUser.user_id).toBe(user.id);
        expect(yooController.ballotBoxOfUser.ballotBox).toBe(ballotBox);

    });

    it('After save the user, must submit the ballot box', function () {
        var user = {
            id: 1,
            name: 'Frank Underwood',
            email: 'fu@wh.com'
        };
        var ballotBox = [
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

        yooController.sendBallot(user, ballotBox);

        expect(yooService.submitBallot).toHaveBeenCalled();
        expect(yooService.submitBallot.calls.count()).toBe(1);

        deferred.resolve(ballotBox); // Resolve the promise.
        scope.$digest();

        expect(yooController.successBallotSubmit).toBe('Voto registrado com sucesso');


    });

});

