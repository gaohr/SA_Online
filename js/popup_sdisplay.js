
        var map = new ol.Map({
          controls: ol.control.defaults().extend([
            new ol.control.ScaleLine({}),new ol.control.LayerSwitcher({tipLabel: "Layers"})
          ]),
          target: document.getElementById('map_sdisplay'),
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
        

        var gjsource = new ol.source.GeoJSON({url: '../JSON/fieldgeos.geojson'});
        
        var masklyr = new ol.layer.Vector({
        	source: gjsource,
        	style:style_0,
        })
        /*显示单一情景内容*/
        function readcontent(n) {  
            textcontent=$.ajax({url:"/SA_Online/SingleSceContent?content=readtxt&textname=" + n,async:false});
        	var responsetext_s = textcontent.responseText;
        	var singletext = eval("(" + responsetext_s + ")");
        	map.addLayer(masklyr);
        	var lyr_single = new ol.layer.Vector({
           		source: gjsource,
           		style:function() { 
           	    	return function(feature, resolution) { 
           	    		for(i = 0; i < singletext.Blocks.length; i++) {
           	    			if(feature.get("ObjectID") == singletext.Blocks[i].ObjectID) {
           	    				switch(singletext.Blocks[i].BMP) {
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
//        	$("#textcontent").html("FID:1-FID:88<br>");
//        	for(i = 0; i < singletext.Blocks.length; i++) {
//        		$("#textcontent").append(singletext.Blocks[i].BMP + " ");
//        	}
        	
    }

        
        
    	/************************************************** 
    	* DivWindow.js 
    	**************************************************/ 
    	var DivWindow= function(popup/*最外层div id*/,popup_drag/*拖动div id*/,popup_exit/*退出按钮id*/ ,exitButton/*触发服务器端退出按钮id*/,varwidth,varheight,zindex){ 
    	    this.popup =popup ;    //窗口名称 
    	    this.height =varheight ; //窗口高度，并没用来设置窗口高度宽度，用来定位在屏幕的位置 
    	    this.width =varwidth ;    //窗口宽度 
    		this.popup_exit=popup_exit; 
    		this.exitButton=exitButton; 
    		this.zindex=zindex; 
    	    this.init = function(){    //初始化窗口 
    	    this.popupShow(); 
    	    this.startDrag();    //设置拖动 
    	    this.setCommond();    //设置关闭 
    	    DivWindow.ArrayW.push(document.getElementById(this.popup));    //存储窗口到数组 
    	    };this.init(); 
    	}; 
    	//存储窗口到数组 
    	DivWindow.ArrayW = new Array(); 
    	//字符串连接类 
    	DivWindow.StringBuild = function(){ 
    	    this.arr = new Array(); 
    	    this.push = function(str){ 
    	        this.arr.push(str); 
    	    }; 
    	    this.toString = function(){ 
    	        return this.arr.join(""); 
    	    }; 
    	}; 
    	//拖动类 
    	DivWindow.Drag = function(o ,oRoot){ 
    	    var _self = this; 
    	    //拖动对象 
    	    this.obj = (typeof oRoot != "undefined") ?oRoot : o; 
    	    this.relLeft = 0;    //记录横坐标 
    	    this.relTop = 0;    //记录纵坐标 
    	    o.onselectstart = function(){ 
    	        return false; 
    	    }; 
    	    o.onmousedown = function(e){    //鼠标按下 
    	        e = _self.fixE(e); 
    	        _self.relLeft = e.clientX - _self.fixU(_self.obj.style.left); 
    	        _self.relTop = e.clientY - _self.fixU(_self.obj.style.top); 
    	        document.onmousemove = function(e){ 
    	            _self.drag(e);  
    	        }; 
    	        document.onmouseup = function(){ 
    	            _self.end(); 
    	        }; 
    	    }; 
    	    this.drag = function(e){    //拖动 
    	        e = this.fixE(e); 
    	        var l = e.clientX - this.relLeft; 
    	        var t = e.clientY - this.relTop; 
    	        if (t < 0) 
    	        { 
    	            t = 0;    //防止头部消失 
    	        } 
    	        this.obj.style.left = l +"px"; 
    	        this.obj.style.top = t +"px";     
    	    }; 
    	    this.end = function(){    //结束拖动 
    	        document.onmousemove = null; 
    	        document.onmouseup = null; 
    	    }; 
    	    this.fixE = function(e){    //修复事件 
    	        if (typeof e == "undefined") e = window.event; 
    	        return e; 
    	    }; 
    	    this.fixU = function(u){    //处理px单位 
    	        return parseInt(u.split("p")[0]); 
    	    }; 
    	}; 

    	//窗口拖动 
    	DivWindow.prototype.startDrag = function(){ 
    	    var obj = document.getElementById(this.popup); 
    	    new DivWindow.Drag(obj.childNodes[0] ,obj); 
    	}; 

    	//设定窗口优先级 
    	DivWindow.prototype.setTop = function(){ 
    	    document.getElementById(this.popup).onclick = 
    	    document.getElementById(this.popup).onmousedown = 
    	    function(){ 
    	        for(var i=0;i<DivWindow.ArrayW.length;i++) 
    	        { 
    	            DivWindow.ArrayW[i].style.zIndex = 1; 
    	        } 
    	        this.style.zIndex = 100; 
    	    };     
    	}; 
    	//显示 
    	DivWindow.prototype.popupShow=function() 
    	{ document.all.mask.style.display="block"; 
    	document.all.mask.style.width=window.screen.width +20; 
    	document.all.mask.style.height=window.screen.width +20; 
    	var element = document.getElementById(this.popup); 
    	element.style.position = "absolute"; 
    	element.style.visibility = "visible"; 
    	element.style.display = "block"; 
    	element.style.width=this.width; 
    	element.style.height='auto'; 
    	element.style.left = (window.screen.width - this.width)/2+"px"; 
    	//element.style.top =(window.screen.height-this.height-100)/2+"px"; 
    	element.style.top =20+"px"; 
    	element.style.zIndex=this.zindex; 
    	} 
    	//设置关闭 
    	DivWindow.prototype.setCommond = function(){ 
    	    var _self = this; 
    	    //根对象 
    	    var obj = document.getElementById(this.popup); 
    	    var exit = document.getElementById(this.popup_exit); 
    	    var triggServerEvent=document.getElementById(this.exitButton); 
    	    //设置关闭 
    	     exit.onclick = function(){ 
    	         obj.style.display = "none"; 
    	         obj.style.visibility = 'hidden'; 
    	document.all.mask.style.display='none'//关闭遮罩层 
    	triggServerEvent.click();//触发服务器端退出事件 
    	     }; 
    	}; 
    	
    	/*运行模型*/
     	$("#run_ss").click(function () {
     		var scenarioname = document.getElementById("settings_2").value;
//     		alert(scenarioname);
     		run_s = $.ajax({
     			url:"/SA_Online/RunScenarioSet?eve=do&name=" + scenarioname, 
     			async:true, 
     			success:function() {
     				sreturn = run_s.responseText;
     	     		var ifrun = eval("(" + sreturn + ")");
     	            var ifrundo = ifrun[0];
     	     		if(ifrundo == "true") {
     	     			$("#runmask").css('display', 'block');
     	     		} else {
     	     			alert("服务器内部错误，无法运行模型！");
     	     		}
     			},
     			error:function() {
     				alert("错误！未连接到服务器，无法运行！");
     			},
     		});
     	});
     
     /*隐藏运行等待窗体*/
     	$("#hidemask").click(function() {
     		$("#runmask").css('display', 'none');
     	})
     
     
    	
    	
    	