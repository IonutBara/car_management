(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('alert-management', {
            parent: 'entity',
            url: '/alert-management?page&sort',
            data: {
                authorities: ['ROLE_ADMIN','ROLE_USER'],
                pageTitle: 'Alerts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/alert-management/alert-management.html',
                    controller: 'AlertManagementController',
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
 /*       .state('alert-management-detail', {
            parent: 'entity',
            url: '/alert/:id',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'platformWebApp'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/alert-management/alert-management-detail.html',
                    controller: 'CarManagementDetailController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('alert-management.new', {
            parent: 'alert-management',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alert-management/alert-management-dialog.html',
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
                    $state.go('alert-management', null, { reload: true });
                }, function() {
                    $state.go('alert-management');
                });
            }]
        })
        .state('alert-management.edit', {
            parent: 'alert-management',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alert-management/alert-management-dialog.html',
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
                    $state.go('alert-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('alert-management.delete', {
            parent: 'alert-management',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alert-management/alert-management-delete-dialog.html',
                    controller: 'CarManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Car', function(Car) {
                            return Car.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('alert-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        }  */
        //);
    }
})();
