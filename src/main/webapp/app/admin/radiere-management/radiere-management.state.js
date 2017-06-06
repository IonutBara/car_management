(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('radiere-management', {
            parent: 'admin',
            url: '/radiere-management?page&sort',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'Radiere auto'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/radiere-management/radiere-management.html',
                    controller: 'RadiereManagementController',
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
        .state('radiere-management-detail', {
            parent: 'admin',
            url: '/radiere/:id',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'platformWebApp'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/radiere-management/radiere-management-detail.html',
                    controller: 'RadiereManagementDetailController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('radiere-management.new', {
            parent: 'radiere-management',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/radiere-management/radiere-management-dialog.html',
                    controller: 'RadiereManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null, name: null, description: null,
                                not_after: null, not_before: null, nr_inregistrare: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('radiere-management', null, { reload: true });
                }, function() {
                    $state.go('radiere-management');
                });
            }]
        })
        .state('radiere-management.edit', {
            parent: 'radiere-management',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/radiere-management/radiere-management-dialog.html',
                    controller: 'RadiereManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Radiere', function(Radiere) {
                            return Radiere.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('radiere-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('radiere-management.delete', {
            parent: 'radiere-management',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/radiere-management/radiere-management-delete-dialog.html',
                    controller: 'RadiereManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Radiere', function(Radiere) {
                            return Radiere.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('radiere-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }
})();
