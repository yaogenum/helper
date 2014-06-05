package search;

import java.io.FileNotFoundException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.spell.LevensteinDistance;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * ������ʾ�����صľ������,��ȡ��ʾ����
 * @since 1.6
 * @version 1.0
 * @author yaoge
 *
 */
public class PointSearcher {
	
	String path=this.getClass().getResource("/").getPath();
	static String type=null;
	
	static CopyOnWriteArrayList<String> list;
	
	//������ʾ���
	public CopyOnWriteArrayList<String> getpoints(String type,String theme,String sou,int numSug) throws IOException{
		File file=new File(path+"points/"+type+"/"+theme);
		String[] suggestions={};
		list=new CopyOnWriteArrayList<String>();
		
		if(file.exists()){
			Directory dir=FSDirectory.open(file); 
			SpellChecker checker=new SpellChecker(dir);
			checker.setStringDistance(new LevensteinDistance());
			suggestions=checker.suggestSimilar(theme, numSug);
			for(String s:suggestions){
				list.add(s);
			}
			dir.close();
			if(list.isEmpty()){
				list.add("nocons");						
			}
			else{
			}
			
		}
		else{
			
			list.add("nocon");
		}
		if(list.isEmpty()==false){
			System.out.println(list.toString());
		}
		return list;

	}
	
	public static void main(String args[]) throws IOException{
		PointSearcher s=new PointSearcher();
		s.getpoints("Digital","ipad","99",5);
		//System.out.println("ok");
	}

}
	
	//��ȡԴ����
	/*public boolean create() throws IOException{
		boolean bool=true;
		Directory dir=FSDirectory.open(new File("D:/unreal_path/helper/WebRoot/WEB-INF/classes/testindex"));
		IndexWriter writer=new IndexWriter(dir,new StandardAnalyzer(Version.LUCENE_30),true,IndexWriter.MaxFieldLength.UNLIMITED);
		Document doc=new Document();
		doc.add(new Field("content","������",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		
		doc=new Document();
		doc.add(new Field("content","���Ĳ���",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		
		doc=new Document();
		doc.add(new Field("content","������",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);

		doc=new Document();
		doc.add(new Field("content","������� ",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		
		doc=new Document();
		doc.add(new Field("content","�й���",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		

		doc=new Document();
		doc.add(new Field("content","�л����񹲺͹� ",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		
		writer.commit();
		writer.close();
		
		return bool;
		
		
		
	}
	*/
	
	
	//������ʾ����
	/*public String setspellindex(String type) throws IOException{
		
		this.type=type;
		File file=new File(path+"points/"+type);
		Directory dir=FSDirectory.open(file);//Ŀ������
		SpellChecker spell=new SpellChecker(dir);
		
		Directory dir2=FSDirectory.open(new File(path+"indexdir/"+type));//Դ����
		IndexReader reader=IndexReader.open(dir2);
		
		LuceneDictionary lucene=new LuceneDictionary(reader,"theme");
		LuceneDictionary lucenes=new LuceneDictionary(reader,"content");
		
		Iterator<String> iter=lucene.getWordsIterator();
		while(iter.hasNext()){
			System.out.println(":"+iter.next());
		}
		spell.indexDictionary(lucenes);
		spell.indexDictionary(lucene);
		
		dir.close();
		dir2.close();
		
		return path;
	}*/
	

