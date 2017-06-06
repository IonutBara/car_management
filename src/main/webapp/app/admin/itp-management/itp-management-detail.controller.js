(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('ItpManagementDetailController', ItpManagementDetailController);

    ItpManagementDetailController.$inject = ['$stateParams', 'Itp'];

    function ItpManagementDetailController ($stateParams, Itp) {
        var vm = this;

        vm.load = load;
        vm.rca = {};

        vm.load($stateParams.id);

        function load (id) {
            Itp.get({id: id}, function(result) {
                vm.rca = result;
            });
        }
    }
})();
