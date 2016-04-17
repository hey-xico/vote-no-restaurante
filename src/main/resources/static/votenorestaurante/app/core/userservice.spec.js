describe('UserService', function () {

    var userFixture = {
        name: 'Frank Underwood',
        email: 'fu@wh.com'
    };

    beforeEach(function () {

        bard.appModule('app.core');
        bard.inject('$http', '$httpBackend', '$q', 'UserService', '$rootScope');

    });

    it('exists', function(){
        expect(UserService).to.exist;

    });



    it('Given a valid user, must successfully submit it', function(){

        $httpBackend
            .when('POST', '/vote-no-restaurante/user/save')
            .respond(200, userFixture);

        UserService.save(userFixture).then(function (data) {
            expect(data).to.exist;
        });

        $httpBackend.flush();
    });

    it('save reports error if server fails', function() {
        $httpBackend
            .when('POST', '/vote-no-restaurante/user/save')
            .respond(500, {description: 'an error has occurred'});

        UserService.save(userFixture).catch(function (data) {
            expect(data).to.exist;
            expect(data).to.be.equal('an error has occurred');
        });

        $httpBackend.flush();
    });

});
