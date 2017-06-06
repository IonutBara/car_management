(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rca-management', {
            parent: 'admin',
            url: '/rca-management?page&sort',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'Asigurare Rca'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/rca-management/rca-management.html',
                    controller: 'RcaManagementController',
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
        .state('rca-management-detail', {
            parent: 'admin',
            url: '/rca/:id',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'platformWebApp'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/rca-management/rca-management-detail.html',
                    controller: 'RcaManagementDetailController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('rca-management.new', {
            parent: 'rca-management',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/rca-management/rca-management-dialog.html',
                    controller: 'RcaManagementDialogController',
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
                    $state.go('rca-management', null, { reload: true });
                }, function() {
                    $state.go('rca-management');
                });
            }]
        })
        .state('rca-management.edit', {
            parent: 'rca-management',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/rca-management/rca-management-dialog.html',
                    controller: 'RcaManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Rca', function(Rca) {
                            return Rca.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('rca-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rca-management.delete', {
            parent: 'rca-management',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/rca-management/rca-management-delete-dialog.html',
                    controller: 'RcaManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Rca', function(Rca) {
                            return Rca.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('rca-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }
})();
