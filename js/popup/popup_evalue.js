
        var container = document.getElementById('popup');
        var content = document.getElementById('popup-content');
        var blockdiv = document.getElementById('FIDlist');
        
        var map = new ol.Map({
          controls: ol.control.defaults().extend([
            new ol.control.ScaleLine({}),new ol.control.LayerSwitcher({tipLabel: "Layers"})
          ]),
          target: document.getElementById('map_evalue'),
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
                stroke: new ol.style.Stroke({
                  color: 'rgba(255,0,0,0.7)',
                  width: 1
                }),
                fill: new ol.style.Fill({
                  color: 'rgba(255,0,0,0.2)'
                }),
                })] 
        });
       
        var doHighlight = true;
        var doHover = false;
        var highlight;
        var onPointerMove = function(evt) {
          var pixel = map.getEventPixel(evt.originalEvent);
          var coord = evt.coordinate;
          var popupField;
          var popupText = '';
          var currentFeature;
          var currentFeatureKeys;
          map.forEachFeatureAtPixel(pixel, function(feature, layer) {
            currentFeature = feature;
          });
            if (currentFeature !== highlight) {
              if (highlight) {
                featureOverlay.removeFeature(highlight);
              }
              if (currentFeature) {
                featureOverlay.addFeature(currentFeature);
              }
              highlight = currentFeature;
            }
        };
        
          var onSingleClick = function(evt) {
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
              $(document).ready(function(){
            	  $("#bmp_1").click(function(){
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                              alert("当前地块选择的BMPs：" + "\n" + "ObjectID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:1(低效林改造)")                          
            	              htmlobj=$.ajax({url:"http://localhost:8090/webproject/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=1",async:false});
            		  }}
            	  $("#div1").html(htmlobj.responseText);
            	  });
            	  $("#bmp_2").click(function(){
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                        	  alert("当前地块选择的BMPs：" + "\n" + "ObjectID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:2(等高草灌带)")                          
            	              htmlobj=$.ajax({url:"http://localhost:8090/webproject/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=2",async:false});
            		  }}
            	  $("#div1").html(htmlobj.responseText);
            	  });
            	  $("#bmp_3").click(function(){
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                        	  alert("当前地块选择的BMPs：" + "\n" + "ObjectID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:3(草灌乔混交)")                          
            	              htmlobj=$.ajax({url:"http://localhost:8090/webproject/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=3",async:false});
            		  }}
            	  $("#div1").html(htmlobj.responseText);
            	  });
            	  $("#bmp_4").click(function(){
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                        	  alert("当前地块选择的BMPs：" + "\n" + "ObjectID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:4(小穴播草)")                          
            	              htmlobj=$.ajax({url:"http://localhost:8090/webproject/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=4",async:false});
            		  }}
            	  $("#div1").html(htmlobj.responseText);
            	  });
            	  $("#bmp_5").click(function(){
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                        	  alert("当前地块选择的BMPs：" + "\n" + "ObjectID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:5(茶果园坡改梯)")                          
            	              htmlobj=$.ajax({url:"http://localhost:8090/webproject/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=5",async:false});
            		  }}
            	  $("#div1").html(htmlobj.responseText);
            	  });
            	  $("#bmp_6").click(function(){
            		  for ( var i=0; i<currentFeatureKeys.length;i++) {
                          if(currentFeatureKeys[i] == 'ObjectID'){
                        	  alert("当前地块选择的BMPs：" + "\n" + "ObjectID:" + currentFeature.get(currentFeatureKeys[i]) + "\n"  + "BMP:6(封禁)")                          
            	              htmlobj=$.ajax({url:"http://localhost:8090/webproject/Myservlet?ObjectID=" + currentFeature.get(currentFeatureKeys[i]) + "&BMP=6",async:false});
            		  }}
            	  $("#div1").html(htmlobj.responseText);
            	  });
            	  
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
        
      
        
     /*Select*/   
     var select = null;
     var selectClick = new ol.interaction.Select({
       condition: ol.events.condition.click
     });
     var selectMouseMove = new ol.interaction.Select({
       condition: ol.events.condition.mouseMove
     });
     var selectElement = document.getElementById('type');
     var changeInteraction = function() {
       if (select !== null) {
         map.removeInteraction(select);
       }
       var value = selectElement.value;
       if (value == 'click') {
         select = selectClick;
       } else if (value == 'mousemove') {
         select = selectMouseMove;
       } else {
         select = null;
       }
       if (select !== null) {
         map.addInteraction(select);
       }
     };
     selectElement.onchange = changeInteraction;
     changeInteraction();
        

        
