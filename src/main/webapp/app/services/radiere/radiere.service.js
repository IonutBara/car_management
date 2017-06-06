(function () {
    'use strict';

    angular
        .module('platformWebApp')
        .factory('Radiere', Radiere);

    Radiere.$inject = ['$resource'];

    function Radiere ($resource) {
        var service = $resource('api/radiere/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': { method:'POST' },
            'update': { method:'PUT' },
            'delete':{ method:'DELETE'}
        });

        return service;
    }
})();
