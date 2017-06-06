(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('RadiereManagementDetailController', RadiereManagementDetailController);

    RadiereManagementDetailController.$inject = ['$stateParams', 'Radiere'];

    function RadiereManagementDetailController ($stateParams, Radiere) {
        var vm = this;

        vm.load = load;
        vm.radiere = {};

        vm.load($stateParams.id);

        function load (id) {
            Radiere.get({id: id}, function(result) {
                vm.radiere = result;
            });
        }
    }
})();
