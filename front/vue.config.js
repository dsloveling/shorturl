'use strict';
// Template version: 1.3.1
// see http://vuejs-templates.github.io/webpack for documentation.

module.exports = {
	devServer: {
		port: 8081,
		proxy: {
			'/api': {
				target: 'http://172.23.23.116:8080',
			}
		}
	},

};
