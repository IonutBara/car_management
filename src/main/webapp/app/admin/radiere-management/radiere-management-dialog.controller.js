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
                console.log('Updating....');
                vm.radiere.car = null;
                getCarByName($scope.current);
                vm.radiere.car = $scope.toSave;
                Radiere.update(vm.radiere, onSaveSuccess, onSaveError);
            } else {
                console.log('Saving....');
                vm.radiere.car = null;
                getCarByName($scope.current);
                vm.radiere.car = $scope.toSave;
                Radiere.save(vm.radiere, onSaveSuccess, onSaveError);
            }
        }

         $scope.cars = Car.query({}, function data() {
            $scope.current = vm.radiere.car.name;
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
