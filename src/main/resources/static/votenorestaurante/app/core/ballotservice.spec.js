describe('BallotService', function () {

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

        bard.appModule('app.core');
        bard.inject('$http', '$httpBackend', '$q', 'BallotService', '$rootScope');

    });

    it('exists', function(){
        expect(BallotService).to.exist;

    });

    it('getCombinations hits the right end-point', function(){

        $httpBackend
            .when('GET','ballot/get-combinations')
            .respond(200, [combinationFixture]);

        BallotService.getCombinations().then(function (data) {
            expect(data).to.exist;
            expect(data[0][1].length).to.be.equal(2);
        });

        $httpBackend.flush();
    });

    it('Given a valid ballotBox, must successfully submit it', function(){
        $httpBackend
            .when('POST', '/vote-no-restaurante/ballot/submit')
            .respond(200, ballotBoxFixture);

        BallotService.submitBallot(ballotBoxFixture).then(function (data) {
            expect(data).to.exist;
            expect(data.user_id).to.be.equal(1);
        });
        $httpBackend.flush();
    });

    it('submitBallot reports error if server fails', function() {
        $httpBackend
            .when('POST', '/vote-no-restaurante/ballot/submit')
            .respond(500, {description: 'Failure'});

        BallotService.submitBallot(ballotBoxFixture).catch(function (error) {
            expect(error).to.be.equal('Failure');
        });

        $httpBackend.flush();
    });

});
