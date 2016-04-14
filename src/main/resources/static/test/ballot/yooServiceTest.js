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
        
        
    });

});