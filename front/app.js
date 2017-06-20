/*
	App configuration
*/

define([
	"libs/webix-mvc-core/core",
	"libs/webix-mvc-core/plugins/menu"
], function(
	core, menu
){

	//configuration
	var app = core.create({
		id:			"crud-patterns", //change this line!
		name:		"CRUD Patterns",
		version:	"0.1.0",
		debug:		true,
		start:		"/top/start"
	});

	app.use(menu);

	// This enables user-control (login)
	//app.use(user);


	return app;
});