define([
    "app",
	"models/users"
],function(app, users){
    //console.log(users.data);
    var fnObj = {
        find: function(id) {
            var url = "/api/audits?target=USER&keyId=" + id;
            webix.ajax().headers({
                "Content-type":"application/json"
            }).get(url, {}, {
                success: function(text, data, XmlHttpRequest) {
                    webix.message({text:'조회되었습니다.', expire:1000});
                    //console.log(XmlHttpRequest);
                    if (XmlHttpRequest.status == 200) {
                        var audits = data.json();
                        console.log(audits);
                        $$("dtAudit").clearAll();
                        $$("dtAudit").parse(audits);
                    }
                    //$$("formUser").parse(user);
                    
                },
                error: function(text, data, XmlHttpRequest){
                    //console.log(XmlHttpRequest.status);
                    webix.message({type:'error', text: '[ERROR]\n' + text});
                }
            });
        
        }
    };

	var ui = { 
        cols: [ 
            { view:"datatable", id:"dtUser", select:true, 
                columns:[
                    {id:"id", header:"#", width:40, sort:"int"},
                    {id:"name", header:"이름", width: 100, sort:"string"},
                    {id:"alias", header:"아이디", width: 120},
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
                        fnObj.find(cell.row);
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
            app.on("repositoryUpdated", function(data){
                users.refresh();
            });
		},
	};
	
});
