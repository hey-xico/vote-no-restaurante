describe('UserController', function () {

    beforeEach(function () {
        bard.appModule('app.ranking');
        bard.inject('$controller', '$q', '$rootScope');
    });

    beforeEach(function () {
        RankingController = $controller('RankingController');
        $rootScope.$apply();
    });

    describe('', function(){

    });

});

