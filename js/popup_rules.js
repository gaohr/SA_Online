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
 		var container = document.getElementById('popup');
        var content = document.getElementById('popup-content');

        var closer = document.getElementById('popup-closer');
        closer.onclick = function() {
          container.style.display = 'none';
          closer.blur();
          return false;
        };
        
        var overlayPopup = new ol.Overlay({
          element: container
        });
        
      var map = new ol.Map({
    	       controls: ol.control.defaults().extend([
    	           new ol.control.ScaleLine({}),new ol.control.LayerSwitcher({tipLabel: "Layers"})
    	       ]),
    	       target: document.getElementById('map'),
    	       renderer: 'canvas',
    	       overlays: [overlayPopup],
    	       layers: [baseLayer],
    	       view: new ol.View({ maxZoom: 28, minZoom: 1 })
    	     });
    	     
    	     map.getView().fitExtent([12962340.487630, 2958323.358259, 12966199.896047, 2960925.883492], map.getSize());
    	     
    	     var style_field = [ new ol.style.Style({
	             stroke: new ol.style.Stroke({color: "rgba(100,150,100,1)", lineDash: null, width: 0}), 
	             fill: new ol.style.Fill({color: "rgba(255,255,255,0.8)"})
	         	})];
    	var gjsource = new ol.source.GeoJSON({url: '../JSON/fieldgeos.geojson'});
    	var lyr_field = new ol.layer.Vector({
    		source: gjsource,
    		style:style_field,
    	    title: "field"
    	 });
    	map.addLayer(lyr_field);
    	
    	var sfstyle = [new ol.style.Style({
            stroke: new ol.style.Stroke({color: 'rgba(235,235,235,1)',width: 1}),
            fill: new ol.style.Fill({color: 'rgba(100,150,200,1)'}),
            })]
    	 var unsfstyle = [new ol.style.Style({
             stroke: new ol.style.Stroke({color: 'rgba(100,150,100,1)',width: 1}),
             fill: new ol.style.Fill({color: 'rgba(255,255,255,1)'}),
             })]
    	var doHighlight = false;
        var doHover = false;
    	var onSingleClick = function(evt) {
            if (doHover){
              return;
            }
            var pixel = map.getEventPixel(evt.originalEvent);
            var coord = evt.coordinate;
            var popupField;
            var popupText = '';
            var currentFeature;
            var currentFeatureKeys;
            map.forEachFeatureAtPixel(pixel, function(feature, layer) {
              currentFeature = feature;
              currentFeatureKeys = currentFeature.getKeys();
              layerPr = layer.getProperties();
              if(layerPr.title == 'field') {
                for ( var i=0; i<currentFeatureKeys.length;i++) {
                    if (currentFeatureKeys[i] != 'geometry') {
                        popupField = currentFeatureKeys[i] + ': '+ currentFeature.get(currentFeatureKeys[i]);
                        popupText = popupText + popupField+'<br>';
                    }
                }
              }
            });

            if (popupText) {
                overlayPopup.setPosition(coord);
                content.innerHTML = popupText;
                container.style.display = 'block';        
            } else {
              container.style.display = 'none';
              closer.blur();
            }
          };

          map.on('singleclick', function(evt) {
            onSingleClick(evt);
          });
          
          var featureOverlay_1 = new ol.FeatureOverlay({
              map: map,
              style: [new ol.style.Style({stroke: new ol.style.Stroke({color: 'white',width: 1}),
                    fill: new ol.style.Fill({color: 'rgba(255,0,0,0.5)'}),
                    })] 
            });
          var featureOverlay_2 = new ol.FeatureOverlay({
              map: map,
              style: [new ol.style.Style({stroke: new ol.style.Stroke({color: 'white',width: 1}),
                    fill: new ol.style.Fill({color: 'rgba(255,165,0,0.5)'}),
                    })] 
            });
          var featureOverlay_3 = new ol.FeatureOverlay({
              map: map,
              style: [new ol.style.Style({stroke: new ol.style.Stroke({color: 'white',width: 1}),
                    fill: new ol.style.Fill({color: 'rgba(255,255,0,0.5)'}),
                    })] 
            });
          var featureOverlay_4 = new ol.FeatureOverlay({
              map: map,
              style: [new ol.style.Style({stroke: new ol.style.Stroke({color: 'white',width: 1}),
                    fill: new ol.style.Fill({color: 'rgba(60,179,113,0.5)'}),
                    })] 
            });
          var featureOverlay_5 = new ol.FeatureOverlay({
              map: map,
              style: [new ol.style.Style({stroke: new ol.style.Stroke({color: 'white',width: 1}),
                    fill: new ol.style.Fill({color: 'rgba(0,255,255,0.5)'}),
                    })] 
            });
          var featureOverlay_6 = new ol.FeatureOverlay({
              map: map,
              style: [new ol.style.Style({stroke: new ol.style.Stroke({color: 'white',width: 1}),
                    fill: new ol.style.Fill({color: 'rgba(65,105,225,0.5)'}),
                    })] 
            });
          var sffids = new Array();
          var sfbmps = new Array();
          $("#searchf").click(function(){
              var fids = new Array(); 
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
              	    				return unsfstyle;
              	    			} break;
              	    		case '1':
              	    			if(feature.get('ObjectID') == sfid || feature.get('Area') > sfarea || feature.get('landuse') == sflanduse){
              	    				getfid(feature.get('ObjectID'));
              	    				return sfstyle;
              	    			} else {
              	    				return unsfstyle;
              	    			} break;
              	    		case '2':
              	    			if(feature.get('ObjectID') == sfid || feature.get('Area') < sfarea || feature.get('landuse') == sflanduse){
              	    				getfid(feature.get('ObjectID'));
              	    				return sfstyle;
              	    			} else {
              	    				return unsfstyle;
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
              	    				return unsfstyle;
              	    			} break;
              	    		case '1':
              	    			if(feature.get('ObjectID') == sfid && feature.get('Area') > sfarea && feature.get('landuse') == sflanduse){
              	    				getfid(feature.get('ObjectID'));
              	    				return sfstyle;
              	    			} else {
              	    				return unsfstyle;
              	    			} break;
              	    		case '2':
              	    			if(feature.get('ObjectID') == sfid && feature.get('Area') < sfarea && feature.get('landuse') == sflanduse){
              	    				getfid(feature.get('ObjectID'));
              	    				return sfstyle;
              	    			} else {
              	    				return unsfstyle;
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
          		fids.push(fid);
          		sffids = fids;
          	}
          
          })
          
          	$("#ruleok").click(function(){
          		var selectchb = document.getElementsByName("check");
          		for (i = 0 ;i < selectchb.length; i++ ){
          		    if(selectchb[i].checked){		//判断复选框是否选中
          		        sfbmps.push(selectchb[i].value); 
          		    }
          		}
          	    fidsobj=$.ajax({url:"/SA_Online/RulesBased?fids=" + sffids + "&bmps=" + sfbmps, async:false, error:function (data, status, e) {alert("奇怪，服务器没响应！");}});
                save_rules = $.ajax({url:"/SA_Online/SaveBMP_rule?fids=" + sffids + "&bmps=" + sfbmps + "&save=undosave",async:false});
          		responsetext = fidsobj.responseText;
                alert("当前地块集配置的BMPs为：\n" + responsetext);
                
                for (i = 0 ;i < fids.length; i++ ){		  //清空fids
                	fids[i] = null;
          		}
          	});
          	
          /*Run the Model*/
          /*运行模型*/
      	$("#run_rules").click(function () {
      		$.ajax({
    			url:"/SA_Online/RunSingleSce?eve=do", 
    			async:false,
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
       

             