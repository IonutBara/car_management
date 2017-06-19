(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('RcaManagementDetailController', RcaManagementDetailController);

    RcaManagementDetailController.$inject = ['$stateParams', 'Rca'];

    function RcaManagementDetailController ($stateParams, Rca) {
        var vm = this;

        vm.load = load;
        vm.rca = {};

        vm.load($stateParams.id);

        function load (id) {
            Rca.get({id: id}, function(result) {
                vm.rca = result;
                vm.currentCar = result.car;
            });
        }
    }
})();
