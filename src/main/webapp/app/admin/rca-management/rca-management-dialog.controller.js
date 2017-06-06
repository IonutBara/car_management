(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('RcaManagementDialogController',RcaManagementDialogController);

    RcaManagementDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Rca', 'Car', '$scope'];

    function RcaManagementDialogController ($stateParams, $uibModalInstance, entity, Rca, Car, $scope) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.clear = clear;
        vm.languages = null;
        vm.save = save;
        vm.rca = entity;

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
            if (vm.rca.id !== null) {
                vm.rca.car = vm.rca.car[0];
                Rca.update(vm.rca, onSaveSuccess, onSaveError);
            } else {
                vm.rca.car = vm.rca.car[0];
                Rca.save(vm.rca, onSaveSuccess, onSaveError);
            }
        }

         $scope.cars = Car.query({}, function data(){});
         vm.rca.car = $scope.cars[0];

         function getCarByName() {
                for (var index = 0; index < $scope.cars.length; index++) {
                    vm.rca.car = $scope.cars[index].id;
                }
         }
    }
})();
