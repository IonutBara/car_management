(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('RcaManagementDeleteController', RcaManagementDeleteController);

    RcaManagementDeleteController.$inject = ['$uibModalInstance', 'entity', 'Rca'];

    function RcaManagementDeleteController ($uibModalInstance, entity, Rca) {
        var vm = this;

        vm.car = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Rca.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
