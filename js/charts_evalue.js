
var dataview,fdataview;
	$(document).ready(function(){
		 fobj=$.ajax({
			 url:"/SA_Online/Evaluation?type=file&content=displayfile&id=0",
			 async:true, 
			 success:function(){
				 //alert("success");
				 fdataview = fobj.responseText;
				 if(currentfile != null) {
					 displayplot(currentfile);
				 }
				 $("#filelist").html(fdataview);
			 },
			 error:function() {
				 alert("Error");
			 }
		 });
	});
	
	 var gjsource = new ol.source.GeoJSON({url: '../JSON/fieldgeos.geojson'});
	 var masklyr = new ol.layer.Vector({
     	source: gjsource,
     	style:style_0,
     })
			var cost,decrease;
            function chart2(data, dataview, filename) {
                var pageviews = data;
                
                var x = new Array;
        		var y = new Array;
                for(i = 0; i < data.length; i++){
                	x[i] = data[i][0];
                	y[i] = data[i][1];
                }
                var xmax = Math.max.apply(null, x);
                var xmin = Math.min.apply(null, x);
                var ymax = Math.max.apply(null, y);
                var ymin = Math.min.apply(null, y);
                var plot = $.plot($("#chart_e"), [{
                            data: pageviews,
                            label: "减沙量（t）&费用（¥）"
                        }], 
                    {
                        series: {
                            points: {show: true},
                            shadowSize: 2
                        },
                        grid: {
                            hoverable: true,
                            clickable: true,
                            tickColor: "#9999ee",
                            borderWidth: 0
                        },
                        colors: ["#d12610", "#37b7f3", "#52e136"],
                        xaxis: {
                            ticks: 5,
                            tickDecimals: 0,
                            min:xmin,
                            max:xmax
                        },
                        yaxis: {
                            ticks: 10,
                            tickDecimals: 2,
                            min:ymin,
                            max:ymax
                        }
                    });
                function showTooltip(x, y, contents) {
                    $('<div id="tooltip">' + contents + '</div>').css({
                            position: 'absolute',
                            display: 'none',
                            top: y + 5,
                            left: x + 15,
                            border: '1px solid #333',
                            padding: '4px',
                            color: '#fff',
                            'border-radius': '3px',
                            'background-color': '#333',
                            opacity: 0.7
                        }).appendTo("body").fadeIn(200);
                }

                var previousPoint = null;
                $("#chart_e").bind("plothover", function (event, pos, item) {		//hover事件
                    $("#x").text(pos.x.toFixed(2));
                    $("#y").text(pos.y.toFixed(2));
                    if (item) {
                        if (previousPoint != item.dataIndex) {
                            previousPoint = item.dataIndex;
                            $("#tooltip").remove();
                            var x = item.datapoint[0].toFixed(2),
                                y = item.datapoint[1].toFixed(2);
                            showTooltip(item.pageX, item.pageY, item.series.label + "<br>" + x + " - " + y);
                        }
                    } else {
                        $("#tooltip").remove();
                        previousPoint = null;
                    }
                });
                
                $("#chart_e").bind("plotclick", function (event, pos, item) {		//click事件
                	$("#x").text(pos.x.toFixed(2));
                    $("#y").text(pos.y.toFixed(2));
                    var x = item.datapoint[0].toFixed(2);
                    var y = item.datapoint[1].toFixed(2);
                	cost = ((x - xmin) / (xmax - xmin)) * 100;
                	decrease = ((y - ymin) / (ymax - ymin)) *100;
                	
                	/*显示单一情景分布*/
                	var pointid = "p" + Math.floor(x);
                	var mapview, Omapview;
//                	alert(pointid);
                	pointobj=$.ajax({url:"/SA_Online/Evaluation?type=display&content=" + filename + "&id=" + pointid,async:false});
                	mapview = pointobj.responseText;
                	Omapview = eval("(" + mapview + ")");
                    	map.addLayer(masklyr);
                    	var lyr_single = new ol.layer.Vector({
                       		source: gjsource,
                       		style:function() { 
                       	    	return function(feature, resolution) {
                       	    		for(i = 0; i < Omapview.length; i++) {
                       	    			if(feature.get("ObjectID") == Omapview[i].ObjectID) {
                       	    				switch(Omapview[i].BMP) {
                       	    				case '1': return style_1; break;
                       	    				case '2': return style_2; break;
                       	    				case '3': return style_3; break;
                       	    				case '4': return style_4; break;
                       	    				case '5': return style_5; break;
                       	    				case '6': return style_6; break;
                       	    				}
                       	    			}
                       	    		}
                               	}; 
                            }(),
                       	})
                    	map.addLayer(lyr_single);
                    	
                    /*计算实施面积*/
                    var oids = new Array(Omapview.length);
                    for(num = 0; num < Omapview.length; num++) {
                    	if(Omapview[num].BMP != "0") {
                    		oids[num] = 1;
                    	} else {
                    		oids[num] = 0;
                    	}
                    }
                	
                	var objArea = eval('objectArea');
                	var BMPsArea = 0;
                	var totalArea = 0;
                	for(n = 0; n < objArea.Objects.length; n++) {
                		totalArea += objArea.Objects[n].area;
                	}
                	for(n = 0; n < objArea.Objects.length; n++) {
                		BMPsArea += objArea.Objects[n].area * oids[n];
                	}
                	var area = (BMPsArea / totalArea) * 100;
                	
                	/*radar*/
                	var radarChartData_e = { 
                		labels: ["实施面积比例", "节省费用", "减沙量"],
                		datasets: [
                			{
                				label: "dataset",
                				fillColor: "rgba(0,100,200,0.2)",
                				strokeColor: "rgba(0,100,220,0.5)",
                				pointColor: "rgba(100,0,0,1)",
                				pointStrokeColor: "#fff",
                				pointHighlightFill: "red",
                				data: [Math.round(area), 100 - Math.round(cost), Math.round(decrease)]
                			}
                		]
                	};
                	window.myRadar = new Chart(document.getElementById("canvas_e").getContext("2d")).Radar(radarChartData_e, {
        				responsive: true
        			});
        			if ($('#radar_e').size() != 0) {
        	            $('#site_statistics_loading_2').hide();
        	        }
        			$("#radarinfo").html("实施面积比例：<b>" + Math.round(area) + "</b> [" + BMPsArea + "（㎡）]<br>减沙量：<b>" + Math.round(decrease) + "</b> [" + y + "（t）]<br>节省费用：<b>" + (100 - Math.round(cost)) + "</b><br>实际花费：[" + x + "（￥）]");    
                });
            }

	//Basic Chart
    var currentfile;
    function displayplot(filename) { 
    	$.ajax({
    		url:"/SA_Online/Evaluation?type=points&id=0&content=" + filename,
    		async:true,
    		success:function(result) {
    			dataview = eval("(" + result + ")");
    	    	if ($('#chart_e').size() != 0) {
    	    		$('#site_statistics_loading_1').hide();
    	    	}
    	    	chart2(dataview.points, dataview, filename);
    	    	currentfile = filename;
    		}
    	});
    	chartsource=$.ajax({url:"/SA_Online/EvaluationSource?content=" + filename, async:false});
    	$("#datasource").html(chartsource.responseText);
    };
    $("#graphrefresh").click(function() {
        $("#chart_e").empty();
        setTimeout("displayplot(currentfile)",200);
    })
    $("#cleardiv").click(function() {
        $("#chart_e").empty();
    })
  
    
    
    
    
    
    
    
    
