/**
 * readjson.js
 */
 
    var responsetext,responsetext_w;
    $(document).ready(function(){
    	//加载页面重置BMP
    	ffobj=$.ajax({
    		url:"/SA_Online/ResetSingleSce",
    		async:false
    	});
    	
        fobj=$.ajax({
        	url:"/SA_Online/Readjson?content=do",
        	async:false
        });
          responsetext = fobj.responseText;
          var jsonobj = eval("(" + responsetext + ")");
          var fnum = jsonobj.fnum[0];
          var tnum = jsonobj.tnum[0];
        //加载情景集文件
          for(i = 0; i < fnum; i++){		
              $("#fileslist").append("<div class=\"fileslist_f\"><i class=\"icon-file\"></i>&nbsp;<button id=\"file_" + i + "\" onclick=\"gettable('" + jsonobj.files[i] + "')\" title=\"Click to display in table\" class=\"btn yellowbtn filebtn\">" + jsonobj.files[i] + "</button><a onclick=\"deletefile('" + jsonobj.files[i] + "')\" class=\"fileclose\" href=\"#\">x</a></div>");           
              $("#settings_2").append("<option>" + jsonobj.files[i] + "</option>");
              $("#settings_1").append("<li><a id=\"gettxtfiles_" + i + "\"" + " onclick=\"gettext('" + jsonobj.files[i] + "')\" href=\"#\">" + jsonobj.files[i] + "</a></li>");
          }
          var select2 = document.getElementsByName("select2");
        //加载情景文件
          for(i = 0; i < tnum; i++){		
        	  $("#textslist").append("<div class=\"fileslist_t\"><i class=\"icon-file-text\"></i>&nbsp;<button id=\"file_" + i + "\" onclick=\"readcontent('" + jsonobj.texts[i] + "')\" title=\"Click to display\" class=\"btn smallbtn yellowbtn filebtn\">" + jsonobj.texts[i] + "</button><a onclick=\"deletetext('" + jsonobj.texts[i] + "')\" class=\"fileclose\" href=\"#\">x</a></div>");
        	  $("#settings_2").append("<option>" + jsonobj.texts[i] + "</option>");
          }
        	
        //显示情景集拆分的单一情景              
        $("#displaytxt").click(function(){		
            $.ajax({
            	url:"/SA_Online/PutoutStr?content=displaytxt",
            	async:true,
            	success:function(result) {
            		$("#txtfile").html(result);
            	}
            });
          });
    
        /*reset_single（生成新的情景集文件）*/
             $("#reset_single").click(function(){
                $.ajax({
                	url:"/SA_Online/ResetBMP?type=single",
                	async:false,
                	success:function() {
                		alert("成功生成新的情景集文件，请配置情景");
                	}
                });
             });
             $("#reset_rules").click(function(){
                $.ajax({
                	url:"/SA_Online/ResetBMP?type=rule",
                	async:false,
                	success:function() {alert("成功生成新的情景集文件，请配置情景");}
                });
             });
             /*save_single*/
             $("#save_single").click(function(){
                 save_single = $.ajax({
                	 url:"/SA_Online/SaveBMP?save=dosave",
                	 async:false
                });
            	 alert("Save successed!" + "\n" + "You can check it in list of ScenarioSeted.");
             });
             $("#save_rules").click(function(){
                 save_rules = $.ajax({
                	 url:"/SA_Online/SaveBMP_rule?save=dosave",
                	 async:false
                });
            	 alert("Save successed!" + "\n" + "You can check it in list of ScenarioSeted.");
             });
             
          
    })
    /*获得情景集*/
    function gettable(name) {
        htmlobj=$.ajax({
        	url:"/SA_Online/ReadjsonContent?content=" + name,
        	async:false
        });
        $("#tablebody").html(htmlobj.responseText);
        TableEditable.init();
    }
    
    /*生成单一情景*/
    function gettext(name) {  
      fobj_w=$.ajax({
    	  url:"/SA_Online/PutoutStr?content=gettxt&textname=" + name,
    	  async:true,
    	  success:function() {alert("Success!");},
    	  error:function() {alert("Error!");}
      });
}
    /*删除文件*/
    function deletefile(fname){
    	var confirmdelete = confirm("确定删除 " + fname + " ？"); 
		if(confirmdelete == true) {
			$.ajax({
				url:"/SA_Online/DeleteFile?type=file&name=" + fname, 
				async:false
			});
			location.reload();
		}       
	};
	function deletetext(fname){
		//alert(fname);
    	var confirmdelete = confirm("确定删除 " + fname + " ？"); 
		if(confirmdelete == true) {
			$.ajax({
				url:"/SA_Online/DeleteFile?type=text&name=" + fname, 
				async:false
			});
			location.reload();
		}       
	};

    
    
    
    
    
    
    