(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('casco-management', {
            parent: 'admin',
            url: '/casco-management?page&sort',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'Inspectie tehnica periodica'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/casco-management/casco-management.html',
                    controller: 'CascoManagementController',
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
        .state('casco-management-detail', {
            parent: 'admin',
            url: '/casco/:id',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'platformWebApp'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/casco-management/casco-management-detail.html',
                    controller: 'CascoManagementDetailController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('casco-management.new', {
            parent: 'casco-management',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/casco-management/casco-management-dialog.html',
                    controller: 'CascoManagementDialogController',
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
                    $state.go('casco-management', null, { reload: true });
                }, function() {
                    $state.go('casco-management');
                });
            }]
        })
        .state('casco-management.edit', {
            parent: 'casco-management',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/casco-management/casco-management-dialog.html',
                    controller: 'CascoManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Casco', function(Casco) {
                            return Casco.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('casco-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('casco-management.delete', {
            parent: 'casco-management',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/casco-management/casco-management-delete-dialog.html',
                    controller: 'CascoManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Casco', function(Casco) {
                            return Casco.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('casco-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }
})();
