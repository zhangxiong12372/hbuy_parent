<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>test01.ftl</title>
    </head>
    <body>
        <div align="center">
            <h1>test01.ftl测试页面</h1>
            <h2>1.普通属性的传值</h2>
            <h3>用户名：${userName}</h3>
            <h3>年纪：${age}</h3>
            <h3>价格：${price}</h3>
            <h3>创建时间：${createDate?string('yyyy/MM/dd HH:mm:ss')}</h3>
            <h3>字符：${char}</h3>
            <h3>布尔类型：${b1?string ("true","false")}</h3>
            <hr/>
            <h2>2.对象属性的传值</h2>
            <h3>id：${productDetail.id}</h3>
            <h3>标题：${productDetail.title}</h3>
            <h3>具体标题：${productDetail.subtitle}</h3>
            <h3>价格：${productDetail.price}</h3>
            <h3>颜色：${productDetail.color}</h3>
            <h3>链接路径${productDetail.href}</h3>
            <h3>封面图：${productDetail.avatorimg}</h3>
            <h3>库存量：${productDetail.num}</h3>
            <h3>创建时间：${productDetail.updatetime?string('yyyy/MM/dd HH:mm:ss')}</h3>
            <hr/>
            <h2>3.list集合的传值</h2>
            <#list strs as str>
               <h3>${str}</h3>
            </#list>
            <hr/>
            <h2>4.map属性的传值</h2>
            <h3>${map['userName']}</h3>
            <h3>${map['create']?string('yyyy/MM/dd HH:mm:ss')}</h3>
            <h3>${map['char']}</h3>
            <h3>${map['num']}</h3>
            <h3>${map['f1']}</h3>
        </div>


    </body>

</html>