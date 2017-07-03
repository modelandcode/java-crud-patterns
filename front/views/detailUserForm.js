define([
    "app",
	"models/users"
],
function(app, users){
     var fnObj = {
        newForm: function() {
            $$("formUser").setValues( {id:0, name:'', alias:'', email:''});
        },
        save: function() {
            var user = $$("formUser").getValues();
            user.contacts =[];
            $$("dtContact").data.each(function(obj){ 
                user.contacts.push({'contactType':obj.contactType, 
                                    'email':obj.email, 
                                    'phone':obj.phone}); 
            });
            console.log(user);
            var id = user.id;
            if (id && id > 0) {
                //console.log('update');
                webix.ajax().headers({
                    "Content-type":"application/json"
                }).put(users.url, JSON.stringify(user), {
                    success: function(text, data, XmlHttpRequest) {
                        webix.message({text:'저장되었습니다.', expire:1000});
                        app.trigger("repositoryUpdated", [user]);
                    },
                    error: function(text, data, XmlHttpRequest){
                        console.log(XmlHttpRequest.status);
                        webix.message({type:'error', text: '[ERROR]\n' + text});
                    }
                });
            } else {
                //console.log('create');
                webix.ajax().headers({
                    "Content-type":"application/json"
                }).post(users.url, JSON.stringify(user), {
                    success: function(text, data, XmlHttpRequest) {
                        webix.message({text:'추가되었습니다.', expire:1000});
                        app.trigger("repositoryUpdated", [user]);
                    },
                    error: function(text, data, XmlHttpRequest){
                        console.log(XmlHttpRequest.status);
                        webix.message({type:'error', text: '[ERROR]\n' + text});
                    }
                });
            }
            
        },
        delete: function() {
            console.log("delete!!");
            var id = $$("formUser").getValues().id;
            if (id) {
                users.data.remove(id);
                webix.ajax().del(users.url + '/' + id, {}, {
                    success: function(text, data, XmlHttpRequest) {
                        webix.message({text:'삭제되었습니다.', expire:1000});
                        app.trigger("repositoryUpdated", [user]);
                    },
                    error: function(text, data, XmlHttpRequest){
                        console.log(XmlHttpRequest.status);
                        webix.message({type:'error', text: '[ERROR]\n' + text});
                    }
                });
            }
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
            {view:"form", id:"formUser", width:500, elements:[
                {view:"text", name:"id", label:"id", disabled:true},
                {view:"text", name:"name", label:"이름"},
                {view:"text", name:"alias", label:"아이디"},
                {view:"text", name:"email", label:"이메일"}
            ]},
            { view:"toolbar", id:"toolDetail", paddingY:2, height:34,
                cols:[
                { view:"button", value:"새 항목", type:"base", width:80, align:"left", 
                    click:function() {
                        $$("dtContact").add({'contactType': '', 'email': '', 'phone': ''});
                    }
                }
            ]},
            { view:"datatable", id:"dtContact", name:"Contacts", 
                select:true,
                autoheight:true,
                editable:true,
                columns:[
                    {id:"contactType", editor:"select", options: ["개인", "직장"], header:"유형", fillspace:true},
                    {id:"email", editor:"text", header:"email", width: 200},
                    {id:"phone", editor:"text", header:"phone", width: 150},
                    {id:"delete", header:"&nbsp;", width:40, 
                        template:"<span  style='cursor:pointer;' class='webix_icon fa-trash-o'></span>"}
                ],
                onClick:{
                    "fa-trash-o":function(e,id,node){
                        console.log(id);
                        webix.confirm({
                            text:"이 항목을 제거하시겠습니까?", ok:"Yes", cancel:"Cancel",
                            callback:function(res){
                                if(res){
                                    var item = $$("dtContact").getItem(id);
                                    console.log(item);
                                    $$("dtContact").remove(id);
                                }
                            }
                        });
                    }
	            },
            },
            {}
        ]
	};

   
	return {
		$ui:ui,
		$oninit:function(){
			$$("formUser").bind(users.data);
		}
	};

});