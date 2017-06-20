define([
	"models/groups"
],
function(groups){
     var fnObj = {
        newForm: function() {
            $$("form:editor").setValues( {id:0, name:''});
        },
        save: function() {
            var group = $$("form:editor").getValues();
            var id = group.id;
            if (id && id > 0) {
                webix.ajax().headers({
                    "Content-type":"application/json"
                }).put(groups.url, JSON.stringify(group), {
                    success: function(text, data, XmlHttpRequest) {
                        webix.message({text:'저장되었습니다.', expire:1000});
                        groups.refresh();
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
                }).post(groups.url, JSON.stringify(group), {
                    success: function(text, data, XmlHttpRequest) {
                        webix.message({text:'추가되었습니다.', expire:1000});
                        groups.refresh();
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
            var id = $$("form:editor").getValues().id;
            if (id) {
                groups.data.remove(id);
                webix.ajax().del(groups.url + '/' + id, {}, {
                    success: function(text, data, XmlHttpRequest) {
                        webix.message({text:'삭제되었습니다.', expire:1000});
                        groups.refresh();
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
            { view:"toolbar", id:"agToolbar",
              cols:[
                { view:"button", value:"새로작성", type:"base", width:80, align:"left", click:fnObj.newForm },
                { view:"button", value:"저장", type:"form", width:80, align:"right", click: fnObj.save },
                { view:"button", value:"삭제", type:"danger", width:80, align:"right", click: fnObj.delete }
            ]},
            {view:"form", id:"form:editor", width:400, elements:[
                {view:"text", name:"id", label:"id", disabled:true},
                {view:"text", name:"name", label:"이름"}
            ]},
            {}
        ]
	};

   
	return {
		$ui:ui,
		$oninit:function(){
			$$("form:editor").bind(groups.data);
		}
	};

});