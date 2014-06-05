package search;

import java.io.BufferedInputStream;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.MapFieldSelector;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexCommit;
import org.apache.lucene.index.IndexDeletionPolicy;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.KeepOnlyLastCommitDeletionPolicy;
import org.apache.lucene.index.SnapshotDeletionPolicy;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * Ramwriter�����ṩ���칦��,��������й��ܼ�����һ��������,ʵ���̣߳���Ϊ�Ժ���չʹ�� 1.���� 2.���� 3.��ѯ 4.������ʾ
 * ˵����
 * 1.�޸ķ�����standardanalyzerΪsmartchineseAnalyzer
 * 2.������չ,�ɽ������й��ܷ���ȫ���ֿ�����Ӱ�칦��
 * 3.������־��ʹ��log4j��Ϊ�˽�����ϸ���Զ�����־���м�⡣���裬�����м���basic,properties,document���ã���Ŀ����ʹ��log4j��¼��־
 * 
 * @since 1.6
 * @version 1.0
 * @author yaoge
 * 
 */

public class Ramwriter { // implements Runnable
	static RAMDirectory ram = null;
	static IndexWriter writer = null;
	static Document doc = null;
	static IndexWriter logwriter = null;
	static Directory dir = null;

	static IndexReader reader = null;
	static IndexSearcher searcher = null;
	static IndexSearcher sou_searcher = null;

	static int sum = 0;

	static Directory sou_dir = null;

	static TermQuery query = null;
	static Term term = null;

	static BufferedInputStream inBuff = null;
	static BufferedOutputStream outBuff = null;

	static boolean backbool;

	static File filelog = null;
	static FileWriter w = null;
	static String theme = null;
	String path = this.getClass().getResource("/").getPath();// ·��

	/**
	 * �������ж����ĳ�ʼ��
	 * 
	 * @return void
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 */
	public boolean init() throws CorruptIndexException,
			LockObtainFailedException, IOException {
		boolean bool = true;
		ram = new RAMDirectory();
		writer = new IndexWriter(ram, new SmartChineseAnalyzer(
				Version.LUCENE_30), true, IndexWriter.MaxFieldLength.UNLIMITED);
		return bool;

	}

	public void getfilewriter() throws IOException {
		filelog = new File(path + "indexdir/log.txt");
		w = new FileWriter(filelog, true);
	}

	public void closefile() throws IOException {
		if (w == null) {
		} else {
			w.close();
		}
	}

	public boolean ram_close(Directory directory) throws CorruptIndexException,
			IOException {
		boolean bool = true;

		directory.close();
		return bool;
	}

	public boolean writer_close(IndexWriter indexwriter)
			throws CorruptIndexException, IOException {
		boolean bool = true;

		indexwriter.close();
		return bool;
	}

	/**
	 * �������ڴ��д��������¼����¼�Ͷ�ʱ�洢
	 * 
	 * @param ��Ʒ����
	 * @param �������Ʒ��
	 * @param ������
	 * @param ������Ϣ
	 * @return �Ƿ���������¼�ɹ�
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	public synchronized boolean add_doc(String type, String theme,

	String sender, String information)// ͬ��д��doc
			throws CorruptIndexException, IOException {
		// ��¼ִ�������Ϣ

		/*
		 * File filelog = new File(path+"indexdir/log.txt"); FileWriter w = new
		 * FileWriter(filelog, true);
		 */
		if (w == null) {
			getfilewriter();
		} else {
			// w.write("writer create ok");
		}

		// w.write(path);

		boolean bool = true;
		this.theme = theme;
		try {
			if (ram == null) {
				init();
				w.write(" not exits ram:\n" + "wrter restart" + (sum + 1)
						+ "ok\n");
				// System.out.println("wrter��������"+(sum + 1) + "ok");

			} else {
				// w.write((sum + 1) + "ok");
			}
			Field field = null;
			try {
				sum++;
				doc = new Document();
				synchronized (this) {
					doc.add(new Field("id", String.valueOf(sum),
							Field.Store.YES, Field.Index.NOT_ANALYZED));
					doc.add(new Field("type", type, Field.Store.YES,
							Field.Index.NOT_ANALYZED));

					doc.add(new Field("sender", sender, Field.Store.YES,
							Field.Index.ANALYZED));

					field = new Field("theme", theme, Field.Store.YES,
							Field.Index.ANALYZED);
					field.setBoost(1.2f);
					doc.add(field);
					doc.add(new Field("information", information,
							Field.Store.YES, Field.Index.ANALYZED));
					doc.add(new Field("date", (new SimpleDateFormat(
							"y/MM/dd HH:mm:ss")).format(Calendar.getInstance()
							.getTime()), Field.Store.YES,
							Field.Index.NOT_ANALYZED));
					writer.addDocument(doc);

					// �ֶɣ�д��Ӳ��
					if ((sum % 500) == 0) {
						w.write((sum) + " document crate and write to hd\n");
						logrotate(type);
					}

				}
			} catch (Exception e) {
				bool = false;

				w.write("write error:" + e.toString() + "\n");

			}

		} catch (Exception e) {
			w.write("adddoc:" + e.toString() + "\n");
		} finally {
			w.flush();
			// w.close();
		}
		if (sum > 10000000) {
			sum = 0;
		}
		return bool;
	}

	/**
	 * �ܵ�����add_doc.Ϊ�����¼���ڴ��ж�ʱ�洢��Ӳ��
	 * 
	 * @param ��Ʒ����
	 * @return �Ƿ񱸷ݳɹ�
	 * @throws IOException
	 */
	public synchronized boolean logrotate(String type) throws IOException {// �ֶ�ÿ100�Σ���RAM����β���,����,��������ʾ����

		// ��¼ִ����Ϣ
		// File files = new File("indexdir/log.txt");
		// String
		// path=this.getClass().getClassLoader().getResource("/").getPath();//·��
		// File filelog = new File(path+"indexdir/log.txt");
		// FileWriter w = new FileWriter(filelog, true);
		if (w == null) {
			getfilewriter();
		} else {
			// w.write("writer create ok");
		}

		boolean bool = true;
		boolean bool_file = false;
		try {
			// writer.optimize();
			if (reader != null) {
				reader.close();// �رս�ʵʱ��ȡ��reader���رջ���
			} else {
			}
			writer.close();// �ϲ�֮ǰ�ر�writer

			System.out.println("����" + sum);// �ۼ��ĵ���

			File file = new File(path + "indexdir/" + type);// ��̬���������ļ���
			// File file_copy = new File(path+"indexdir/" + type+"copy");//
			// ��̬�������������ļ���

			if (file.exists() == false) {
				file.mkdir();
				// file_copy.mkdir();
				System.out.println(file.getName() + ":"
						+ file.getAbsolutePath());
				bool_file = true;
			} else {
				System.out.println("��д��Ӳ��");
				// file.delete();
				bool_file = false;
			}

			// �ϲ�����

			dir = FSDirectory.open(file);

			logwriter = new IndexWriter(dir, new SmartChineseAnalyzer(
					Version.LUCENE_30), bool_file,
					IndexWriter.MaxFieldLength.UNLIMITED);

			logwriter.addIndexesNoOptimize(ram);
			// ��־

			w.write(sum + "document have write to hd \n");

			// �Ż�����������
			logwriter.setMergeFactor(3);// �Ż����ݶΣ��߼��򵥣��ϲ�Ϊһ����
			logwriter.optimize(5);

			logwriter.close();

			// ������ʾ����

			// ���屸�ݲ���
			// �ȱ��ݡ���˵û��Ҫ���Ż�Ϊ�Ժ�����,ÿ������12��
			// ����
			SimpleDateFormat simple = new SimpleDateFormat("Hmmss");

			String backuptime = simple.format(Calendar.getInstance().getTime());

			int inttime = Integer.parseInt(backuptime);

			if (inttime > 220000 && inttime <= 230000) {
				backbool = true;
			} else {
				backbool = false;
			}

			if (inttime > 230000 && inttime < 240000 && backbool) {
				w.write("have backup before\n");
				backup(type);
				w.write("have backup after\n");
				backbool = false;

			} else {
				w.write("time is not 24:0:0\n");

			}
			// backup(type);
			ram.close();
			dir.close();

			// ����������������ʾ
			// if (inttime > 040000 && inttime < 050000 && backbool) {
			w.write("points:create points index before\n");
			setspellindex(type, this.theme);
			w.write("points:create points index ok\n");
			// }
			// else{
			w.write("points:time no create points index\n");
			// }

			init();

		} catch (Exception e) {
			w.write("logrotate:" + e.toString() + "\n");
			System.out.println(e.toString());
		} finally {
			// w.close();
			w.flush();
		}
		return bool;

	}

	/**
	 * ��Ҫɾ��ԭ���Ŀ��ձ��ݣ��ٽ���copy������������Ϣ�ı���
	 * 
	 * @param ��Ʒ����
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 */
	public void backup(String type) throws CorruptIndexException,
			LockObtainFailedException, IOException {

		if (w == null) {
			getfilewriter();
		} else {
			// w.write("writer create ok");
		}

		IndexDeletionPolicy policy = new KeepOnlyLastCommitDeletionPolicy();
		SnapshotDeletionPolicy snapshotter = new SnapshotDeletionPolicy(policy);
		File file_copy = new File(path + "indexdir/" + type + "copy");// ��̬�������������ļ���
		boolean back = false;
		if (file_copy.exists() == false) {
			file_copy.mkdir();
			System.out.println(file_copy.getName() + ":"
					+ file_copy.getAbsolutePath());
			back = true;
			w.write("back:first backup\n");
		} else {
			// ��ȫɾ��һ��
			delefiles(file_copy);
			file_copy.mkdir();
			w.write("back:not first time backup\n");
			// file.delete();
		}

		logwriter = new IndexWriter(dir, new SmartChineseAnalyzer(
				Version.LUCENE_30), snapshotter,
				IndexWriter.MaxFieldLength.UNLIMITED);
		File Source_file = null;
		File dis_file = null;

		try {
			IndexCommit commit = snapshotter.snapshot();
			Collection<String> fileNames = commit.getFileNames();
			Iterator iter = fileNames.iterator();
			String copypath = null;
			while (iter.hasNext()) {
				copypath = (String) iter.next();
				Source_file = new File(path + "indexdir/" + type + "/"
						+ copypath);// ��ȡԴ�ļ�
				dis_file = new File(path + "indexdir/" + type + "copy" + "/"
						+ copypath);// ��ȡĿ���ļ�
				copyFile(Source_file, dis_file);
				// logwriter.setMergeFactor(3);
				w.write("backup:is creating snapshot" + copypath + "\n");
			}
			w.write("backup:snapshot ok\n");
		} catch (Exception e) {
			if (w == null) {
				getfilewriter();
			} else {
				// w.write("writer create ok");
				w.write("backup:" + e.toString() + "\n");
			}
		} finally {
			snapshotter.release();
			logwriter.optimize();
			logwriter.setMergeFactor(3);
			logwriter.close();
			closefile();
			getfilewriter();
		}
	}

	public static boolean delefiles(File file) {
		boolean bools = true;
		try {
			File files[] = file.listFiles();

			for (File filesingle : files) {
				filesingle.delete();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			bools = false;
		}
		return bools;
	}

	/**
	 * ����ļ��ĸ��Ʋ���
	 * 
	 * @param sourceFile
	 *            ��targetFile��Դ�ļ���ַ��Ŀ���ļ���ַ
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {

		try {
			// �½��ļ����������������л���
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// �½��ļ���������������л���
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// ��������
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// ˢ�´˻���������
			outBuff.flush();
		} finally {
			// �ر���
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	//
	/**
	 * 
	 * �����ѯΪ��ȡĳ����Ʒ�����300��������Ϣ
	 * ��ȡ����10��/2s��¼,��ʵʱ��ȡram,���ر�reader:©������writer���´򿪣�reader��ȡʱ����
	 * ����Ҫͬ�����ƣ�filedcache��ʱ���ʵʱ��
	 * 
	 * @param ��Ʒ����
	 * @param ��Ʒ����
	 * @return ����ѯ��������Ϣ
	 * @throws IOException
	 */
	public synchronized CopyOnWriteArrayList<String> get_theme_content(
			String type, String theme) throws IOException {
		// String
		// path=this.getClass().getClassLoader().getResource("/").getPath();//·��
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

		if (ram == null) {
			init();

			// System.out.println("wrter��������"+(sum + 1) + "ok");
		} else {

		}

		if (sum % 1 != 0) {
			list = null;
		} else {
			int i = 0;

			/*
			 * File filelog = new File(path+"indexdir/log.txt"); FileWriter w =
			 * new FileWriter(filelog, true);
			 */

			if (w == null) {
				getfilewriter();
			} else {
				// w.write("writer create ok");
			}

			// �Թ̶���

			reader = writer.getReader();

			/*
			 * String[] id=FieldCache.DEFAULT.getStrings(reader, "id"); String[]
			 * sender=FieldCache.DEFAULT.getStrings(reader, "sender"); String[]
			 * information=FieldCache.DEFAULT.getStrings(reader, "information");
			 * String[] date=FieldCache.DEFAULT.getStrings(reader, "date"); long
			 * a=System.currentTimeMillis(); for(i=0;i<id.length;i++){
			 * list.add(id[i]); list.add(sender[i]); list.add(information[i]);
			 * list.add(date[i]); //System.out.println("::"+i); } long
			 * b=System.currentTimeMillis()-a; System.out.println("ʱ�䣺"b);
			 */

			searcher = new IndexSearcher(reader);

			MapFieldSelector s = new MapFieldSelector("sender");

			// MatchAllDocsQuery all_query = new MatchAllDocsQuery();

			WildcardQuery termquerys = new WildcardQuery(new Term("theme",
					theme + "*"));

			TopDocs tops = searcher.search(termquerys, null, 300, new Sort(
					new SortField("date", SortField.STRING, true)));
			// long a=System.currentTimeMillis();
			for (ScoreDoc score : tops.scoreDocs) {
				i++;
				doc = searcher.doc(score.doc);
				// list.add(doc.get("id"));
				list.add(doc.get("information") + ":" + doc.get("date") + ":"
						+ "  " + doc.get("sender") + "\n");
				// System.out.println("���в��" + i);
			}
			// long b=System.currentTimeMillis();
			// System.out.println("ʱ�䣺"+a+"::"+b);
			w.write("content:write out " + i + "ok!\n");
			// w.close();
			w.flush();
		}

		return list;
	}

	// ����Խ���ʵ�Ĺرգ��ɲ�ʹ��
	public boolean get_theme_content_close() throws IOException {

		boolean bool = true;
		searcher.close();
		reader.close();
		return bool;
	}

	/**
	 * 
	 * �����ѯ��ѯ����ĶԵ�����Ʒ�ļ�¼
	 * ��ȡ����10��/2s��¼,��ʵʱ��ȡram,���ر�reader:©������writer���´򿪣�reader��ȡʱ����
	 * ����Ҫͬ�����ƣ�filedcache��ʱ���ʵʱ��
	 * 
	 * @param ��Ʒ����
	 * @param ��Ʒ����
	 * @return ����ѯ��������Ϣ
	 * @throws IOException
	 */
	public synchronized CopyOnWriteArrayList<String> get_theme_history(

	String type, String theme) throws IOException {
		// String
		// path=this.getClass().getClassLoader().getResource("/").getPath();//·��
		int i = 0;
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		// File filelog = new File(path+"indexdir/log.txt");
		// FileWriter w = new FileWriter(filelog, true);
		if (w == null) {
			getfilewriter();
		} else {
			// w.write("writer create ok");
		}

		// �Թ̶���
		// ������writer.getReader();
		File file = new File(path + "indexdir/" + type);
		if (file.exists()) {// ����
			if (theme == null || theme.equals("")) {
				theme = "*:*";
			} else {
			}

			sou_dir = FSDirectory.open(file);

			// FieldSelector chose=new MapFieldSelector("id","");
			sou_searcher = new IndexSearcher(sou_dir);

			term = new Term("theme", theme);
			query = new TermQuery(term);

			TopDocs tops = sou_searcher.search(query, 1000);

			for (ScoreDoc score : tops.scoreDocs) {
				i++;
				doc = sou_searcher.doc(score.doc);
				// doc.get("id")
				list.add(String.valueOf(i) + ":" + doc.get("sender") + ":"
						+ doc.get("date") + ":" + doc.get("information") + "\n");
				// list.add();
				// list.add();
				// list.add();
				// System.out.println("history:"+doc.get("id"));
			}

			w.write("history:write out " + i + "ok!" + "\n");
			// w.close();
			w.flush();
		} else {
			w.write("no content\n");
		}

		return list;
	}

	/**
	 * �������ݽ���׼ȷ��ѯ type��theme,���и�����ʾ����ֹ��ѯʱ��3s(����ȡ),�������ݽ��з���bunweik
	 * 
	 * @param type
	 *            ,theme,sender��information����Ʒ�����Ʒ���ƣ�������Ϣ�ߣ�������Ϣ
	 * @return ��ѯ�������
	 * @throws IOException
	 * @throws ParseException
	 * @throws java.text.ParseException
	 * @throws InvalidTokenOffsetsException
	 */
	public synchronized CopyOnWriteArrayList<String> searcher_interest(

	String type, String theme, String sender, String information)
			throws IOException, ParseException, java.text.ParseException,
			InvalidTokenOffsetsException {
		// String
		// path=this.getClass().getClassLoader().getResource("/").getPath();//·��
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		int i = 0;
		if (information == null || information.equals("")) {
			information = "*:*";
		} else {

		}
		// File filelog = new File(path+"indexdir/log.txt");
		// FileWriter w = new FileWriter(filelog, true);
		if (w == null) {
			getfilewriter();
		} else {
			// w.write("writer create ok");
		}
		// �Թ̶���
		// ������writer.getReader();
		File file = new File(path + "indexdir/" + type);

		sou_dir = FSDirectory.open(file);
		sou_searcher = new IndexSearcher(sou_dir);

		QueryParser parser = new QueryParser(Version.LUCENE_30, "information",
				new SmartChineseAnalyzer(Version.LUCENE_30));
		Query query_infor = parser.parse(information);// ������Ϣ

		// QueryParser parser_time=new QueryParser(Version.LUCENE_30,"date",new
		// SmartChineseAnalyzer(Version.LUCENE_30));
		parser.parse(information);// ������Ϣ
		TermQuery theme_query = new TermQuery(new Term("theme", theme));// ��Ʒ����ѯ

		FuzzyQuery sender_query = new FuzzyQuery(new Term("sender", sender));// ����
		WildcardQuery wildquery = new WildcardQuery(new Term("sender", "?"
				+ sender + "*"));
		BooleanQuery sender_qeruy = new BooleanQuery();

		sender_qeruy.add(sender_query, Occur.SHOULD);
		sender_qeruy.add(wildquery, Occur.SHOULD);
		// ʱ�������Χ
		SimpleDateFormat simple = new SimpleDateFormat("y/MM/dd");
		// Date time=simple.parse(date);
		// Date date_from=new Date(time.getTime() - 5 * 24 * 60 * 60 *
		// 1000);//����
		// String from_time=simple.format(date_from);
		// Query
		// parser_date=parser_time.parse("date:["+from_time+" TO��"+date+"]");

		BooleanQuery bool_query = new BooleanQuery();
		bool_query.add(theme_query, Occur.MUST);
		// bool_query.add(parser_date,Occur.MUST);
		bool_query.add(query_infor, Occur.SHOULD);
		bool_query.add(sender_qeruy, Occur.SHOULD);
		TokenStream stream = null;
		// ʱ������,��js������Ƹ���
		// TopScoreDocCollector tops_col=TopScoreDocCollector.create(500,false);
		// Collector col=new TimeLimitingCollector(tops_col,3000);

		TopDocs tops = sou_searcher.search(bool_query, null, 500, new Sort(
				new SortField("date", SortField.STRING, true)));

		String text = null;
		TermQuery infor_query = new TermQuery(new Term("infor", information));
		String hightext = null;
		for (ScoreDoc score : tops.scoreDocs) {
			i++;
			doc = sou_searcher.doc(score.doc);
			// list.add(doc.get("id"));

			// ������ʾ
			text = doc.get("information");
			stream = (new SimpleAnalyzer()).tokenStream("infor",
					new StringReader(text));
			QueryScorer queryscore = new QueryScorer(infor_query, "infor");
			Highlighter lighter = new Highlighter(queryscore);
			SimpleSpanFragmenter fragmenter = new SimpleSpanFragmenter(
					queryscore);
			lighter.setTextFragmenter(fragmenter);
			// list.add();
			hightext = lighter.getBestFragment(stream, text);

			if (hightext.isEmpty() || hightext.length() < 2 || hightext == null) {
				hightext = doc.get("information");
			} else {

			}

			list.add(String.valueOf(i) + ":" + doc.get("sender") + ":"
					+ doc.get("date") + ":" + hightext + "\n");
			// list.add();
			// System.out.println(lighter.getBestFragment(stream, text));
			// System.out.println("�������"+doc.get("id"));
		}

		w.write("interest:write out " + i + "ok!\n");
		// w.close();
		w.flush();
		return list;
	}

	/**
	 * ���ڴ����û���������ݽ���Ԥ��������ʾ���û����������������������Ϣ���ش洢�е���شʣ�����Ч����̫�ã����鲻ʹ�ã�
	 * 
	 * @param type
	 *            ,theme:��
	 * @return ��ش�
	 * @throws IOException
	 */
	public synchronized String setspellindex(String type, String theme)
			throws IOException {

		File file = new File(path + "points/" + type + "/" + theme);
		Directory dir = FSDirectory.open(file);// Ŀ������
		SpellChecker spell = new SpellChecker(dir);

		Directory dir2 = FSDirectory.open(new File(path + "indexdir/" + type));// Դ����
		IndexReader reader = IndexReader.open(dir2);

		/*
		 * IndexSearcher find=new IndexSearcher(reader); TermQuery query=new
		 * TermQuery(new Term("theme",theme)); TopDocs tops=find.search(query,
		 * 100); Document doc=new Document(); for(ScoreDoc
		 * score:tops.scoreDocs){ doc=find.doc(score.doc);
		 * System.out.println(doc.get("theme")+":"+doc.get("information")); }
		 */

		LuceneDictionary lucene = new LuceneDictionary(reader, "theme");

		Iterator<String> iter = lucene.getWordsIterator();
		while (iter.hasNext()) {
			System.out.println(":" + iter.next());
		}
		spell.indexDictionary(lucene);

		dir.close();
		dir2.close();

		return path;
	}

	/**
	 * �˴��������̲��Ե�ǰ������Ĳ��ԣ� ��ʾ��������࣬�����ʹ��JUNIT4
	 */

	public static void main(String args[]) throws CorruptIndexException,
			IOException, ParseException, java.text.ParseException,
			InvalidTokenOffsetsException {// ��������

		Ramwriter r = new Ramwriter();

		/*
		 * for (int i = 1; i <= 1000; i++) { r.add_doc("Digital", "ipad",
		 * "�Ұ�ipad" + i, "sharefssssly" + i);
		 * 
		 * }
		 */
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		// list = r.get_theme_history("Digital", "ipad");
		list = r.searcher_interest("Digital", "ipad", "share", "share");

		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			iter.next();

		}
		// r.setspellindex("Digital","ipad");
		// System.out.println(r.sum); //
		// r.get_theme_history("Digital", "share");
		System.out.println("�����");
		// r.logrotate("Digital"); // r.backup("Digital");
	}

	/*
	 * public void run() { // TODO Auto-generated method stub
	 * 
	 * }
	 */

}
