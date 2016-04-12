/*
 * 仪表图
 */
var dataview;
	$(document).ready(function(){
		 fobj=$.ajax({url:"/SA_Online/ReadPol_Sed",async:false});
		 dataview = (fobj.responseText / 1000).toFixed(3);
		 
		 echart(dataview);//绘制仪表盘
		 //每30秒刷新一次数据
		 setInterval("echart(($.ajax({url:'/SA_Online/ReadPol_Sed',async:false}).responseText / 1000).toFixed(3))",30000);
	});
	/*Echarts*/
	function echart(data) {
		
    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('echart')); 
            
            option = {
	    tooltip : {
	        formatter: "{a} <br/>{b}（吨） : {c}"
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            saveAsImage : {show: true}
	        }
	    },
	    series : [
	        {
	            name:'总产沙量',
	            type:'gauge',
	            detail : {formatter:'{value}'},
	            data:[{value: data,name:'t'}],
	            radius:['90%', '90%'],
				axisLine: {
					lineStyle: {
						color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
						width: 20
					}
				},
				splitLine: {
					length:25,
				},
	            splitNumber:5,
	            min:0,
	            max:50
	        }
	    ]
	};
            // 为echarts对象加载数据 
            myChart.setOption(option); 
        }
    );
	}
	
	$("#moreinfo").click(function() {
		window.open('attach/Running.html', 'Result', 'height=550, width=900, top=50, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
	})	
	
	$("#refreshguage").click(function() {
		 fobj=$.ajax({url:"/SA_Online/ReadPol_Sed",async:false});
		 dataview = (fobj.responseText / 1000).toFixed(3);
		 echart(dataview);//绘制仪表盘
	})
	
	
	
	
	
	