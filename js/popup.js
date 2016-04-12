
        var container = document.getElementById('popup');
        var content = document.getElementById('popup-content');

        var closer = document.getElementById('popup-closer');
        closer.onclick = function() {
          container.style.display = 'none';
          closer.blur();
          return false;
        };
        
        var ok = document.getElementById('popup-ok');
        ok.onclick = function() {
            container.style.display = 'none';
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
          layers: layersList_single,
          view: new ol.View({
            extent: [12962340.487630, 2958323.358259, 12966199.896047, 2960925.883492], maxZoom: 16, minZoom: 1
          })
        });
        
        map.getView().fitExtent([12962340.487630, 2958323.358259, 12966199.896047, 2960925.883492], map.getSize());
        
        var NO_POPUP = 0
        var ALL_FIELDS = 1

        popupLayers = [1];

        var featureOverlay = new ol.FeatureOverlay({
          map: map,
          style: [new ol.style.Style({
                stroke: new ol.style.Stroke({color: '#a00',width: 1}),
                fill: new ol.style.Fill({color: 'rgba(255,0,0,0.2)'}),
                })] 
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
        var doHighlight = true;
        var doHover = false;
        var highlight;
        var onPointerMove = function(evt) {
          if (!doHover && !doHighlight){
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
            for ( var i=0; i<currentFeatureKeys.length;i++) {
                  if (currentFeatureKeys[i] != 'geometry') {
                      popupField = currentFeatureKeys[i]  + ': '+ currentFeature.get(currentFeatureKeys[i]);
                      popupText = popupText + popupField+'<br>';
                  }
            }
                  
          });

          if (doHighlight){
            if (currentFeature !== highlight) {
              if (highlight) {
                featureOverlay.removeFeature(highlight);
              }
              if (currentFeature) {
                featureOverlay.addFeature(currentFeature);
              }
              highlight = currentFeature;
            }
          }

          if (doHover){
            if (popupText) {
              overlayPopup.setPosition(coord);
              content.innerHTML = popupText;
              container.style.display = 'block';        
            } else {
              container.style.display = 'none';
              closer.blur();
            }
          }
        };

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
            for ( var i=0; i<currentFeatureKeys.length;i++) {
                  if (currentFeatureKeys[i] != 'geometry') {
                      popupField = '<table border="1" width="100%"><tr><th width="65%">' + currentFeatureKeys[i] + '</th><td>' + currentFeature.get(currentFeatureKeys[i]) + '</td></tr></table>';
                      popupText = popupText + popupField;
                  }
            }
          });
          
          var fcontent = document.getElementById('info-content');
          fcontent.innerHTML =  popupText;

          var BMPcontent = document.getElementById('BMP-content');
          var popupBMPs = document.getElementById("thepopup").value;

          if (popupText) {
              overlayPopup.setPosition(coord);
              BMPcontent.innerHTML = popupBMPs;
              container.style.display = 'block';
              //Select BMPs
              var currentFeatureKeys = currentFeature.getKeys();
                //使用jquery-ajax向服务器端传递参数，在服务器端生成json格式数据
            	  $("#bmp_1").click(function(){
            		  featureOverlay_1.addFeature(currentFeature);
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                              alert("当前地块选择的BMPs：" + "\n" + "FID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:1-低效林改造")                          
            	              htmlobj=$.ajax({url:"/SA_Online/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=1",async:false});
                              htmlobj=$.ajax({url:"/SA_Online/SaveBMP?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=1&save=undosave",async:false});
                          }}
            	  });
            	  $("#bmp_2").click(function(){
            		  featureOverlay_2.addFeature(currentFeature);
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                        	  alert("当前地块选择的BMPs：" + "\n" + "FID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:2-等高草灌带")                          
            	              htmlobj=$.ajax({url:"/SA_Online/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=2",async:false});
                        	  htmlobj=$.ajax({url:"/SA_Online/SaveBMP?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=2&save=undosave",async:false});
            		  }}
            	  });
            	  $("#bmp_3").click(function(){
            		  featureOverlay_3.addFeature(currentFeature);
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                        	  alert("当前地块选择的BMPs：" + "\n" + "FID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:3-草灌乔混交")                          
            	              htmlobj=$.ajax({url:"/SA_Online/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=3",async:false});
                        	  htmlobj=$.ajax({url:"/SA_Online/SaveBMP?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=3&save=undosave",async:false});
            		  }}
            	  });
            	  $("#bmp_4").click(function(){
            		  featureOverlay_4.addFeature(currentFeature);
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                        	  alert("当前地块选择的BMPs：" + "\n" + "FID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:4-小穴播草")                          
            	              htmlobj=$.ajax({url:"/SA_Online/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=4",async:false});
                        	  htmlobj=$.ajax({url:"/SA_Online/SaveBMP?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=4&save=undosave",async:false});
            		  }}
            	  });
            	  $("#bmp_5").click(function(){
            		  featureOverlay_5.addFeature(currentFeature);
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                        	  alert("当前地块选择的BMPs：" + "\n" + "FID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:5-茶果园坡改梯")                          
            	              htmlobj=$.ajax({url:"/SA_Online/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=5",async:false});
                        	  htmlobj=$.ajax({url:"/SA_Online/SaveBMP?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=5&save=undosave",async:false});
            		  }}
            	  });
            	  $("#bmp_6").click(function(){
            		  featureOverlay_6.addFeature(currentFeature);
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                        	  alert("当前地块选择的BMPs：" + "\n" + "FID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:6-封禁")                          
            	              htmlobj=$.ajax({url:"/SA_Online/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=6",async:false});
                        	  htmlobj=$.ajax({url:"/SA_Online/SaveBMP?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=6&save=undosave",async:false});
            		  }}
            	  });
          } else {
            container.style.display = 'none';
            closer.blur();
          }
        };
        
        map.on('pointermove', function(evt) {
          onPointerMove(evt);
        });
        map.on('singleclick', function(evt) {
            onSingleClick(evt);
          });
        
        /*Run the Model*/
        /*运行模型*/
    	$("#run_single").click(function () {
    		$.ajax({
    			url:"/SA_Online/RunSingleSce?eve=do", 
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
     

        
