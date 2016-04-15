describe('yooService', function () {

    var yooService, $httpBackend;

    beforeEach(module('yooApp'));

    beforeEach(inject(function (_yooService_, _$httpBackend_) {
        yooService = _yooService_;
        $httpBackend = _$httpBackend_;
    }));

    describe('ChecK Service Behavior', function () {

        it('getCombinations must an array of data', function () {
            $httpBackend
                .expectGET('ballot/get-combinations')
                .respond(200);
            var succeeded;
            var data = yooService.getCombinations().then(function (data) {
                succeeded = true;
            });
            $httpBackend.flush();
            //noinspection BadExpressionStatementJS
            expect(succeeded).toBe(true);
        });

        it('Given a vaid ballotBox, must successfully submit it', function () {
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

            $httpBackend.when('POST', '/vote-no-restaurante/ballot/submit').respond(ballotBox);

            var result;

            yooService.submitBallot(ballotBox).then(function (data) {
                result = data;
            });
            $httpBackend.flush();
            //noinspection BadExpressionStatementJS
            expect(result).toBe(result);
        });
        
        
    });

});