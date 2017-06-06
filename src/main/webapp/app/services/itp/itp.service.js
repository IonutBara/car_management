(function () {
    'use strict';

    angular
        .module('platformWebApp')
        .factory('Itp', Itp);

    Itp.$inject = ['$resource'];

    function Itp ($resource) {
        var service = $resource('api/itp/:id', {}, {
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
