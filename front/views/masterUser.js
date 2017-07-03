define([
	"models/users"
],function(users){
    //console.log(users.data);
	var ui = { 
        cols: [ 
            { view:"datatable", id:"dtUser", select:true, 
                columns:[
                    {id:"id", header:"#", width:40, sort:"int"},
                    {id:"name", header:"이름", width: 150, sort:"string"},
                    {id:"alias", header:"아이디", width: 150},
                    {id:"email", header:"이메일", fillspace:true}
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
                        users.data.setCursor(cell);
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
            users.refresh();
            $$("dtUser").parse(users.data);
		},
	};
	
});
