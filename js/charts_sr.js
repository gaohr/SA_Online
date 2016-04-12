var Charts = function () {

    return {
        //main function to initiate the module

        init: function () {

            App.addResponsiveHandler(function () {
                 Charts.initPieCharts(); 
            });
            
        },

        initCharts: function () {

            if (!jQuery.plot) {
                return;
            }

            var data = [];
            var totalPoints = 250;

            // random data generator for plot charts

            function getRandomData() {
                if (data.length > 0) data = data.slice(1);
                // do a random walk
                while (data.length < totalPoints) {
                    var prev = data.length > 0 ? data[data.length - 1] : 50;
                    var y = prev + Math.random() * 10 - 5;
                    if (y < 0) y = 0;
                    if (y > 100) y = 100;
                    data.push(y);
                }
                // zip the generated y values with the x values
                var res = [];
                for (var i = 0; i < data.length; ++i) res.push([i, data[i]])
                return res;
            }

            //Basic Chart

            function chart1() {
               
                var pageviews = [
                    [105000, 28],
                    [405000, 58]
                ];
               
                var plot = $.plot($("#chart"), [{
                            data: pageviews,
                            label: "减沙量&费用"
                        }
                    ], {
                        series: {
                        	points: {show: true},
                            lines: {show: true},
                            shadowSize: 2
                        },
                        grid: {
                            hoverable: true,
                            clickable: true,
                            tickColor: "#88dd88",
                            borderWidth: 0
                        },
                        colors: ["#d12610", "#37b7f3", "#52e136"],
                        xaxis: {
                            ticks: 3,
                            tickDecimals: 0,
                            min:0,
                            max:1000000
                        },
                        yaxis: {
                            ticks: 10,
                            tickDecimals: 2,
                            min:0,
                            max:100
                        },
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
                            opacity: 0.80
                        }).appendTo("body").fadeIn(200);
                }

                var previousPoint = null;
                $("#chart").bind("plothover", function (event, pos, item) {
                    $("#x").text(pos.x.toFixed(2));
                    $("#y").text(pos.y.toFixed(2));

                    if (item) {
                        if (previousPoint != item.dataIndex) {
                            previousPoint = item.dataIndex;

                            $("#tooltip").remove();
                            var x = item.datapoint[0].toFixed(2),
                                y = item.datapoint[1].toFixed(2);

                            showTooltip(item.pageX, item.pageY, item.series.label + " of " + x + " = " + y);
                        }
                    } else {
                        $("#tooltip").remove();
                        previousPoint = null;
                    }
                });
                $("#chart").bind("plotclick", function (event, pos, item) {
//                	$("#x").text(pos.x.toFixed(2));
//                    $("#y").text(pos.y.toFixed(2));
//                    var x = item.datapoint[0].toFixed(2);
//                    var y = item.datapoint[1].toFixed(2);
//                	alert("You clicked at " + x + ", " + y);
                });
            }
            if ($('#chart').size() != 0) {
                $('#site_statistics_loading_1').hide();
            }
            //graph
            chart1();
        },
        
       
    };

}();

/*radar*/
var radarChartData = {
		labels: ["实施面积", "费用", "减沙量"],
		datasets: [
			{
				label: "dataset",
				fillColor: "rgba(0,100,200,0.2)",
				strokeColor: "rgba(0,100,220,0.5)",
				pointColor: "rgba(100,0,0,1)",
				pointStrokeColor: "#fff",
				pointHighlightFill: "red",
				data: [165,79,42]
			}
		]
	};

	window.onload = function(){
		window.myRadar = new Chart(document.getElementById("canvas").getContext("2d")).Radar(radarChartData, {
			responsive: true
		});
		if ($('#radar').size() != 0) {
            $('#site_statistics_loading_2').hide();
        }
	}
	
	
	