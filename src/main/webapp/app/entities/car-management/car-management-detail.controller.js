(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('CarManagementDetailController', CarManagementDetailController);

    CarManagementDetailController.$inject = ['$stateParams', 'Car'];

    function CarManagementDetailController ($stateParams, Car) {
        var vm = this;

        vm.load = load;
        vm.car = {};

        vm.load($stateParams.id);

        function load (id) {
            Car.get({id: id}, function(result) {
                vm.car = result;
            });
        }
    }
})();
