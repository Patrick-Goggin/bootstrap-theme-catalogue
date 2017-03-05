var Controllers = angular.module('Controllers', ['app.services']);

Controllers.controller('ThemeController', ['$scope', '$http', '$rootScope','$global', function ($scope, $http, $rootScope, $global) {

    $scope.showForm = true;

    $scope.ui = 'css/bootstrap.min.css';

    $scope.changePath = function() {
        $scope.ui = 'css/bootstrap.min.css';
    };

    $scope.styles = $global.getList();

    $scope.changeUI = function(style){
        $scope.ui = style.cdn;
        angular.element('.btn').blur();
    }

    $scope.restoreDefault = function(){
        $scope.ui = 'css/bootstrap.min.css';
    }

    $scope.formTheme = {
        name: '',
        description: '',
        cdn: '',
        price: '',
        provider: '',
        website: '',
    };

    $scope.create = function(formTheme){
        var toPost = {
            name: $scope.formTheme.name,
            description: $scope.formTheme.description,
            cdn: $scope.formTheme.cdn,
            price: $scope.formTheme.price,
            provider: $scope.formTheme.provider,
            website: $scope.formTheme.website,
        };
        $http.post("/theme/create", toPost)
            .then(function(response){
            resetForm();
            $global.getList();
            createStyleSheet(response.data);
        });
        testList();
        resetForm();
        $scope.newThemeName = null;
        $scope.formTheme = null;
    }

    function createStyleSheet(newTheme){
        var css;
        var name = newTheme.name;
        var themeId = newTheme.id;
        var uri = newTheme.cdn;
        $http.get(uri)
            .then(function (response) {
            css = response.data.toString();
            var parameter = JSON.stringify({type: "Css",
                                            name: name,
                                            themeId: themeId,
                                            css: css});
            $http.post("/style", parameter)
                .then(function(response){
                $scope.css = response.data.css;
                $scope.cssTest = response;
            });
        });
    }

    $scope.getStyleSheet = function(theme){
        $http.get('added/' + theme.id + '.css')
            .then(function (response) {
            if(response.data){
                $scope.styleSheet = response.data;
            }
            $scope.styleSheet = response.data;
        });
    }

    // test to be sure theme has been created and list updated
    function testList(){
        function check(l){
            var l = $rootScope.list;
            return l;
        }(function doit(){
            setTimeout(function(){
                console.log(
                    "list length: " + $rootScope.list.length + "\n"
                    + "last name added: " + $rootScope.list[$rootScope.list.length - 1].name)}, 3000);
        }());
    }

    function resetForm(){
        angular.element('#add-theme-name').val('');
        angular.element('#add-theme-description').val('');
        angular.element('#add-theme-cdn').val('');
        angular.element('#add-theme-provider').val('');
        angular.element('#add-theme-website').val('');
        angular.element('#add-theme-price').val('');
    }

    $scope.selectedIndex = -1;

    $scope.itemClicked = function ($index, aTheme) {
        console.log($index);
        $scope.selectedIndex = $index;
        $global.setDetails(aTheme);
    }

    $scope.updateTheme = function(themeDetails){
        var toPatch = {
            id: $scope.themeToEdit.id,
            name: $scope.themeToEdit.name,
            description: $scope.themeToEdit.description,
            cdn: $scope.themeToEdit.cdn,
            price: $scope.themeToEdit.price,
            provider: $scope.themeToEdit.provider,
            website: $scope.themeToEdit.website,
        };
        $http.patch("/theme/update", toPatch)
            .then(function(response){
            $scope.theme = null;
            if (response.data == null) {
                $scope.theme = null;
            }
              $global.setDetails(response.data);
            var li = $global.getList();
            $global.setList(li);
        });
    }

}]);

Controllers.controller('ListController', ['$scope', '$http', '$rootScope','$global', function ($scope, $http, $rootScope, $global) {

    $rootScope.list = $global.getList();

    $scope.updateList =  function(){
        $scope.list = $global.getList();
    }

    $scope.deleteAll = function(){
        $http.delete('/theme/delete/all')
            .then(function (response) {
            $rootScope.list = $global.getList();
            $global.setList(response.data);
            $global.clearDetails();
        });
    }

$scope.viewDetails = function(aTheme){
        var themeDetails = aTheme;
        $global.clearDetails();
        $global.setDetails(themeDetails);
        $rootScope.themeDetails = aTheme;
    }

    $scope.set_color = function (aTheme) {
            var list = $scope.list;
            var c = '';
            for(i=0; i < list.length; i++){
                var theme = list[i];
                if(aTheme.id == theme.id){
                    if(4 !== 0 && i%3 !== 0 && i%2 !== 0 && i%1 !== 0){
                        c = 'info';
                        $scope.list[i].c = c;
                        return c;
                    }
                    if(i == 0){
                        c = 'info';
                        $scope.list[i].c = c;
                        return c;
                    }
                    if(i%5 == 0){
                        c = 'info';
                        $scope.list[i].c = c;
                        return c;
                    }
                    if(i%4 !== 0 && i%3 !== 0 && i%2 !== 0 && i%1 == 0){
                        c = 'success';
                        $scope.list[i].c = c;
                        return c;
                    }
                    if(i%4 !== 0 && i%2 == 0){
                        c = 'danger';
                        $scope.list[i].c = c;
                        return c;
                    }
                    if(i%3 == 0){
                        c = 'warning';
                        $scope.list[i].c = c;
                        return c;
                    }
                    if(i%4 == 0 ){
                        c = 'active';
                        $scope.list[i].c = c;
                        return c;
                    }
                }
            }
        }

    $scope.button_color = function (item) {
        var list = $scope.list;
        for(i=0; i < list.length; i++){
            var theme = list[i];
            if(item.id == theme.id){
                if(4 !== 0 && i%3 !== 0 && i%2 !== 0 && i%1 !== 0){
                    return 'btn-info';
                }
                if(i == 0){
                    return 'btn-info';
                }
                if(i%5 == 0){
                    return 'btn-info';
                }
                if(i%4 !== 0 && i%3 !== 0 && i%2 !== 0 && i%1 == 0){
                    return 'btn-success';
                }
                if(i%4 !== 0 && i%2 == 0){
                    return 'btn-danger';
                }
                if(i%3 == 0){
                    return 'btn-warning';
                }
                if(i%4 == 0 ){
                    return 'btn-active';
                }
            }
        }
    }

}]);

Controllers.controller('DetailsController', ['$scope', '$http', '$rootScope','$global', function ($scope, $http, $rootScope, $global) {

    $scope.hideDetails = function(){
        $global.clearDetails();
    }

    $scope.checkPrice = function(aTheme){
            if(aTheme.price == 0 || aTheme.price == null){
                return "Free";
            }else
                return aTheme.price;
        }

    $scope.editTheme = function(themeDetails){
        $rootScope.themeToEdit = themeDetails;
    }

    $scope.deleteTheme = function(themeDetails){
        var id = themeDetails.id;
        $http.delete('/theme/'+id)
            .then(function (response) {
            $global.setList(response.data);
            $global.clearDetails();
            $global.getList();
        });
    }

}]);


