module.exports = function (grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        uncss: {
            dist: {
                files: {
                    'dist/css/tidy.css': [
                        'src/main/resources/static/index.html'
                        ,'src/main/resources/static/votenorestaurante/app/ballot/ballot.html'
                        ,'src/main/resources/static/votenorestaurante/app/user/user.html'
                        ,'src/main/resources/static/votenorestaurante/app/ranking/ranking.html']
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-uncss');

    // Default task(s).
    grunt.registerTask('default', ['uncss']);



};
