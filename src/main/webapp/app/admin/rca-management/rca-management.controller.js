(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('RcaManagementController', RcaManagementController);

    RcaManagementController.$inject = ['Principal', 'Rca', 'ParseLinks', 'AlertService', '$state', 'pagingParams', 'paginationConstants'];

    function RcaManagementController(Principal, Rca, ParseLinks, AlertService, $state, pagingParams, paginationConstants) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.currentAccount = null;
        vm.languages = null;
        vm.loadAll = loadAll;
        vm.setActive = setActive;
        vm.rcas = [];
        vm.page = 1;
        vm.totalItems = null;
        vm.clear = clear;
        vm.links = null;
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.transition = transition;

        vm.loadAll();
        Principal.identity().then(function(account) {
            vm.currentAccount = account;
        });

        function setActive (rca, isActivated) {
            rca.car.user.activated = isActivated;
            Rca.update(car, function () {
                vm.loadAll();
                vm.clear();
            });
        }

        function loadAll () {
            Rca.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
            }, onSuccess, onError);
        }

        function onSuccess(data, headers) {
            //hide anonymous user from user management: it's a required user for Spring Security
            var hiddenCarsSize = 0;
            for (var i in data) {
                if (data[i]['login'] === 'anonymoususer') {
                    data.splice(i, 1);
                    hiddenCarsSize++;
                }
            }
            vm.totalItems = data.length;
            vm.queryCount = vm.totalItems;
            vm.page = pagingParams.page;
            vm.rcas = data;
        }

        function onError(error) {
            AlertService.error(error.data.message);
        }

        function clear () {
            vm.rca = {
                id: null, name: null, description: null, not_before: null, not_after: null,
                nr_inregistrare: null, car: null
            };
        }

        function sort () {
            var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
            if (vm.predicate !== 'id') {
                result.push('id');
            }
            return result;
        }

        function loadPage (page) {
            vm.page = page;
            vm.transition();
        }

        function transition () {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
    }
})();
