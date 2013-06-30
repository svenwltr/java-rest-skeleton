define([ "jquery", "backbone", "text!html/time.tpl.html", "utils/templating" ],
		function($, Backbone, template, Templating) {

			return function() {

				var templates = {
					item : Templating.extract($(template), "item"),
					table : Templating.extract($(template), "timetable")
				};

				/**
				 * 
				 */
				var Time = Backbone.Model.extend({

					defaults : function() {
						return {
							name : null,
							date : Date.now(),
							duration : null
						};
					}

				});

				/**
				 * 
				 */
				var TimeList = Backbone.Collection.extend({
					model : Time,
					url : '/rest/time/'

				});

				var timeList = new TimeList;

				/**
				 * 
				 */
				var TimeView = Backbone.View.extend({
					events : {
						"click [data-action='edit']" : "edit",
						"click [data-action='remove']" : "remove"
					},

					initialize : function() {
						this.setElement(templates.item.clone());
						this.listenTo(this.model, 'change', this.render);

					},

					render : function() {
						Templating.render(this.$el, this.model.toJSON());

					},

					edit : function() {
						this.trigger("edit", this.model);

					},

					remove : function() {
						this.model.destroy();

					}

				});

				/**
				 * 
				 */
				var TimeFormView = Backbone.View.extend({

					events : {
						"click [data-action='submit']" : "submit",
						"blur input" : "change",
						"keypress input" : "change"
					},

					initialize : function() {
						this.model = null;
						this.values = {};

					},

					render : function() {
						Templating.render(this.$el, this.model.toJSON());

					},

					edit : function(model) {
						this.values = model.toJSON();
						this.model = model;
						this.render();

					},

					change : function(event) {
						var name = $(event.target).attr("name");
						var value = $(event.target).val();
						
						this.values[name] = value;
					},
					
					submit : function(event) {
						this.model.set(this.values);
						this.model.save();
						
					}

				});

				/**
				 * 
				 */
				var TimeListView = Backbone.View.extend({

					initialize : function() {
						this.listenTo(timeList, 'add', this.addOne);
						this.listenTo(timeList, 'all', this.render);

						this.setElement(templates.table.clone());
						this.form = new TimeFormView({
							el : Templating.find(this.$el, "timetable-form")
						});

						$("#timetable").replaceWith(this.$el);
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

						this.form.listenTo(view, 'edit', this.form.edit);
						view.render();
						Templating.append(this.$el, "items", view.$el);

					}

				});

				return new TimeListView;

			};

		});
