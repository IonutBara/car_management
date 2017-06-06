(function () {
    'use strict';

    angular
        .module('platformWebApp')
        .factory('Casco', Casco);

    Casco.$inject = ['$resource'];

    function Casco ($resource) {
        var service = $resource('api/casco/:id', {}, {
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
