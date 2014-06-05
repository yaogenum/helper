package spell;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.spell.LevensteinDistance;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
//建立提示索引文件类
public class Createspellindex {
	
	String path=this.getClass().getResource("/").getPath();
	static String type=null;
	
	//建立提示索引
	public String setspellindex(String type) throws IOException{
		
		this.type=type;
		File file=new File(path+"points/"+type);
		Directory dir=FSDirectory.open(file);//目的索引
		SpellChecker spell=new SpellChecker(dir);
		
		Directory dir2=FSDirectory.open(new File(path+"testindex"));//源索引
		IndexReader reader=IndexReader.open(dir2);
		
		LuceneDictionary lucene=new LuceneDictionary(reader,"content");
		LuceneDictionary lucenes=new LuceneDictionary(reader,"contents");
		
		Iterator<String> iter=lucene.getWordsIterator();
		Iterator<String> iters=lucenes.getWordsIterator();
		while(iter.hasNext()){
			System.out.println(":"+iter.next());
			System.out.println(""+iters.next());
		}
		//spell.indexDictionary(new LuceneDictionary(reader,"theme"));
		spell.indexDictionary(lucene);
		spell.indexDictionary(lucenes);
		
		dir.close();
		dir2.close();
		
		return path;
	}
	
	//给出提示结果
	public String[] getpoints(String word,int numSug) throws IOException{
		Directory dir=FSDirectory.open(new File(path+"points/"+type)); 
		
		SpellChecker checker=new SpellChecker(dir);
		checker.setStringDistance(new LevensteinDistance());
		String suggestions[]=checker.suggestSimilar(word, numSug);
		for(String s:suggestions){
			System.out.println("结果:"+s);
		}
		dir.close();
		return suggestions;

	}
	
	//获取源索引
	public boolean create() throws IOException{
		boolean bool=true;
		Directory dir=FSDirectory.open(new File("D:/unreal_path/helper/WebRoot/WEB-INF/classes/testindex"));
		IndexWriter writer=new IndexWriter(dir,new SmartChineseAnalyzer(Version.LUCENE_30),true,IndexWriter.MaxFieldLength.UNLIMITED);
		Document doc=new Document();
		doc.add(new Field("content","麻辣烫",Field.Store.YES,Field.Index.ANALYZED));
		doc.add(new Field("contents","你好",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		
		doc=new Document();
		doc.add(new Field("content","中文测试",Field.Store.YES,Field.Index.ANALYZED));
		doc.add(new Field("contents","你好的",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		
		doc=new Document();
		doc.add(new Field("content","麻辣酱",Field.Store.YES,Field.Index.ANALYZED));
		doc.add(new Field("contents","他你好",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);

		doc=new Document();
		doc.add(new Field("content","麻辣火锅 ",Field.Store.YES,Field.Index.ANALYZED));
		doc.add(new Field("contents","好你妹",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		
		doc=new Document();
		doc.add(new Field("content","中国人",Field.Store.YES,Field.Index.ANALYZED));
		doc.add(new Field("contents","你好天天好",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		

		doc=new Document();
		doc.add(new Field("content","中华人民共和国 ",Field.Store.YES,Field.Index.ANALYZED));
		doc.add(new Field("contents","你好的不",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		
		writer.commit();
		writer.close();
		
		return bool;
		
		
		
	}
	
	
	
	
	
	
	
	
}















