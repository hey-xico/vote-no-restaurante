describe('UserService', function () {

    var userFixture = {
        name: 'Frank Underwood',
        email: 'fu@wh.com'
    };

    beforeEach(function () {
        bard.appModule('app.core');
        bard.inject('$http', '$httpBackend', '$q', 'RankingService', '$rootScope');
    });

    it('exists', function(){
        expect(RankingService).to.exist;
    });


    describe('retrieving user ranking', function() {
        it('Must succeed', function(){

            $httpBackend
                .when('GET', '/vote-no-restaurante/ranking/user', userFixture)
                .respond(200, []);

            RankingService.getUserRanking(userFixture).then(function (data) {
                expect(data).to.exist;
            });

            $httpBackend.flush();
        });

        it('reports error if server fails', function() {
            $httpBackend
                .when('GET', '/vote-no-restaurante/ranking/user', userFixture)
                .respond(500, {description: 'an error has occurred'});

            RankingService.getUserRanking(userFixture).catch(function (data) {
                expect(data).to.exist;
                expect(data).to.be.equal('an error has occurred');
            });

            $httpBackend.flush();
        });
    });


    describe('retrieving global ranking', function() {
        it('Must succeed', function(){

            $httpBackend
                .when('GET', '/vote-no-restaurante/ranking/total')
                .respond(200, []);

            RankingService.getRankingGlobal(userFixture).then(function (data) {
                expect(data).to.exist;
            });

            $httpBackend.flush();
        });

        it('reports error if server fails', function() {
            $httpBackend
                .when('GET', '/vote-no-restaurante/ranking/total', userFixture)
                .respond(500, {description: 'an error has occurred'});

            RankingService.getRankingGlobal(userFixture).catch(function (data) {
                expect(data).to.exist;
                expect(data).to.be.equal('an error has occurred');
            });

            $httpBackend.flush();
        });
    });


});
