(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('RadiereManagementDeleteController', RadiereManagementDeleteController);

    RadiereManagementDeleteController.$inject = ['$uibModalInstance', 'entity', 'Radiere'];

    function RadiereManagementDeleteController ($uibModalInstance, entity, Radiere) {
        var vm = this;

        vm.car = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Radiere.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
