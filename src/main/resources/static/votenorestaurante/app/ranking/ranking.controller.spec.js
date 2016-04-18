describe('UserController', function () {
    var id = 1;

    var ballotBoxFixture = {
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
    };

    beforeEach(function () {
        bard.appModule('app.ranking');
        bard.inject(this, '$controller', '$q', '$rootScope', '$stateParams', 'RankingService');
    });

    beforeEach(function () {
        sinon.stub(RankingService, 'getUserRanking').returns($q.when(ballotBoxFixture));
        sinon.stub(RankingService, 'getOverallRanking').returns($q.when(ballotBoxFixture));
        RankingController = $controller('RankingController');
        $rootScope.$apply();
    });

    bard.verifyNoOutstandingHttpRequests();


    describe('CustomerDetail controller', function() {
        it('should be created successfully', function() {
            expect(RankingController).to.be.defined;
        });

        describe('after activate', function() {

            describe('should have called RankingService.getUserRanking', function () {
                it('1 time', function () {
                    expect(RankingService.getUserRanking).to.have.been.calledOnce;
                });

                it('thant set userRankingData', function () {
                    expect(RankingController.userRankingData).to.be.definded;
                });

            });

            describe('should have called RankingService.getOverallRanking', function () {
                it('1 time', function () {
                    expect(RankingService.getOverallRanking).to.have.been.calledOnce;
                });

                it('thant set overallRankingData', function () {
                    expect(RankingController.overallRankingData).to.be.definded;
                });

            });
        });
    });

});

