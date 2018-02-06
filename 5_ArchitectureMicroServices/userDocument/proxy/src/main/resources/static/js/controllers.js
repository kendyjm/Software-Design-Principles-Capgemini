var userDocumentControllers = angular.module('userDocumentControllers', []);


userDocumentControllers.controller('userDocumentsCtrl', [
		'$scope',
		'$http',
		'$location',
		'userDocumentService',
		function($scope, $http, $location, userDocumentService) {
			$scope.membre = {email:'Votre email',password:'Votre mot de passe'};
			
			// Enter in the application
			$scope.enter = function() {
				userDocumentService.authenticate($scope.membre, function(response) {
					$scope.membre = response;
					userDocumentService.getDocuments($scope.membre,function(response) {
						$scope.parent.documents = response;
						return $location.path('/documents');
					})
				},function() {
					$scope.$parent.message = 'Unable to authenticate with given credentials';
					$scope.$parent.showMessage=true;
				})
						
			};
			// Register a member
			$scope.register = function() {
				
			};

		} ]);

userDocumentControllers.controller('documentsCtrl', [
                                                 		'$scope',
                                                 		'$http',
                                                 		'$location',
                                                 		'userDocumentService',
                                                 		function($scope, $http, $location, userDocumentService) {
                                                 			// A COMPLETER
                                                 		} ]);

userDocumentControllers.controller('parentCtrl', [
                                              		'$scope',
                                              		function($scope) {
                                              			$scope.message = '';

                                              		} ]);
