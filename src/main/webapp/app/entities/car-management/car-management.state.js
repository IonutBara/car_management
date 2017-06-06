(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('car-management', {
            parent: 'entity',
            url: '/car-management?page&sort',
            data: {
                authorities: ['ROLE_ADMIN','ROLE_USER'],
                pageTitle: 'Cars'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/car-management/car-management.html',
                    controller: 'CarManagementController',
                    controllerAs: 'vm'
                }
            },            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                }
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort)
                    };
                }]
            }        })
        .state('car-management-detail', {
            parent: 'entity',
            url: '/car/:id',
            data: {
                authorities: ['ROLE_ADMIN','ROLE_USER'],
                pageTitle: 'platformWebApp'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/car-management/car-management-detail.html',
                    controller: 'CarManagementDetailController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('car-management.new', {
            parent: 'car-management',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN','ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/car-management/car-management-dialog.html',
                    controller: 'CarManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null, name: null, description: null, marca: null, model: null,
                                versiune: null, anFabricatie: null, capacitateCilindrica: null, combustibil: null,
                                cutieViteza: null, transmisie: null, caroserie: null, culoare:null, putere:null,
                                nrInmatriculare: null, nrKm: null, user: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('car-management', null, { reload: true });
                }, function() {
                    $state.go('car-management');
                });
            }]
        })
        .state('car-management.edit', {
            parent: 'car-management',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN','ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/car-management/car-management-dialog.html',
                    controller: 'CarManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Car', function(Car) {
                            return Car.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('car-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('car-management.delete', {
            parent: 'car-management',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN','ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/car-management/car-management-delete-dialog.html',
                    controller: 'CarManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Car', function(Car) {
                            return Car.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('car-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }
})();
