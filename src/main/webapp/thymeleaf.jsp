<%--
  Created by IntelliJ IDEA.
  User: 13905
  Date: 2018/8/2
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title>Title</title>
    <%--导入jQuery库--%>
    <script src="../bootstrap3/jquery-1.9.1.min.js"></script>
    


<!--导入百度地图js库-->
    <style type="text/css">
        html{height:100%}
        body{height:100%;margin:0px;padding:0px}
        #container{height:100%}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=MuT8ux6ZhvmiiGTjrGNWCHRlnjiwd4qe">
        //v3.0版本的引用方式：src="http://api.map.baidu.com/api?v=3.0&ak=您的密钥"
    </script>
</head>


<body>

<%--用ajax从后台contorller拿到数据--%>
<script type="text/javascript">
    // 写ajax请求，调用TestController中的方法
        function  getCurrentPosition() {
           var position = [];
            console.log("执行了函数");
            jQuery.ajax({
                type:'GET',
                content:'application/json',
                url:'/currentMapFuck',
                dataType:'json',
                success :function (modelMap) {
                    console.log("成功");
                    console.log(modelMap.data.lon);
                    console.log(modelMap.data.lat);
                    position.push(modelMap.data.lon);
                    position.push( modelMap.data.lat);

                },
                error :function () {
                    alert("error");
                    console.log("失败")
                }
            });
                      return position;
        }
</script>


<p>
这是展示实时位置的地图
</p>


<p>
    刷新方式：
    <select class="refreshChoice" name="refreshChoice"> <!--别把class写成calss了ヽ(●-`Д´-)ノ-->
        <option  selected="selected" id="manual" value="手动刷新"  >手动刷新</option> <!--设置手动刷新为默认选项，但是在火狐上好像有bug-->
        <option id="auto" value="自动刷新" >自动刷新</option>
    </select>

<div id="manualDiv" class="manualDiv">
    <%--手动刷新就显示这个div--%>
    <button id="getCurrentPosition" onclick="changeMarkerPosition()">刷新</button>

</div>

</p>





<script>
    var timer;
    //利用jq监测下拉菜单的状态
    $(document).ready(function(){
        $(".refreshChoice").change(function(){
           // alert($(this).find("option:checked").attr("id")); 通过此方法能得到用户选择的option的id值，从而判断用户选择了哪个选项。

            if ($(this).find("option:checked").attr("id")=="manual"){


                $(document).ready(function(){

                   $("#manualDiv").show();//如果选择了手动,就把面板显示出来
                    clearInterval(timer);

                });



            }else if($(this).find("option:checked").attr("id")=="auto"){
                $("#manualDiv").hide();//如果选择了自动，就把面板隐藏。

              timer = setInterval(changeMarkerPosition,5000);
                //每隔3秒刷新一次点的坐标

            }

        });
    });
</script>



<%--div的名字只能是container，否则就显示不出来，别问我问什么知道--%>
<div id="container">
</div>

<script type="text/javascript">
    var map = new BMap.Map("container");//地图的实例

    var myPosition = [];
    myPosition = getCurrentPosition();

    //要把点和marker设置成全局变量试一试
    var point;
    var marker;

    var  carIcon = new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/car.png", new BMap.Size(300,157));//创建一个小车的图标

    //让程序停止200毫秒之后在做function中的事情
    setTimeout(function () {
        // 创建地图实例
         point = new BMap.Point(myPosition[0], myPosition[1]);//这里已经能得到，就不打印了

        // 初始化地图
        map.centerAndZoom(point, 13);

        //添加一个marker
         marker = new BMap.Marker(point,{icon:carIcon});
        map.addOverlay(marker);

    },500);


    function changeMarkerPosition () {
        myPosition = getCurrentPosition();
       setTimeout(function () {
           point = new BMap.Point(myPosition[0], myPosition[1]);//的饿到了最新的坐标点
           console.log("地图中心："+myPosition[0]+"  "+myPosition[1]);
           marker = new BMap.Marker(point,{icon:carIcon});
           //map.centerAndZoom(point, 14);
           map.clearOverlays();//在地图上清除掉所有的覆盖物。
           map.addOverlay(marker);
           marker.setAnimation(BMAP_ANIMATION_BOUNCE);//给marker设置动画
           console.log("maker的位置"+marker.getPosition().lng+"  "+marker.getPosition().lat);
       },500);//此暂停放噶目前不得已而位置，带我日后掌握回调函数再战

    }

</script>

</body>
</html>
