(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('CascoManagementDetailController', CascoManagementDetailController);

    CascoManagementDetailController.$inject = ['$stateParams', 'Casco'];

    function CascoManagementDetailController ($stateParams, Casco) {
        var vm = this;

        vm.load = load;
        vm.casco = {};

        vm.load($stateParams.id);

        function load (id) {
            Casco.get({id: id}, function(result) {
                vm.casco = result;
            });
        }
    }
})();
