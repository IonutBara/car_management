(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('CascoManagementDeleteController', CascoManagementDeleteController);

    CascoManagementDeleteController.$inject = ['$uibModalInstance', 'entity', 'Casco'];

    function CascoManagementDeleteController ($uibModalInstance, entity, Casco) {
        var vm = this;

        vm.car = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Casco.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
