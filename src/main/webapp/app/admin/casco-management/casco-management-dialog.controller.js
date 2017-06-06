(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('CascoManagementDialogController',CascoManagementDialogController);

    CascoManagementDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Casco', 'Car', '$scope'];

    function CascoManagementDialogController ($stateParams, $uibModalInstance, entity, Casco, Car, $scope) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.clear = clear;
        vm.languages = null;
        vm.save = save;
        vm.casco = entity;

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
            if (vm.casco.id !== null) {
                vm.casco.car = vm.casco.car[0];
                console.log('vm.casco.altePrecizari',vm.casco.altePrecizari);
                 console.log('vm.casco.clauzeSpeciale',vm.casco.clauzeSpeciale);
                  console.log('vm.casco.sumaAsigurata',vm.casco.sumaAsigurata);
                Casco.update(vm.casco, onSaveSuccess, onSaveError);
            } else {
                vm.casco.car = vm.casco.car[0];
                Casco.save(vm.casco, onSaveSuccess, onSaveError);
            }
        }

         $scope.cars = Car.query({}, function data(){});
         vm.casco.car = $scope.cars[0];

         function getCarByName() {
                for (var index = 0; index < $scope.cars.length; index++) {
                    vm.casco.car = $scope.cars[index].id;
                }
         }
    }
})();
