//###################################################################################
// Create Start/Stop Editing Checkboxe controls
//###################################################################################

        function editorChange(obj) {
        	var dbtnObj = document.getElementsByName("disablebutton");
            if (obj.checked == false) {
                for (var i = 0; i < dbtnObj.length; i++)
                    dbtnObj[i].disabled = true;
            } else {
                for (var i = 0; i < dbtnObj.length; i++)
                    dbtnObj[i].disabled = false;
            }
        }
//###################################################################################
// Display BMPs Name or info
//###################################################################################

        function displayBMPname(obj, Type) {
        	  var objectDiv = document.getElementById(obj);
        	  if (Type == 'show') { objectDiv.style.display = 'block';}
        	  if (Type == 'hide') { objectDiv.style.display = 'none';}
        	 }  
        
        function displayBMPinfo(obj) {
      	  var objectDiv = document.getElementById(obj);
      	  var preobjectDiv = document.getElementById('preinfo');
      	  var aaDiv = document.getElementById('aainfo');
      	  var bbDiv = document.getElementById('bbinfo');
      	  var ccDiv = document.getElementById('ccinfo');
      	  var ddDiv = document.getElementById('ddinfo');
      	  var eeDiv = document.getElementById('eeinfo');
      	  var ffDiv = document.getElementById('ffinfo');
      	  aaDiv.style.display = 'none';
      	  bbDiv.style.display = 'none';
      	  ccDiv.style.display = 'none';
      	  ddDiv.style.display = 'none';
      	  eeDiv.style.display = 'none';
      	  ffDiv.style.display = 'none';
      	  preobjectDiv.style.display = 'none';
      	  objectDiv.style.display = 'block';
      	 }
        
        function displayBMPinfo2(obj) {
        	  var objectDiv = document.getElementById(obj);
        	  var preobjectDiv = document.getElementById('pretext');
        	  var aaDiv = document.getElementById('dxlgz');
          	  var bbDiv = document.getElementById('dgcgd');
          	  var ccDiv = document.getElementById('cgqhj');
          	  var ddDiv = document.getElementById('xxbc');
          	  var eeDiv = document.getElementById('fj');
          	  aaDiv.style.display = 'none';
          	  bbDiv.style.display = 'none';
          	  ccDiv.style.display = 'none';
          	  ddDiv.style.display = 'none';
          	  eeDiv.style.display = 'none';
          	  preobjectDiv.style.display = 'none';
          	  objectDiv.style.display = 'block';
        	 }  
        
        function displayBMPinfo1(obj) {
      	  var objectDiv = document.getElementById(obj);
      	  var preobjectDiv = document.getElementById('pretext');
      	  var aaDiv = document.getElementById('chgypgt');
      	  preobjectDiv.style.display = 'none';
      	  objectDiv.style.display = 'block';
      	 }  
        

//###################################################################################
// Display div of field
//###################################################################################
        
        function fieldDisplay(obj) {
      	  var fieldDiv = document.getElementById(obj);
      	  fieldDiv.style.display = 'block';
      	 }  
        
        function aclose(obj) {
        	  var fieldDiv = document.getElementById(obj);
        	  fieldDiv.style.display = 'none';
        	 }  
        
        var selectfield = "<select class=\"span4\"><option value=\"1\">FID</option><option value=\"2\">LandUse</option><option value=\"3\">Ele</option><option value=\"4\">Slp</option><option value=\"5\">Area</option></select>";
        var fielddiv = "<div class=\"inquiryInfo\"><input type=\"checkbox\" checked=\"checked\"/>&nbsp;"+selectfield+"&nbsp;<input class=\"span4\" type=\"text\"/></div>";
        var fielddiv_g = "<div class=\"inquiryInfo\" style=\"margin-left:-20px;margin-right:-20px\"><input type=\"checkbox\" checked=\"checked\"/>&nbsp;"+selectfield+"&nbsp;<input class=\"span4\" placeholder=\"查询条件...\" type=\"text\"/></div>";
        $(document).ready(function(){
        	$("#addfield").click(function(){
        		$("#fielddivs").append(fielddiv);
        		var objdivs = document.getElementById("fielddivs").getElementsByTagName("div");
        		var objs = document.getElementById("fielddivs").getElementsByTagName("select");
        		var objsinput = document.getElementById("fielddivs").getElementsByTagName("input");
        		for(var i = 0; i < objs.length; i++) {
        			objs[i].id = "field_" + i;
        			objdivs[i].id = "fielddiv_" + i;
        			objsinput[i].id = "input_" + i;
        		}
        	});
        	$("#addfield_g").click(function(){
        		$("#fielddivs").append(fielddiv_g);
        		var objs=document.getElementById("fielddivs").getElementsByTagName("div");
        		for(var i=0;i<objs.length;i++) {
        			objs[i].id="field_" + i;
        		}
        	});
        });
        $(document).ready(function(){
        	$("#removefield").click(function(){
        		$("#fielddivs").empty();
        	});
        });
        

        
        
        