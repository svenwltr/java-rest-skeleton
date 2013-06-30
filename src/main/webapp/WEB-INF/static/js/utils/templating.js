define([ "jquery", "backbone", "transparency" ], function($, Backbone,
		Transparency) {

	$.fn.render = Transparency.jQueryPlugin;

	Transparency.matcher = function(element, key) {
		return element.el.getAttribute('data-bind') == key;
	};

	return {
		find : function(element, key) {
			var selector = "[data-bind='" + key + "']";

			if (element.is(selector))
				return element.filter(selector);
			else
				return element.find(selector);

		},

		extract : function(element, key) {
			return this.find(element, key).clone();
		},

		render : function(element, data) {
			return element.render(data);

		},

		append : function(element, key, item) {
			this.find(element, key).append(item);

		}
	};

});