(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('CarManagementDialogController',CarManagementDialogController);

    CarManagementDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Car', 'User', '$scope', 'Account'];

    function CarManagementDialogController ($stateParams, $uibModalInstance, entity, Car, User, $scope, Account) {
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
                console.log('Updating....');
                vm.car.user = null;
                getUserByEmail($scope.currentUser);
                vm.car.user = $scope.toSave;
                Car.update(vm.car, onSaveSuccess, onSaveError);
            } else {
                console.log('Saving....');
                vm.car.user = null;
                getUserByEmail($scope.currentUser);
                vm.car.user = $scope.toSave;
                Car.save(vm.car, onSaveSuccess, onSaveError);
            }
        }

         $scope.users = User.query({}, function (data) {
            $scope.currentUser = vm.car.user.email;
            console.log("$scope.currentUser", $scope.currentUser);
         });

         function getUserByEmail(mail) {
                for (var index = 0; index < $scope.users.length; index++) {
                    if($scope.users[index].email == mail) {
                        $scope.toSave = $scope.users[index];
                    }
                }
         }

         $scope.auth = Account.get({}, function (response) {
              console.log("currentRole", response.data.authorities[0]);
              $scope.currentRole = response.data.authorities[0];
         });

    }
})();
