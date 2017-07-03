define([],function(){
	var apiUrl = {
		select: "/api/group-users/0"
	};

	var collection = new webix.DataCollection({ 
		url: apiUrl.select
	});
	//console.log(collection);
	return {
		data: collection,
		url: apiUrl.select,
		apiUrl: apiUrl,
		refresh: function(id) {
			if (id)
				apiUrl.select = "/api/group-users/" + id;
			collection.clearAll();
			//console.log(apiUrl.select);
			collection.load(apiUrl.select);
		},
		rowState: '조회'
	};
});