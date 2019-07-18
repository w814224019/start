<html>
<head>
<title>hello</title>
</head>
<boby>
<h1>hello${user.company}</h1>
<table border="1" id="table-result" width="80%" align="center">
             
               <tr>
               <th>出发地</th>
               <th>公司名称</th>
               <th>到达地</th>
               <th>电话</th>
               </tr>
               <tbody id="tbody-result"> <tr>
               <th>${user.cityFrom}</th>
               <th>${user.company}</th>
               <th>${user.cityTo}</th>
               <th>${user.phone}</th>
               </tr>
               </tbody>
           
               </table>

<span><a href="http://localhost:8080/web/view-source.html">首页</a></span>
</body>
</html>