(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('CarManagementDialogController',CarManagementDialogController);

    CarManagementDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Car'];

    function CarManagementDialogController ($stateParams, $uibModalInstance, entity, Car) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.clear = clear;
        vm.languages = null;
        vm.save = save;
        vm.car = entity;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function onSaveSuccess (result) {
            vm.isSaving = false;
            $uibModalInstance.close(result);
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        function save () {
            vm.isSaving = true;
            if (vm.car.id !== null) {
                Car.update(vm.car, onSaveSuccess, onSaveError);
            } else {
                Car.save(vm.car, onSaveSuccess, onSaveError);
            }
        }
    }
})();
