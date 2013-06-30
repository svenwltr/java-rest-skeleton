define([ "jquery", "backbone", "text!html/time.tpl.html", "utils/templating" ], function($,
		Backbone, template, Templating) {

	var Time = Backbone.Model.extend({

		defaults : function() {
			return {
				name : null,
				date : Date.now(),
				duration : null
			};
		}

	});

	var TimeList = Backbone.Collection.extend({
		model : Time,
		url : '/rest/time/'
			
	});

	var timeList = new TimeList;
	

	var TimeView = Backbone.View.extend({
		template : Templating.extract($(template), "item"),

		initialize : function() {
			this.el = this.template.clone();
			
			this.listenTo(this.model, 'change', this.render);

		},

		render : function() {
			Templating.render(this.el, this.model.toJSON());
			return this;
			
		}

	});

	var TimeListView = Backbone.View.extend({

		template : Templating.extract($(template), "timetable-list"),

		initialize : function() {
			this.listenTo(timeList, 'add', this.addOne);
			this.listenTo(timeList, 'all', this.render);
			
			this.el = this.template.clone();

			$("#timetable-list").replaceWith(this.el);
			
			timeList.fetch();

		},

		render : function() {
			Templating.render(this.el, {items: []});
			
		},

		addOne : function(time) {
			var view = new TimeView({
				model : time
			});
			
			Templating.append(this.el, "items", view.render().el);
			
		}

	});
	
	var TimeFormView = Backbone.View.extend({
		
	});

	return {
		init : function() {
			return new TimeListView;
		}

	};

});
