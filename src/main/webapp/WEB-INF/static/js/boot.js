
requirejs
		.config({
			"baseUrl" : "js",
			"paths" : {
				"html" : "../html",

				"jquery" : "//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min",
				"underscore" : "//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.4.4/underscore-min",
				"backbone" : "//cdnjs.cloudflare.com/ajax/libs/backbone.js/1.0.0/backbone-min",
				"text" : "//cdnjs.cloudflare.com/ajax/libs/require-text/2.0.5/text",
				"transparency": "https://raw.github.com/leonidas/transparency/master/dist/transparency.min"
			},

			shim : {
				'jquery' : {
					exports : '$'
				},
				'backbone' : {
					deps : [ 'underscore', 'jquery' ],
					exports : 'Backbone'
				},
				'underscore' : {
					exports : '_'
				}
			}
		});

// Load the main app module to start the app
requirejs([ "main" ]);