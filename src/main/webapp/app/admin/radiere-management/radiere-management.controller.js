(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('RadiereManagementController', RadiereManagementController);

    RadiereManagementController.$inject = ['Principal', 'Radiere', 'ParseLinks', 'AlertService', '$state', 'pagingParams', 'paginationConstants'];

    function RadiereManagementController(Principal, Radiere, ParseLinks, AlertService, $state, pagingParams, paginationConstants) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.currentAccount = null;
        vm.languages = null;
        vm.loadAll = loadAll;
        vm.setActive = setActive;
        vm.erasers = [];
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

        function setActive (radiere, isActivated) {
            radiere.car.user.activated = isActivated;
            Radiere.update(car, function () {
                vm.loadAll();
                vm.clear();
            });
        }

        function loadAll () {
            Radiere.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
            }, onSuccess, onError);
        }

        function onSuccess(data, headers) {
            vm.queryCount = vm.totalItems;
            vm.page = pagingParams.page;
            vm.erasers = data;
        }

        function onError(error) {
            AlertService.error(error.data.message);
        }

        function clear () {
            vm.radiere = {
                id: null, name: null, description: null, motivul: null, data: null,
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
