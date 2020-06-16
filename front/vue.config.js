'use strict';
// Template version: 1.3.1
// see http://vuejs-templates.github.io/webpack for documentation.

module.exports = {
	devServer: {
		port: 8081,
		proxy: {
			'/api': {
				target: 'http://192.168.1.15:8080',
			}
		}
	},

};
