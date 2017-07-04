define([
	"app"
],function(app){

	var header = {
		type:"header", template:app.config.name
	};

	var menu = {
		view:"menu", id:"top:menu", 
		width:180, layout:"y", select:true,
		template:"<span class='webix_icon fa-#icon#'></span> #value# ",
		data:[
			{ value:"Home", 				id:"start",			
				href:"#!/top/start", 		icon:"envelope-o" },
			{ value:"Master-Only(User)", 	id:"masterUser",	
				href:"#!/top/masterUser/userForm", 	icon:"briefcase" },
			{ value:"Master-Only(Group)", 	id:"masterGroup",	
				href:"#!/top/masterGroup/groupForm", 	icon:"briefcase" },
			{ value:"Master(GroupUser)", 	id:"masterGroupUser",	
				href:"#!/top/masterGroupUser/groupUserForm", 	icon:"briefcase" },
			{ value:"Master-Detail(User)", 	id:"masterDetailUser",	
				href:"#!/top/masterDetailUser/detailUserForm", 	icon:"briefcase" },
			{ value:"Master-Master(User)", 	id:"masterMasterUser",	
				href:"#!/top/masterMasterUser/masterUserForm", 	icon:"briefcase" }			
		]
	};

	var ui = {
		type:"line", cols:[
			{ type:"clean", css:"app-left-panel",
				padding:10, margin:20, borderless:true, rows: [ header, menu ]},
			{ rows:[ { height:10}, 
				{ type:"clean", css:"app-right-panel", padding:4, rows:[
					{ $subview:true } 
				]}
			]}
		]
	};

	return {
		$ui: ui,
		$menu: "top:menu"
	};
});
