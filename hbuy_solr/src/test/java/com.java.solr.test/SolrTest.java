package com.java.solr.test;

import com.java.solr.model.Student;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020-04-13 09:04
 * @Description:
 */
public class SolrTest {
    //定义solr的链接对象
    private HttpSolrClient solr = null;

    @Before
    public void before(){
        //1.定义solr的服务器链接路径
        String solrUrl="http://localhost:8888/solr/new_core";
        //2.创建solr的链接对象
        solr = new HttpSolrClient.Builder(solrUrl).
                withConnectionTimeout(10000).
                withSocketTimeout(60000).build();
    }
    //添加
    @Test
    public void test01(){
        SolrInputDocument document = new SolrInputDocument();
        document.addField("xh",109);
        document.addField("age",27);
        document.addField("name","小翠儿");
        document.addField("sex","女");
        try {
            UpdateResponse updateResponse = solr.add(document);
            solr.commit();
            System.out.println("添加成功....."+updateResponse.toString());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("添加失败.....");
        }
    }
    //根据id删除
    @Test
    public void test02(){
        try {
            UpdateResponse updateResponse = solr.deleteById("aee38a74-6f2e-449b-ad64-b1356191ac19");
            solr.commit();
            System.out.println("删除了"+updateResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败.....");
        }
    }
    //根据条件模糊删除
    @Test
    public void test03(){
        try {
            UpdateResponse updateResponse = solr.deleteByQuery("name:王健林");
            solr.commit();
            System.out.println(updateResponse.toString());
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    //修改,根据id修改
    @Test
    public void test04(){
        SolrInputDocument document = new SolrInputDocument();
        document.addField("xh",109);
        document.addField("age",17);
        document.addField("name","小翠儿");
        document.addField("sex","女");
        document.addField("id","bf8cc20a-a90f-49d9-b304-0aa6ebb87f4a");
        try {
            UpdateResponse updateResponse = solr.add(document);
            solr.commit();
            System.out.println("修改结果"+updateResponse.toString());
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("修改失败");
        }
    }

    //修改,先删除,再添加
    @Test
    public void test05(){
        try {
            //删除
            solr.deleteByQuery("xh:109");
            //添加
            SolrInputDocument document = new SolrInputDocument();
            document.addField("xh",109);
            document.addField("age",77);
            document.addField("name","小翠儿");
            document.addField("sex","男");
            UpdateResponse add = solr.add(document);
            solr.commit();
            System.out.println(add.toString());
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
        }
    }
    //通过javaBean添加
    @Test
    public void test06(){
        Student student = new Student(102,"王健林",62,"男");
        try {
            UpdateResponse updateResponse = solr.addBean(student);
            solr.commit();
            System.out.println("添加结果"+updateResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }
    //javaBean批量添加
    @Test
    public void test07(){
        Student student1 = new Student(103,"马云",55,"男");
        Student student2 = new Student(104,"赵六",33,"男");
        Student student3 = new Student(105,"凤姐",35,"女");
        List<Student> studentList = Arrays.asList(student1,student2,student3);
        try {
            UpdateResponse updateResponse = solr.addBeans(studentList);
            solr.commit();
            System.out.println("批量添加结果"+updateResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("批量添加失败");
        }
    }
    //查询
    @Test
    public void test08(){
        //新建查询条件
        SolrQuery query = new SolrQuery();
        //查询条件
        query.set("q","*:*");
        //分页
        query.setStart(0);
        query.setRows(3);
        //执行查询
        try {
            QueryResponse queryResponse = solr.query(query);
            List<Student> studentList = queryResponse.getBeans(Student.class);
            for (Student student:studentList){
                System.out.println(student);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
