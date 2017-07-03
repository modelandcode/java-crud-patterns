define([
	"models/groups"
],function(groups){
	var ui = { 
        cols: [ 
            { view:"datatable", id:"dtGroup", select:true, 
                columns:[
                    {id:"id", header:"#", width:40, sort:"int"},
                    {id:"name", header:"이름", width: 150, sort:"string", fillspace:true}
                ],
                on:{
                    onBeforeLoad:function(){
                        this.showOverlay("Loading...");
                    },
                    onAfterLoad:function(){
                        if (!this.count())
                            this.showOverlay("Sorry, there is no data");
                        else
                            this.hideOverlay();
                    },
                    onAfterSelect:function(cell){
                        //console.log(cell);
                        groups.data.setCursor(cell);
                        this.$scope.show({id:cell.row});
                    }
                }
            },
            {view:"resizer" },
            { $subview:true }
        ]
	};

	return {
		$ui: ui,
		$oninit:function(view){
            groups.refresh();
            $$("dtGroup").parse(groups.data);
		},
	};
	
});
