$(document).ready(function(){
	 $("#searchf").click(function(){
		 var sfid = document.getElementById("sfid").value;
	     var sfarea = document.getElementById("sfarea").value;
	     var sflanduse = document.getElementById("sflanduse").value;
		 if(sfid == "" && sfarea == "" && sflanduse == "") {
 			alert("请输入查询条件！");
 		}
	 });
 })
        var map = new ol.Map({
          controls: ol.control.defaults().extend([
            new ol.control.ScaleLine({}),new ol.control.LayerSwitcher({tipLabel: "Layers"})
          ]),
          target: document.getElementById('map_f'),
          renderer: 'canvas',
          layers: layersList,
          view: new ol.View({
            extent: [12962340.487630, 2958323.358259, 12966199.896047, 2960925.883492], maxZoom: 28, minZoom: 1
          })
        });
        
        map.getView().fitExtent([12962340.487630, 2958323.358259, 12966199.896047, 2960925.883492], map.getSize());
        
        var featureOverlay = new ol.FeatureOverlay({
          map: map,
          style: [new ol.style.Style({
                stroke: new ol.style.Stroke({color: 'rgba(255,0,0,0.7)',width: 1}),
                fill: new ol.style.Fill({color: 'rgba(255,0,0,0.2)'}),
                })] 
        });

        var doHighlight = true;
        var doHover = false;
        var highlight;
        var onSingleClick = function(evt) {
            var pixel = map.getEventPixel(evt.originalEvent);
            var coord = evt.coordinate;
            var currentFeature;
            var currentFeatureKeys;
            map.forEachFeatureAtPixel(pixel, function(feature, layer) {
              currentFeature = feature;
              currentFeatureKeys = currentFeature.getKeys();
              layerPr = layer.getProperties();
              if(layerPr.title == "field") {
            	 var chbname = document.getElementsByName("selectfid");
            	 if(chbname[feature.get("ObjectID") -1].checked == false){
            		 chbname[feature.get("ObjectID") -1].checked = true;
                    	 var flayer = new ol.layer.Vector({
                       		source: gjsource,
                       		style:function() { 
                       	    	return function(sfeature, resolution) { 
                           	    	if(sfeature.get('ObjectID') == feature.get("ObjectID")){
                           	    		return sfstyle;
                           	    	} else {
                           	    		return unsfstyle;
                           	    	}
                               	}; 
                           }(),
                       	});
                    	currentlayer[feature.get("ObjectID")] = flayer;		//将layer存放在数组之中
                       	map.addLayer(currentlayer[feature.get("ObjectID")]);
            	 } else if(chbname[feature.get("ObjectID") -1].checked == true) {
            		 chbname[feature.get("ObjectID") -1].checked = false;
            		 map.removeLayer(currentlayer[feature.get("ObjectID")]);
            	 }
             	 
             }
          });
      };
      map.on('singleclick', function(evt) {
          onSingleClick(evt);
      });
          
     var gjsource = new ol.source.GeoJSON({url: '../JSON/fieldgeos.geojson'});
     var sfstyle = [new ol.style.Style({
         stroke: new ol.style.Stroke({color: 'rgba(235,235,235,1)',width: 1}),
         fill: new ol.style.Fill({color: 'rgba(200,120,120,1)'}),
         })]
 	 var unsfstyle = [new ol.style.Style({
          stroke: new ol.style.Stroke({color: 'rgba(0,0,0,0)',width: 1}),
          fill: new ol.style.Fill({color: 'rgba(0,0,0,0)'}),
          })]
     var unsfstyle_r = [new ol.style.Style({
         stroke: new ol.style.Stroke({color: 'rgba(100,150,100,1)',width: 1}),
         fill: new ol.style.Fill({color: 'rgba(255,255,255,1)'}),
         })]
     
     /*逐地块*/
	 var currentlayer = [];
     function checkboxselected(fid){  
    	 var checkboxname = document.getElementsByName("selectfid");
         if (checkboxname[fid - 1].checked){      //checkbox选中事件
        	 var flayer = new ol.layer.Vector({
           		source: gjsource,
           		style:function() { 
           	    	return function(feature, resolution) { 
               	    	if(feature.get('ObjectID') == fid){
               	    		return sfstyle;
               	    	} else {
               	    		return unsfstyle;
               	    	}
                   	}; 
               }(),
           	});
        	currentlayer[fid] = flayer;		//将layer存放在数组之中
           	map.addLayer(currentlayer[fid]);
         } else {		//checkbox取消选中事件
        	 var flayers = map.getLayers();
        	 map.removeLayer(currentlayer[fid]);
         }
     }  
     $("#resetselect").click(function() {
    	 for(i = 0; i < currentlayer.length; i++) {
    		 map.removeLayer(currentlayer[i]);
    	 }
		 var checkboxname = document.getElementsByName("selectfid");
    	 for(i = 0; i < checkboxname.length; i++) {
    		 checkboxname[i].checked = false;
    	 }
     });

     $("#criticalok_s").click(function(){
     	var sfids = new Array();
     	var sbmp;
   		var selectfids = document.getElementsByName("selectfid");
   		var selectbmp = document.getElementsByName("radios");
   		for (i = 0 ;i < selectfids.length; i++ ){		//或得选中的feature的FID
   		    if(selectfids[i].checked){					//判断复选框是否选中
   		        sfids.push(selectfids[i].value); 
   		    }
   		}
  		for (i = 0 ;i < selectbmp.length; i++ ){		//获得选中的单选框的value
  		    if(selectbmp[i].checked){					//判断复选框是否选中
  		        sbmp = selectbmp[i].value; 
  		    }
  		}
  		
   	    fidsobj=$.ajax({url:"/SA_Online/CriticalZone?save=undosave&fids=" + sfids + "&bmp=" + sbmp, async:false, error:function (data, status, e) {alert("服务器没响应！");}});
  		var responsetext_s = fidsobj.responseText;
  		$("#bmpstr").html(responsetext_s);
   	});

     /*按规则*/
     $("#searchf").click(function(){ 
     	var fidsr = new Array();
       	var lyr_field_1 = new ol.layer.Vector({
       		source: gjsource,
       		style:function() { 
       	    	return function(feature, resolution) { 
       	    		var selectradio = document.getElementsByName("sradio");
       	    		var selectid = document.getElementById("selectid").value;
           			var selectarea = document.getElementById("selectarea").value;
           	    	var sfid = document.getElementById("sfid").value;
           	    	var sfarea = document.getElementById("sfarea").value;
           	    	var sflanduse = document.getElementById("sflanduse").value;
           	    	if(selectradio[0].checked) {
          	    		switch(selectarea) {
          	    		case '0':
          	    			if(feature.get('ObjectID') == sfid || feature.get('Area') == sfarea || feature.get('landuse') == sflanduse){
                      	    	getfid(feature.get('ObjectID'));
          	    				return sfstyle;
          	    			} else {
          	    				return unsfstyle_r;
          	    			} break;
          	    		case '1':
          	    			if(feature.get('ObjectID') == sfid || feature.get('Area') > sfarea || feature.get('landuse') == sflanduse){
          	    				getfid(feature.get('ObjectID'));
          	    				return sfstyle;
          	    			} else {
          	    				return unsfstyle_r;
          	    			} break;
          	    		case '2':
          	    			if(feature.get('ObjectID') == sfid || feature.get('Area') < sfarea || feature.get('landuse') == sflanduse){
          	    				getfid(feature.get('ObjectID'));
          	    				return sfstyle;
          	    			} else {
          	    				return unsfstyle_r;
          	    			} break;
          	    		}
          	    	}
          	    	if(selectradio[1].checked) {
          	    		switch(selectarea) {
          	    		case '0':
          	    			if(feature.get('ObjectID') == sfid && feature.get('Area') == sfarea && feature.get('landuse') == sflanduse){
          	    				getfid(feature.get('ObjectID'));
          	    				return sfstyle;
          	    			} else {
          	    				return unsfstyle_r;
          	    			} break;
          	    		case '1':
          	    			if(feature.get('ObjectID') == sfid && feature.get('Area') > sfarea && feature.get('landuse') == sflanduse){
          	    				getfid(feature.get('ObjectID'));
          	    				return sfstyle;
          	    			} else {
          	    				return unsfstyle_r;
          	    			} break;
          	    		case '2':
          	    			if(feature.get('ObjectID') == sfid && feature.get('Area') < sfarea && feature.get('landuse') == sflanduse){
          	    				getfid(feature.get('ObjectID'));
          	    				return sfstyle;
          	    			} else {
          	    				return unsfstyle_r;
          	    			} break;
          	    		}
          	    	}
               	}; 
               }(),
       	});
       	map.addLayer(lyr_field_1);
       	$("#researchf").click(function(){		//清除layers、清空文本框
      		map.removeLayer(lyr_field_1);
      		document.getElementById("sfid").value = "";
  	    	document.getElementById("sfarea").value = "";
  	    	document.getElementById("sflanduse").value = "";
  	    	
      	});
       	
       	function getfid(fid) {		//将FID存进数组
       		fidsr.push(fid);
        }
       	$("#criticalok_r").click(function(){
        	var sbmp;
       		var selectfids = document.getElementsByName("selectfid");
       		var selectbmp = document.getElementsByName("radior");
      		for (i = 0 ;i < selectbmp.length; i++ ){		//获得选中的单选框的value
      		    if(selectbmp[i].checked){					//判断复选框是否选中
      		        sbmp = selectbmp[i].value; 
      		    }
      		}
       	    fidsrobj=$.ajax({url:"/SA_Online/CriticalZone?save=undosave&fids=" + fidsr + "&bmp=" + sbmp, async:false, error:function (data, status, e) {alert("奇了怪，服务器没响应！");}});
      		var responsetext_r = fidsrobj.responseText;
            $("#bmpstr").html(responsetext_r);

  	    	for (i = 0 ;i < fidsr.length; i++ ){		//清空sfbmps
  	    		fidsr[i] = null;
      		}
       	});
    })
    
    $("#save_f").click(function() {
   	    $.ajax({
   	    	url:"/SA_Online/CriticalZone?save=dosave&fids=null&bmp=null", 
   	    	async:true, 
   	    	success:function() {alert("Save successed!");}, 
   	    	error:function() {alert("服务器没响应！");}
   	    });    	
    })
    $("#cancel").click(function() {
       	var radioname = document.getElementsByName("radior");
    	for(i = 0; i < radioname.length; i++) {
    		radioname[i].checked = false;
    	}
    })
    
    $("#refresh_c").click(function() {
   	    $.ajax({
   	    	url:"/SA_Online/CriticalZone?save=reset&fids=null&bmp=null", 
   	    	async:true, 
   	    	success:function() {alert("Reset successed!");}, 
   	    	error:function() {alert("服务器没响应！");}
   	    });    	
    })
     
     	/*运行模型*/
     	$("#run_f").click(function () {
     		run_f = $.ajax({
     			url:"/SA_Online/RunCriticalSce?eve=do", 
     			async:true,
    			success:function(result) {
    	    		var ifrun = eval("(" + result + ")");
    	            var ifrundo = ifrun[0];
    	    		if(ifrundo == "true") {  
    	    			$("#runmask").css('display', 'block');
    	    		} else {
    	    			alert("服务器内部错误，无法运行模型！");
    	    		}
    			},
    			error:function() {
    				alert("错误！未连接到服务器，无法运行！");
    			}
    		});
     	});
     
     /*隐藏运行等待窗体*/
  	$("#hidemask").click(function() {
  		$("#runmask").css('display', 'none');
  	})
  
     
