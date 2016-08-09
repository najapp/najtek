angular.module('NAJTek').controller(
		'NavigationController',
		[ '$scope', 'AuthenticationService', '$location', 'Notification',
				function($scope, AuthenticationService, $location, Notification) {
					this.logout = function() {
						AuthenticationService.logout(function(success) {
							if (success) {
								Notification.success('Logged-out successfully');	
								$location.path("/login");
							}
						});
					};
				} ]);