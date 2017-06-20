define([],function(){
	var apiUrl = {
		select: "/api/groups"
	};
	var collection = new webix.DataCollection({ 
		url: apiUrl.select
	});
	//console.log(collection);
	return {
		data: collection,
		url: apiUrl.select,
		apiUrl: apiUrl,
		refresh: function() {
			collection.clearAll();
			collection.load(apiUrl.select);
		}
	};
});