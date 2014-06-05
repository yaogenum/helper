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
 * Ramwriter用于提供聊天功能,这里把所有功能集成在一个类里面,实现线程，可为以后扩展使用 1.聊天 2.备份 3.查询 4.搜索提示
 * 说明：
 * 1.修改分析器standardanalyzer为smartchineseAnalyzer
 * 2.如需扩展,可将此类中功能方法全部分开，不影响功能
 * 3.此类日志非使用log4j，为了进行详细的自定义日志进行检测。如需，请自行加入basic,properties,document设置，项目其他使用log4j记录日志
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
	String path = this.getClass().getResource("/").getPath();// 路径

	/**
	 * 进行所有动作的初始化
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
	 * 用于在内存中处理聊天记录的收录和定时存储
	 * 
	 * @param 商品种类
	 * @param 聊天的商品名
	 * @param 聊天者
	 * @param 聊天信息
	 * @return 是否添加聊天记录成功
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	public synchronized boolean add_doc(String type, String theme,

	String sender, String information)// 同步写入doc
			throws CorruptIndexException, IOException {
		// 记录执行情况信息

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
				// System.out.println("wrter重新启动"+(sum + 1) + "ok");

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

					// 轮渡，写入硬盘
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
	 * 受调用与add_doc.为聊天记录从内存中定时存储到硬盘
	 * 
	 * @param 商品种类
	 * @return 是否备份成功
	 * @throws IOException
	 */
	public synchronized boolean logrotate(String type) throws IOException {// 轮渡每100次，将RAM并入段操作,备份,并创建提示索引

		// 记录执行信息
		// File files = new File("indexdir/log.txt");
		// String
		// path=this.getClass().getClassLoader().getResource("/").getPath();//路径
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
				reader.close();// 关闭进实时读取的reader，关闭缓存
			} else {
			}
			writer.close();// 合并之前关闭writer

			System.out.println("共计" + sum);// 累计文档数

			File file = new File(path + "indexdir/" + type);// 动态创建索引文件夹
			// File file_copy = new File(path+"indexdir/" + type+"copy");//
			// 动态创建备份索引文件夹

			if (file.exists() == false) {
				file.mkdir();
				// file_copy.mkdir();
				System.out.println(file.getName() + ":"
						+ file.getAbsolutePath());
				bool_file = true;
			} else {
				System.out.println("已写入硬盘");
				// file.delete();
				bool_file = false;
			}

			// 合并索引

			dir = FSDirectory.open(file);

			logwriter = new IndexWriter(dir, new SmartChineseAnalyzer(
					Version.LUCENE_30), bool_file,
					IndexWriter.MaxFieldLength.UNLIMITED);

			logwriter.addIndexesNoOptimize(ram);
			// 日志

			w.write(sum + "document have write to hd \n");

			// 优化方法不存在
			logwriter.setMergeFactor(3);// 优化数据段，逻辑简单，合并为一个段
			logwriter.optimize(5);

			logwriter.close();

			// 创建提示索引

			// 具体备份操作
			// 热备份。虽说没必要，优化为以后需求,每天晚上12点
			// 条件
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

			// 建立、更新索引提示
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
	 * 先要删除原来的快照备份，再进行copy操作的聊天信息的备份
	 * 
	 * @param 商品种类
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
		File file_copy = new File(path + "indexdir/" + type + "copy");// 动态创建备份索引文件夹
		boolean back = false;
		if (file_copy.exists() == false) {
			file_copy.mkdir();
			System.out.println(file_copy.getName() + ":"
					+ file_copy.getAbsolutePath());
			back = true;
			w.write("back:first backup\n");
		} else {
			// 完全删除一遍
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
						+ copypath);// 获取源文件
				dis_file = new File(path + "indexdir/" + type + "copy" + "/"
						+ copypath);// 获取目的文件
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
	 * 完成文件的复制操作
	 * 
	 * @param sourceFile
	 *            ，targetFile：源文件地址，目的文件地址
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {

		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	//
	/**
	 * 
	 * 此项查询为获取某种商品的最近300天聊天信息
	 * 读取内容10次/2s记录,进实时读取ram,不关闭reader:漏洞：当writer重新打开，reader获取时出错
	 * ，需要同步控制，filedcache，时间近实时快
	 * 
	 * @param 商品种类
	 * @param 商品名称
	 * @return 所查询的聊天信息
	 * @throws IOException
	 */
	public synchronized CopyOnWriteArrayList<String> get_theme_content(
			String type, String theme) throws IOException {
		// String
		// path=this.getClass().getClassLoader().getResource("/").getPath();//路径
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

		if (ram == null) {
			init();

			// System.out.println("wrter重新启动"+(sum + 1) + "ok");
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

			// 对固定的

			reader = writer.getReader();

			/*
			 * String[] id=FieldCache.DEFAULT.getStrings(reader, "id"); String[]
			 * sender=FieldCache.DEFAULT.getStrings(reader, "sender"); String[]
			 * information=FieldCache.DEFAULT.getStrings(reader, "information");
			 * String[] date=FieldCache.DEFAULT.getStrings(reader, "date"); long
			 * a=System.currentTimeMillis(); for(i=0;i<id.length;i++){
			 * list.add(id[i]); list.add(sender[i]); list.add(information[i]);
			 * list.add(date[i]); //System.out.println("::"+i); } long
			 * b=System.currentTimeMillis()-a; System.out.println("时间："b);
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
				// System.out.println("已有查出" + i);
			}
			// long b=System.currentTimeMillis();
			// System.out.println("时间："+a+"::"+b);
			w.write("content:write out " + i + "ok!\n");
			// w.close();
			w.flush();
		}

		return list;
	}

	// 仅针对近视实的关闭，可不使用
	public boolean get_theme_content_close() throws IOException {

		boolean bool = true;
		searcher.close();
		reader.close();
		return bool;
	}

	/**
	 * 
	 * 此项查询查询更早的对单个商品的记录
	 * 读取内容10次/2s记录,进实时读取ram,不关闭reader:漏洞：当writer重新打开，reader获取时出错
	 * ，需要同步控制，filedcache，时间近实时快
	 * 
	 * @param 商品种类
	 * @param 商品名称
	 * @return 所查询的聊天信息
	 * @throws IOException
	 */
	public synchronized CopyOnWriteArrayList<String> get_theme_history(

	String type, String theme) throws IOException {
		// String
		// path=this.getClass().getClassLoader().getResource("/").getPath();//路径
		int i = 0;
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		// File filelog = new File(path+"indexdir/log.txt");
		// FileWriter w = new FileWriter(filelog, true);
		if (w == null) {
			getfilewriter();
		} else {
			// w.write("writer create ok");
		}

		// 对固定的
		// 本质上writer.getReader();
		File file = new File(path + "indexdir/" + type);
		if (file.exists()) {// 存在
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
	 * 根据内容进行准确查询 type，theme,具有高亮显示，禁止查询时间3s(不可取),搜索内容进行分析bunweik
	 * 
	 * @param type
	 *            ,theme,sender，information：商品类别，商品名称，聊天信息者，聊天信息
	 * @return 查询结果集合
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
		// path=this.getClass().getClassLoader().getResource("/").getPath();//路径
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
		// 对固定的
		// 本质上writer.getReader();
		File file = new File(path + "indexdir/" + type);

		sou_dir = FSDirectory.open(file);
		sou_searcher = new IndexSearcher(sou_dir);

		QueryParser parser = new QueryParser(Version.LUCENE_30, "information",
				new SmartChineseAnalyzer(Version.LUCENE_30));
		Query query_infor = parser.parse(information);// 解析信息

		// QueryParser parser_time=new QueryParser(Version.LUCENE_30,"date",new
		// SmartChineseAnalyzer(Version.LUCENE_30));
		parser.parse(information);// 解析信息
		TermQuery theme_query = new TermQuery(new Term("theme", theme));// 商品名查询

		FuzzyQuery sender_query = new FuzzyQuery(new Term("sender", sender));// 发送
		WildcardQuery wildquery = new WildcardQuery(new Term("sender", "?"
				+ sender + "*"));
		BooleanQuery sender_qeruy = new BooleanQuery();

		sender_qeruy.add(sender_query, Occur.SHOULD);
		sender_qeruy.add(wildquery, Occur.SHOULD);
		// 时间解析范围
		SimpleDateFormat simple = new SimpleDateFormat("y/MM/dd");
		// Date time=simple.parse(date);
		// Date date_from=new Date(time.getTime() - 5 * 24 * 60 * 60 *
		// 1000);//五天
		// String from_time=simple.format(date_from);
		// Query
		// parser_date=parser_time.parse("date:["+from_time+" TO　"+date+"]");

		BooleanQuery bool_query = new BooleanQuery();
		bool_query.add(theme_query, Occur.MUST);
		// bool_query.add(parser_date,Occur.MUST);
		bool_query.add(query_infor, Occur.SHOULD);
		bool_query.add(sender_qeruy, Occur.SHOULD);
		TokenStream stream = null;
		// 时间限制,在js里面控制更好
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

			// 高亮显示
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
			// System.out.println("搜索结果"+doc.get("id"));
		}

		w.write("interest:write out " + i + "ok!\n");
		// w.close();
		w.flush();
		return list;
	}

	/**
	 * 用于处理用户输入的内容进行预处理，简单提示与用户输入内容相关且在聊天信息本地存储中的相关词（测试效果不太好，建议不使用）
	 * 
	 * @param type
	 *            ,theme:，
	 * @return 相关词
	 * @throws IOException
	 */
	public synchronized String setspellindex(String type, String theme)
			throws IOException {

		File file = new File(path + "points/" + type + "/" + theme);
		Directory dir = FSDirectory.open(file);// 目的索引
		SpellChecker spell = new SpellChecker(dir);

		Directory dir2 = FSDirectory.open(new File(path + "indexdir/" + type));// 源索引
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
	 * 此处用于立刻测试当前所有类的测试， 提示：如需更多，请测试使用JUNIT4
	 */

	public static void main(String args[]) throws CorruptIndexException,
			IOException, ParseException, java.text.ParseException,
			InvalidTokenOffsetsException {// 测试数据

		Ramwriter r = new Ramwriter();

		/*
		 * for (int i = 1; i <= 1000; i++) { r.add_doc("Digital", "ipad",
		 * "我爱ipad" + i, "sharefssssly" + i);
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
		System.out.println("已完成");
		// r.logrotate("Digital"); // r.backup("Digital");
	}

	/*
	 * public void run() { // TODO Auto-generated method stub
	 * 
	 * }
	 */

}
