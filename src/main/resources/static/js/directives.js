var Directives = angular.module('Directives', []);

//Directives.directive('list', function() {
//
//return {
//    restrict: 'E',
//    name:,'@'
//    template: '<div class="label label-primary label-lg">{{theme.name}}</div>'
//           })
//           .controller('DirCtrl'{
//
//
//           });
//           }

Directives.directive('themeDetails', function(){
    return{
        restrict:'EA',
        template:"<div>Name: {{list.theme.name}}<br>"+
            "<div>Description: {{list.theme.description}}</div>"
    };
});

  Directives.directive('themePanels', function () {
      return {
          restrict: 'EA',
          template: "<div ng-repeat='theme in list'>" +
              "<span>{{theme.name}}</span>, <span>{{theme.description}}</span>, " +
              "</div>"
      };
  });


//  Directives.directive('themeTable', function () {
//      return {
//          restrict: 'EA',
//          template:
//      };
//  });





//Directives.directive('ngSparkline', function() {
//                      var url = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=imperial&cnt=14&callback=JSON_CALLBACK&q=";
//                      return {
//                        restrict: 'A',
//                        require: '^ngCity',
//                        scope: {
//                          ngCity: '@'
//                        },
//                        template: '<div class="sparkline"><h4>Weather for </h4><div class="graph"></div></div>',
//                        controller: ['$scope', '$http', function($scope, $http) {
//                          $scope.getTemp = function(city) {
//                            $http({
//                              method: 'JSONP',
//                              url: url + city
//                            }).success(function(data) {
//                              var weather = [];
//                              angular.forEach(data.list, function(value){
//                                weather.push(value);
//                              });
//                              $scope.weather = weather;
//                            });
//                          }
//                        }],
//                        link: function(scope, iElement, iAttrs, ctrl) {
//                          scope.getTemp(iAttrs.ngCity);
//                          scope.$watch('weather', function(newVal) {
//                            // the `$watch` function will fire even if the
//                            // weather property is undefined, so we'll
//                            // check for it
//                            if (newVal) {
//                              var highs = [],
//                                  width   = 200,
//                                  height  = 80;
//
//                              angular.forEach(scope.weather, function(value){
//                                highs.push(value.temp.max);
//                              });
//                              // chart
//                            }
//                          });
//                        }
//                      }
//                    });