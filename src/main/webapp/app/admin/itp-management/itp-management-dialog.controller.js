(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('ItpManagementDialogController',ItpManagementDialogController);

    ItpManagementDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Itp', 'Car', '$scope'];

    function ItpManagementDialogController ($stateParams, $uibModalInstance, entity, Itp, Car, $scope) {
        var vm = this;

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
                console.log('Updating....');
                vm.rca.car = null;
                getCarByName($scope.current);
                vm.rca.car = $scope.toSave;
                Itp.update(vm.rca, onSaveSuccess, onSaveError);
            } else {
                console.log('Saving....');
                vm.rca.car = null;
                getCarByName($scope.current);
                vm.rca.car = $scope.toSave;
                Itp.save(vm.rca, onSaveSuccess, onSaveError);
            }
        }

         $scope.cars = Car.query({}, function data() {
            $scope.current = vm.rca.car.name;
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
