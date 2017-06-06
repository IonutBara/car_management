(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('ItpManagementDialogController',ItpManagementDialogController);

    ItpManagementDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Itp', 'Car', '$scope'];

    function ItpManagementDialogController ($stateParams, $uibModalInstance, entity, Itp, Car, $scope) {
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
                Itp.update(vm.rca, onSaveSuccess, onSaveError);
            } else {
                vm.rca.car = vm.rca.car[0];
                Itp.save(vm.rca, onSaveSuccess, onSaveError);
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
