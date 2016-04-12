
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
            new ol.control.MousePosition({}),
            new ol.control.ZoomSlider({}),                                      
            new ol.control.ScaleLine({}),new ol.control.LayerSwitcher({tipLabel: "Layers"})
          ]),
          target: document.getElementById('map_vdisplay'),
          renderer: 'canvas',
          overlays: [overlayPopup],
          layers: layersList,
          view: new ol.View({
            extent: [12962340.487630, 2958323.358259, 12966199.896047, 2960925.883492], maxZoom: 28, minZoom: 1
          })
        });
        
        map.getView().fitExtent([12962340.487630, 2958323.358259, 12966199.896047, 2960925.883492], map.getSize());
        
        var NO_POPUP = 0
        var ALL_FIELDS = 1

        popupLayers = [1];

        var featureOverlay = new ol.FeatureOverlay({
          map: map,
          style: [new ol.style.Style({
                stroke: new ol.style.Stroke({
                  color: '#a00',
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
                      popupField = '<tr><td>' + currentFeatureKeys[i] + '</td><td>' + currentFeature.get(currentFeatureKeys[i]) + '</td></tr>';
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
        

        var select = null;
        var selectClick = new ol.interaction.Select({
          condition: ol.events.condition.click
        });
        var selectMouseMove = new ol.interaction.Select({
          condition: ol.events.condition.mouseMove
        });
        var changeInteraction = function() {
          if (select !== null) {
            map.removeInteraction(select);
          }
            select = selectClick;
          if (select !== null) {
            map.addInteraction(select);
          }
        };
        changeInteraction();
        

        
