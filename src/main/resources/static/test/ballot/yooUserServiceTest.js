describe('yooService', function () {

    var yooUserService, $httpBackend;

    beforeEach(module('yooApp'));

    beforeEach(inject(function (_yooUserService_, _$httpBackend_) {
        yooUserService = _yooUserService_;
        $httpBackend = _$httpBackend_;
    }));

    describe('ChecK User ServiceBehavior', function () {

        it('Sabe a user should return the persisted object', function () {

            var user = {
                name: 'Frank Underwood',
                email: 'fu@wh.com'
            };

            $httpBackend
                .expectPOST('user/save')
                .respond(user);

            var result;
            yooUserService.save(user).then(function(data) {
                result = data;
            });

            $httpBackend.flush();
            //noinspection BadExpressionStatementJS
            expect(result).toEqual(user)
        });
        
        
    });

});