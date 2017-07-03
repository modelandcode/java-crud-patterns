define([
	"models/users"
],
function(users){
     var fnObj = {
        newForm: function() {
            $$("formUser").setValues( {id:0, name:'', alias:'', email:''});
        },
        save: function() {
            var user = $$("formUser").getValues();
            var id = user.id;
            if (id && id > 0) {
                //console.log('update');
                //console.log(user);
                webix.ajax().headers({
                    "Content-type":"application/json"
                }).put(users.url, JSON.stringify(user), {
                    success: function(text, data, XmlHttpRequest) {
                        webix.message({text:'저장되었습니다.', expire:1000});
                        users.refresh();
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
                        users.refresh();
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
                        users.refresh();
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
            {view:"form", id:"formUser", width:400, elements:[
                {view:"text", name:"id", label:"id", disabled:true},
                {view:"text", name:"name", label:"이름"},
                {view:"text", name:"alias", label:"아이디"},
                {view:"text", name:"email", label:"이메일"}
            ]},
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