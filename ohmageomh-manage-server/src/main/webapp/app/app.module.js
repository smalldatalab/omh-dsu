(function() {
    'use strict';

    angular
        .module('ohmageApp', [
            'ngStorage',
            'ngResource',
            'ngCookies',
            'ngAria',
            'ngCacheBuster',
            'ngFileUpload',
            'ui.bootstrap',
            'ui.bootstrap.datetimepicker',
            'ui.router',
            'infinite-scroll',
            'ds.objectDiff',
            'ui.grid',
            'ui.grid.exporter',
            'ui.grid.resizeColumns',
            'ui.grid.pagination',
            // jhipster-needle-angularjs-add-module JHipster will add new module here
            'angular-loading-bar',
            'ui.select',
            'ngSanitize',
            'mwl.calendar'])
        .run(run);

    run.$inject = ['stateHandler'];

    function run(stateHandler) {
        stateHandler.initialize();
    }
})();
