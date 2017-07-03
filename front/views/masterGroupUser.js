define([
    "app",
    "models/groupUsers"
],function(app, groupUsers){
    var fnObj = {
        find: function(id) {
            console.log("SELECT ID : " + id );
            groupUsers.refresh(id);
        }
    };
	var ui = { 
        cols: [ 
            {     
                rows: [
                    { view:"toolbar", id:"tbSearch",
                        cols:[
                            {view:"richselect", name:"groupId", label:"그룹", labelAlign:"right", width:300,
                                    id: "selectGroup", options:"/api/group-options", 
                                on: {
                                    "onChange": function(newv, oldv) {
                                        fnObj.find(newv);
                                    }
                                }},
                            { view:"button", value:"검색", type:"base", width:80, align:"right", 
                                click: function() {
                                    var id = $$("selectGroup").getValue();
                                    fnObj.find(id); 
                                }
                            }
                        ]
                    },
                    { view:"datatable", id:"dtGroupUser", 
                        select:true,                
                        columns:[
                            {id:"groupId", header:"groupId", width:50, sort:"int", hidden:true},
                            {id:"userId", header:"userId", width:50, sort:"int", hidden:true},
                            {id:"userName", header:"사용자", sort:"string", fillspace:true},
                            {id:"position", header:"직책", width: 150, sort:"string"}
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
                                groupUsers.data.setCursor(cell);
                                this.$scope.show({id:cell.row});
                                groupUsers.rowState = '조회';
                            }
                        }
                    }
                ]
            },
            {view:"resizer" },
            { $subview:true }
        ]
	};

	return {
		$ui: ui,
		$oninit:function(view){
            $$("dtGroupUser").parse(groupUsers.data);
            app.on("repositoryUpdated", function(data){
                groupUsers.refresh(data.groupId);
            });
		},
	};
	
});
