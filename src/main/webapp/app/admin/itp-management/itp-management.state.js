(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('itp-management', {
            parent: 'admin',
            url: '/itp-management?page&sort',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'Inspectie tehnica periodica'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/itp-management/itp-management.html',
                    controller: 'ItpManagementController',
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
        .state('itp-management-detail', {
            parent: 'admin',
            url: '/itp/:id',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'platformWebApp'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/itp-management/itp-management-detail.html',
                    controller: 'ItpManagementDetailController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('itp-management.new', {
            parent: 'itp-management',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/itp-management/itp-management-dialog.html',
                    controller: 'ItpManagementDialogController',
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
                    $state.go('itp-management', null, { reload: true });
                }, function() {
                    $state.go('itp-management');
                });
            }]
        })
        .state('itp-management.edit', {
            parent: 'itp-management',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/itp-management/itp-management-dialog.html',
                    controller: 'ItpManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Itp', function(Itp) {
                            return Itp.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('itp-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('itp-management.delete', {
            parent: 'itp-management',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/itp-management/itp-management-delete-dialog.html',
                    controller: 'ItpManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Itp', function(Itp) {
                            return Itp.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('itp-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }
})();
