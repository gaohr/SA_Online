var baseLayer = new ol.layer.Group({
	'title': 'Base maps',
	layers: [new ol.layer.Tile({title: 'OSM', source: new ol.source.OSM()}),
	         new ol.layer.Tile({title: 'BingMap',
	        	 source: new ol.source.BingMaps({key:'ZKnizKrcjUnzsVfZnLNY~io4C0_y1otivNQqHuseerg~AtP7sJ5Z9RrMDH_LVKcJMHVNkP7rThk1zj9vdkOWF9ABsesmNKxZsFLv1sCSJsPw', imagerySet: 'Aerial'})
			}),
	        ]});
var lyr_field = new ol.layer.Vector({
	source: new ol.source.GeoJSON({
		projection: 'EPSG:3857',
		url: '../JSON/fieldgeos.geojson',
	}),
    style: style_field,
    title: "field"
});
lyr_field.setVisible(true);
var layersList = [baseLayer,lyr_field];

var lyr_field_single = new ol.layer.Vector({
	source: new ol.source.GeoJSON({ 
		projection: 'EPSG:3857',
		url: '../JSON/fieldgeos.geojson'
	}),
    style: style_field_single,
    title: "field"
            });
lyr_field_single.setVisible(true);
var layersList_single = [baseLayer,lyr_field_single];


