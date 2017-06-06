(function () {
    'use strict';

    angular
        .module('platformWebApp')
        .factory('Rca', Rca);

    Rca.$inject = ['$resource'];

    function Rca ($resource) {
        var service = $resource('api/rca/:id', {}, {
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
