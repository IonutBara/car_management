(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('ItpManagementDeleteController', ItpManagementDeleteController);

    ItpManagementDeleteController.$inject = ['$uibModalInstance', 'entity', 'Itp'];

    function ItpManagementDeleteController ($uibModalInstance, entity, Itp) {
        var vm = this;

        vm.car = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Itp.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
