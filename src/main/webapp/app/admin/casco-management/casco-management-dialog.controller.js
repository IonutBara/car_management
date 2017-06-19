(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('CascoManagementDialogController',CascoManagementDialogController);

    CascoManagementDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Casco', 'Car', '$scope'];

    function CascoManagementDialogController ($stateParams, $uibModalInstance, entity, Casco, Car, $scope) {
        var vm = this;

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
                console.log('Updating....');
                vm.casco.car = null;
                getCarByName($scope.current);
                vm.casco.car = $scope.toSave;
                Casco.update(vm.casco, onSaveSuccess, onSaveError);
            } else {
                console.log('Saving....');
                vm.casco.car = null;
                getCarByName($scope.current);
                vm.casco.car = $scope.toSave;
                Casco.save(vm.casco, onSaveSuccess, onSaveError);
            }
        }

         $scope.cars = Car.query({}, function data() {
            $scope.current = vm.casco.car.name;
            console.log("$scope.current", $scope.current);
         });

         function getCarByName(carName) {
                for (var index = 0; index < $scope.cars.length; index++) {
                    if($scope.cars[index].name == carName) {
                        $scope.toSave = $scope.cars[index];
                    }
                }
         }
    }
})();
