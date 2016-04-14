
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

         //var yooController = $controller('yooController', { yooService: yooService });
        //yooController.setNext(combinationFixture);
        
        expect(yooController).toBeDefined();
        expect(yooController.setNext).toBeDefined();

        /*expect(yooController.pairindex).toBe(eqeqw);
        expect(yooController.pair).toHaveBeenCalled();
        expect(yooController.pair.length).toBe(1);
        expect(yooController.pairindex).toBe(1);*/
    })

});

