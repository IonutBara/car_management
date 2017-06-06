(function () {
    'use strict';

    angular
        .module('platformWebApp')
        .factory('Alert', Alert);

    Alert.$inject = ['$resource'];

    function Alert ($resource) {
        var service = $resource('api/alerts/:id', {}, {
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
