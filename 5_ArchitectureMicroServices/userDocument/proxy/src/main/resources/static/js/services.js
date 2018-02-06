

var competenceService = angular.module('competenceService', []);

var userDocumentService = angular.module('userDocumentService', []);

userDocumentService.factory('userDocumentService', 
		function($http) {
			return {
				authenticate : function(membre, callback, errorHandler) {
					// A COMPLETER
					$http.post("/members-service/",membre).success(callback).error(errorHandler);
				},
				register : function(idIntervenant,callback) {
					// A COMPLETER
				},
				getDocuments : function(membre,callback) {
					$http.get('/documents-service/Documents/owner/'+membre.email+'/').success(callback);
				}
			};
		} );

