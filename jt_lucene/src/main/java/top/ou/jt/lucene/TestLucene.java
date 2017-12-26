/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: TestLucene
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/26 17:10
 * Description: 模拟全文搜索
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;


/**
 * 〈一句话功能简述〉<br> 
 * 〈模拟全文搜索〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/26 17:10
 * @since 1.0.0
 */
public class TestLucene {
    /**
     * 步骤
     * 1、要把信息转换成Document
     * 2、创建索引、分词
     * 3、搜索
     */

    @Test
    public void doc() throws Exception {
        //1.创建一个文档对象
        Document document = new Document();

        //2.参数一：字段，参数二：商品id，参数三：是否存储
        //添加搜索该商品的信息
        document.add(new LongField("id",11252778L,Store.YES));
        document.add(new TextField("title","深入理解Java虚拟机：JVM高级特性与最佳实践（第2版）",Store.YES));
        document.add(new DoubleField("price",54.70d,Store.YES));
        document.add(new StringField("image","//img13.360buyimg.com/n1/jfs/t6130/167/771989293/235186/608d0264/592bf167Naf49f7f6.jpg",Store.YES));
        document.add(new IntField("num",100,Store.NO));

        //3.创建索引
        //3.1创建目录,放在当前工程的目录下
        Directory dir = FSDirectory.open(Paths.get("./index"));
        //3.2创建解析器,标准的分词器
        Analyzer analyzer = new StandardAnalyzer();
        //3.3创建配置
        IndexWriterConfig conf = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(dir,conf);

        //4.写索引文件
        indexWriter.addDocument(document);

        //5.关闭目录
        dir.close();

    }


    @Test
    public void search() throws IOException {
        //1、获取目录
        Directory dir = FSDirectory.open(Paths.get("./index"));
        //2、创建一个搜索的对象
        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(dir));

        //3、创建搜索条件
        TermQuery query = new TermQuery(new Term("title","java"));
        //lucene支持分页
        TopDocs topDocs = searcher.search(query,10);
        //遍历
        for (ScoreDoc scoreDoc : topDocs.scoreDocs){
            System.out.println("得分："+scoreDoc.score);
            
            //根据获取索引号来获取document
            //查询最终document对象
            Document doc = searcher.doc(scoreDoc.doc);
            //获取商品信息
            System.out.println(doc.get("id"));
            System.out.println(doc.get("title"));
            System.out.println(doc.get("price"));
            System.out.println(doc.get("image"));
        }
    }
}
