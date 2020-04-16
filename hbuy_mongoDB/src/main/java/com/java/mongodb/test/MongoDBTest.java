package com.java.mongodb.test;

import com.java.mongodb.util.MongoDBUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Auther: Administrator
 * @Date: 2020-03-24 09:56
 * @Description:
 */
public class MongoDBTest {
    //mongoDB连接对象
    private MongoCollection<Document> collection = MongoDBUtil.getCollection();

    //测试连接
    @Test
    public void  test(){
        System.out.println(collection);
    }
    //查询所有
    @Test
    public void  test01(){
        //查询所有
        FindIterable<Document> documents = collection.find();
        documents.iterator().forEachRemaining(temp-> System.out.println(temp));
    }
    //多条件查询(查询范围price小于180或者price大于249.8并且作者名字中存在a(不区分大小写)的书籍)条件外层有and
    @Test
    public void test02(){
        //创建查询条件bson格式
        String query = "{\n" +
                "    $and:[\n" +
                "        {\"author\":{$regex:/a/i}},\n" +
                "        {$or:[\n" +
                "             {\"price\":{$lt:180}},\n" +
                "             {\"price\":{$gt:249.8}}\n" +
                "        ]}\n" +
                "    ]\n" +
                "}";
        Document praDocument= Document.parse(query);
        //根据条件查询
        FindIterable<Document> documents = collection.find(praDocument);
        documents.iterator().forEachRemaining(temp-> System.out.println(temp));
    }
    //根据bname和author查询
    @Test
    public void test03(){
        //查询条件
        Document document = new Document();
       //document.append("bname","西游记");
        document.append("author","路遥");
        //根据条件查询
        FindIterable<Document> documents = collection.find(document);
        documents.iterator().forEachRemaining(temp-> System.out.println(temp));
    }
    //分页查询
    @Test
    public void test04(){
        FindIterable<Document> limit1 = collection.find().skip((1 - 1) * 4).limit(4);
        limit1.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //根据bname和autho分页
    @Test
    public void test05(){
        //查询条件
        Document document = new Document();
        document.append("bname","西游记");
        document.append("author","吴承恩");
        FindIterable<Document> limit1 = collection.find(document).skip((1 - 1) * 2).limit(2);
        limit1.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //多条件查询(查询范围price小于180或者price大于249.8并且作者名字中存在a(不区分大小写)的书籍)条件外层有and
    @Test
    public void test06(){
        //小于180
        Bson ltPrice = Filters.lt("price", 180);
        //大于249.8
        Bson gtPrice = Filters.gt("price", 249.8);
        Bson or = Filters.or(ltPrice, gtPrice);
        //模糊查询
        Pattern pattern = Pattern.compile("a",Pattern.CASE_INSENSITIVE);
        Bson regex = Filters.regex("author", pattern);
        //整合条件
        Bson and = Filters.and(or, regex);
        FindIterable<Document> documents = collection.find(and);
        documents.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //添加单个1
    @Test
    public void test07(){
        //添加文档对象
        Document document = Document.parse("{'bname':'金品梅','price':'9.9','count':'99'}");
        //添加
        collection.insertOne(document);
    }
    //添加单个2
    @Test
    public void test08(){
        //新建执行文档
        Document document = new Document();
        document.append("bname","天龙八部");
        document.append("author","金庸");
        document.append("price",199);
        //添加
        collection.insertOne(document);
    }
    //添加多个
    @Test
    public void test09(){
        //新建添加文档对象
        Document document = Document.parse("{'bname':'平凡的世界','author':'路遥','price':'9.9','count':'99'}");
        Document document1 = Document.parse("{'bname':'平凡的世界1','author':'路遥','price':'19.9','count':'199'}");
        Document document2 = Document.parse("{'bname':'平凡的世界2','author':'路遥','price':'29.9','count':'299'}");
        Document document3 = Document.parse("{'bname':'平凡的世界3','author':'路遥','price':'39.9','count':'399'}");
        //执行
        collection.insertMany(Arrays.asList(document,document1,document2,document3));
    }

    //往mongoDB中添加100000万条数据
    @Test
    public void test10(){
        //开始时间
        long startTime = System.currentTimeMillis();
        //新建执行文档
        for (int i = 0; i <100000 ; i++) {
            Document document = new Document();
            document.append("bname","天龙八部"+i);
            document.append("author","金庸");
            document.append("price",199.8);
            document.append("counts",100);
            document.append("type","文学类");
            document.append("createDate",new Date());
            document.append("sales",199);
            document.append("num",1800);
            //添加
            collection.insertOne(document);
        }
        //结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("共消耗"+(endTime-startTime)/1000+"秒");
    }
    //删除10000条
    @Test
    public void test11(){
        //开始时间
        long startTime = System.currentTimeMillis();
        //删除条件
        Document document = Document.parse("{'num':1800}");
        DeleteResult deleteResult = collection.deleteMany(document);
        //结束时间
        long endTime = System.currentTimeMillis();
        System.out.println(deleteResult.getDeletedCount());
        System.out.println("共消耗"+(endTime-startTime)/1000+"秒");
    }

    //Bson构建多条件删除
    @Test
    public void test12(){
        Bson bnameBson = Filters.in("bname", "天龙八部");
        Bson authorBson = Filters.in("author", "金庸");
        Bson and = Filters.and(bnameBson, authorBson);
        //DeleteResult deleteResult = collection.deleteOne(and);
        DeleteResult deleteResult = collection.deleteMany(and);
        System.out.println(deleteResult.getDeletedCount());
    }

    //修改
    @Test
    public void test13(){
        Bson author = Filters.in("author", "路遥");
        Document upd = Document.parse("{$set:{'count':200}}");
        UpdateResult updateResult = collection.updateMany(author, upd);
        System.out.println(updateResult.getMatchedCount());
    }
    //多条件修改
    @Test
    public void test14(){
        Bson in = Filters.in("bname", "天龙八部");
        Bson in1 = Filters.in("author", "金庸");
        Bson and = Filters.and(in, in1);
        Document upd = Document.parse("{$set:{'price':200}}");
        UpdateResult updateResult = collection.updateOne(and, upd);
        System.out.println(updateResult.getMatchedCount());
        System.out.println(updateResult.getModifiedCount());

    }
}
