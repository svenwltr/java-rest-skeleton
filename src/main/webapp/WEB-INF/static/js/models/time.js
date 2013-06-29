define([ "jquery", "backbone", "text!html/time.tpl.html" ], function($,
		Backbone, template) {

	var template = $(template);

	var selectors = {
		root : "#timetable-widget",
		item : "#timetable-widget-item"
	}

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
		template : template.find(selectors.item),

		initialize : function() {
			this.el = this.template.clone();
			this.el.removeAttr("id");

			this.elValues = {
				id : this.el.find("#time-value-id"),
				name : this.el.find("#time-value-name"),
				date : this.el.find("#time-value-date"),
				duration : this.el.find("#time-value-duration")
				
			}
			
			this.elValues.id.removeAttr("id");
			this.elValues.name.removeAttr("id");
			this.elValues.date.removeAttr("id");
			this.elValues.duration.removeAttr("id");
			
			this.listenTo(this.model, 'change', this.render);

		},

		render : function() {
			this.elValues.id.text(this.model.id);
			this.elValues.name.text(this.model.get("name"));
			this.elValues.date.text(this.model.get("date"));
			this.elValues.duration.text(this.model.get("duration"));
			
			return this;
		}

	});

	var TimeListView = Backbone.View.extend({

		template : template.filter(selectors.root),

		initialize : function() {
			this.listenTo(timeList, 'add', this.addOne);
			this.listenTo(timeList, 'reset', this.addAll);
			this.listenTo(timeList, 'all', this.render);

			timeList.fetch();

			this.el = this.template.clone();
			this.elItem = this.el.find(selectors.item);
			this.elItem.hide();

			$(selectors.root).replaceWith(this.el);

		},

		render : function() {

		},

		addOne : function(time) {
			var view = new TimeView({
				model : time
			});

			this.elItem.before(view.render().el);
		},

		addAll : function() {

		}

	});

	return {
		init : function() {
			return new TimeListView;
		}

	};

});
