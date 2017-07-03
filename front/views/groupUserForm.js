define([
    "app",
    "models/groupUsers"
],
function(app, groupUsers){
    var url = "/api/group-users";
    var fnObj = {
        newForm: function() {
            var id = $$("selectGroup").getValue() || 0;
            $$("formGroupUser").setValues( {groupId: id, userId: '', position: ''});
            groupUsers.rowState = '신규';
        },
        save: function() {
            var id = $$("selectGroup").getValue() || 0;
            console.log(groupUsers.rowState);
            var groupUser = $$("formGroupUser").getValues();
            if (groupUsers.rowState == '신규') {
                console.log('insert');
                webix.ajax().headers({
                    "Content-type":"application/json"
                }).post(url, JSON.stringify(groupUser), {
                    success: function(text, data, XmlHttpRequest) {
                        webix.message({text:'추가되었습니다.', expire:1000});
                        app.trigger("repositoryUpdated", [groupUser]);
                    },
                    error: function(text, data, XmlHttpRequest){
                        console.log(XmlHttpRequest.status);
                        webix.message({type:'error', text: '[ERROR]\n' + text});
                    }
                });
               
            } else {
                console.log('update');
                webix.ajax().headers({
                    "Content-type":"application/json"
                }).put(url, JSON.stringify(groupUser), {
                    success: function(text, data, XmlHttpRequest) {
                        webix.message({text:'저장되었습니다.', expire:1000});
                        app.trigger("repositoryUpdated", [groupUser]);
                    },
                    error: function(text, data, XmlHttpRequest){
                        console.log(XmlHttpRequest.status);
                        webix.message({type:'error', text: '[ERROR]\n' + text});
                    }
                });
            }
            
        },
        delete: function() {
            //console.log("delete!!");
            var id = $$("selectGroup").getValue() || 0;
            var groupUser = $$("formGroupUser").getValues();
            webix.ajax().headers({
                    "Content-type":"application/json"
            }).del(url, JSON.stringify(groupUser), {
                success: function(text, data, XmlHttpRequest) {
                    webix.message({text:'삭제되었습니다.', expire:1000});
                    app.trigger("repositoryUpdated", [groupUser]);
                },
                error: function(text, data, XmlHttpRequest){
                    //console.log(XmlHttpRequest.status);
                    webix.message({type:'error', text: '[ERROR]\n' + text});
                }
            });
        }
    };
 
	var ui = { 
        rows: [
            { view:"toolbar", id:"tbCrud",
              cols:[
                { view:"button", value:"새로작성", type:"base", width:80, align:"left", click:fnObj.newForm },
                { view:"button", value:"저장", type:"form", width:80, align:"right", click: fnObj.save },
                { view:"button", value:"삭제", type:"danger", width:80, align:"right", click: fnObj.delete }
            ]},
            {view:"form", id:"formGroupUser", width:400, 
                elements:[
                    {view:"text", name:"groupId", label:"groupId", disabled:true},
                    {view:"select", name:"userId", label:"사용자", options:"/api/user-options"},
                    {view:"text", name:"position", label:"직책"}
                ]
            },
            {}
        ]
	};

   
	return {
		$ui:ui,
		$oninit:function(){
			$$("formGroupUser").bind(groupUsers.data);
		}
	};

});