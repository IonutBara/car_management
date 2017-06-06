(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('RadiereManagementDialogController',RadiereManagementDialogController);

    RadiereManagementDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Radiere', 'Car', '$scope'];

    function RadiereManagementDialogController ($stateParams, $uibModalInstance, entity, Radiere, Car, $scope) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.clear = clear;
        vm.languages = null;
        vm.save = save;
        vm.radiere = entity;

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
            if (vm.radiere.id !== null) {
                vm.radiere.car = vm.radiere.car[0];
                Radiere.update(vm.radiere, onSaveSuccess, onSaveError);
            } else {
                vm.radiere.car = vm.radiere.car[0];
                Radiere.save(vm.radiere, onSaveSuccess, onSaveError);
            }
        }

         $scope.cars = Car.query({}, function data(){});
         vm.radiere.car = $scope.cars[0];

         function getCarByName() {
                for (var index = 0; index < $scope.cars.length; index++) {
                    vm.radiere.car = $scope.cars[index].id;
                }
         }
    }
})();
