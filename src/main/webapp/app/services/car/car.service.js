(function () {
    'use strict';

    angular
        .module('platformWebApp')
        .factory('Car', Car);

    Car.$inject = ['$resource'];

    function Car ($resource) {
        var service = $resource('api/cars/:id', {}, {
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
