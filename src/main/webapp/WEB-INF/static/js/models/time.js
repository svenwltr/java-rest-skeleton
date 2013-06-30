define([ "jquery", "backbone", "text!html/time.tpl.html", "utils/templating" ],
		function($, Backbone, template, Templating) {

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

				events : {
					"click [data-action='edit']" : "edit"
				},

				initialize : function() {
					this.setElement(this.template.clone());

					this.listenTo(this.model, 'change', this.render);

				},

				render : function() {
					Templating.render(this.$el, this.model.toJSON());
					return this;

				},
				
				edit : function() {
					this.trigger("edit", this);
					
				}
				
			});

			var TimeListView = Backbone.View.extend({

				template : Templating.extract($(template), "timetable"),
				
				events : {
					"click [data-action='save']" : "save"
				},

				initialize : function() {
					this.listenTo(timeList, 'add', this.addOne);
					this.listenTo(timeList, 'all', this.render);

					this.setElement(this.template.clone());

					$("#timetable").replaceWith(this.$el);
					
					this.form = Templating.find(this.$el, "timetable-form");

					timeList.fetch();

				},

				render : function() {
					Templating.render(this.$el, {
						items : []
					});

				},

				addOne : function(time) {
					var view = new TimeView({
						model : time
					});
					
					this.listenTo(view, 'edit', this.edit);

					Templating.append(this.$el, "items", view.render().$el);

				},
				
				edit : function(time) {
					Templating.render(this.form, time.model.toJSON());
					
				},
				
				save : function(ooo) {
					console.log(ooo);
				}

			});

			return {
				init : function() {
					return new TimeListView;
				}

			};

		});
