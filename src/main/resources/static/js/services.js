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
                return $rootScope.ui;
            },

            getUI : function(){
                return $rootScope.ui;
            },

            setCurrentTheme : function(data){
                return $rootScope.currentTheme;
            },

            getCurrentTheme : function(){
                $http.get('/current')
                .then(function (response) {
                console.log(response.data);
                $rootScope.currentTheme = response.data;
                    if(response.data.cdn){
                        $rootScope.ui = response.data.cdn;
                        }else{$rootScope.ui = 'css/bootstrap.min.css';}
                    if(response.data.name){
                        $rootScope.currentTheme = response.data;
                        $rootScope.ui = response.data.cdn;
                    }
                });
                return $rootScope.currentTheme;
            },

            updateCurrentTheme : function(data){
            //$rootScope.ui = data.cdn;
            //$rootScope.currentTheme = data;
            var toPatch = {
                        id: $rootScope.currentTheme.id,
                        name: data.name,
                        currentId: data.id,
                        cdn: data.cdn,
                        localUri:data.localUri,
                        description: data.description,
                        color: data.c
                    };
            $http.patch("/current", toPatch)
                        .then(function(response){
                        console.log(response.data)
                        $rootScope.currentTheme = response.data;
                        $rootScope.ui = response.data.cdn;
                    });
                return $rootScope.currentTheme;
            }
        };
    }]);
