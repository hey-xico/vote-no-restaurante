
describe('yooController', function () {
    
    beforeEach(module('yooApp'));

    var yooService;
    var yooController;
    
    // Mock services and spy on methods
    beforeEach(inject(function($q, _yooService_) {
        
        deferred = $q.defer();

        yooService = _yooService_;

        //spyOn(yooService, 'getCombinations').and.callThrough();

        spyOn(yooService, 'getCombinations').and.returnValue(deferred.promise);
        
    }));
    beforeEach(inject(function($rootScope, $controller) {
        scope = $rootScope.$new();
        
        yooController = $controller('yooController', {
            $scope: scope,
            yooService: yooService
        });
    }));

    
    it('Should call the GetCombinations in order to get the list of Combination', function() {
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

    it('Given a selected ballot, must compute the vote and increase the ballot box', function(){
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

    it('Given a selected ballot n times, the BallotBox must store only one and increase the vote', function(){
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

    it('Given a selected ballot twice, vote in the BallotBox must be increased', function(){
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

    it('When the combinations finish, the nextfunction must set the User registry as true', function(){
        //Given
        yooController.pairindex = 5;
        var combinations = {length : 5};

        //When
        var result = yooController.setNext(combinations);

        expect(yooController.registryUser).toBeTruthy();
        //expect(result).toBe('hue');
    });

    describe('Save User', function(){

        beforeEach(inject(function($templateCache, $compile){
            templateHtml = $templateCache.get('path/to/my/template.html')
            formElem = angular.element("<div>" + templateHtml + "</div>")
            $compile(formElem)(scope)
            form = scope.form

            scope.$apply()
        }));
        it('Given a valid user and email, must to submit to the API', function(){



        })
    });

});

