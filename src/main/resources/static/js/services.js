angular.module('app.services',[])
    .factory('$global',['$http', '$rootScope', function($http, $rootScope){
        return {
            getList : function(){
                var li;
                $http.get('/themes')
                    .then(function (response) {
                    if(response.data.length > 0){
                        $rootScope.list = response.data;
                        return $rootScope.list;
                    }
                    if(response.data.length < 1){
                        $rootScope.list = null;
                        return $rootScope.list;
                    }
                });
                return $rootScope.list;
            },

            setList: function(data){
                 $rootScope.list = data;
            },

            setDetails : function(data){
                $rootScope.thingDetails = data;
                //$rootScope.thingDetails.c = (data.c);
                return data;
            },

            getDetails : function(){
                return $rootScope.thingDetails;
            },

            clearDetails : function(){
                $rootScope.thingDetails = null;
                return $rootScope.thingDetails;
            },

            setUI : function(data){
                $rootScope.ui = data;
                alert($rootScope.ui);
                return $rootScope.ui;
            },

            getUI : function(){
                return $rootScope.ui;
            }
        };
    }]);
