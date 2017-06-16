(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .controller('CascoManagementController', CascoManagementController);

    CascoManagementController.$inject = ['Principal', 'Casco', 'ParseLinks', 'AlertService', '$state', 'pagingParams', 'paginationConstants'];

    function CascoManagementController(Principal, Casco, ParseLinks, AlertService, $state, pagingParams, paginationConstants) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.currentAccount = null;
        vm.languages = null;
        vm.loadAll = loadAll;
        vm.setActive = setActive;
        vm.cascos = [];
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

        function setActive (casco, isActivated) {
            casco.car.user.activated = isActivated;
            Casco.update(car, function () {
                vm.loadAll();
                vm.clear();
            });
        }

        function loadAll () {
            Casco.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
            }, onSuccess, onError);
        }

        function onSuccess(data, headers) {
            vm.totalItems = data.length;
            vm.queryCount = vm.totalItems;
            vm.page = pagingParams.page;
            vm.cascos = data;
        }

        function onError(error) {
            AlertService.error(error.data.message);
        }

        function clear () {
            vm.casco = {
                id: null, name: null, description: null, observatii: null, not_before: null,
                 not_after: null, nr_inregistrare: null, sumaAsigurata: null,
                 moneda: null, clauzeSpeciale: null, altePrecizari: null, car: null
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
