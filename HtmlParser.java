import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

/**
 * 分析www.cheshi.com首页新闻
 * @author j.li
 */
public class HtmlParser {
	private static Logger logger;
	private Connection conn = null;
	private static final String SiteName = "";

	public void indexNewsContent(String sitepath) throws Exception {
		logger.info("分析网站【" + sitepath + "】首页的新闻列表，内容为【<div class=\"hotjd\"></div>】所有网页新闻地址的HTML内容。");
		Parser myParser = new Parser(sitepath);
		myParser.setEncoding("GBK");
		NodeList nodeList = myParser.extractAllNodesThatMatch(new NodeFilter() {
			public boolean accept(Node node) {
				return ((node instanceof Tag)
		                && !((Tag)node).isEndTag()
		                && ((Tag)node).getTagName().equals("DIV")
		                && ((Tag)node).getAttribute("class") != null
		                && ((Tag)node).getAttribute("class").equals("w_box"));
			}
		});
		Node node = nodeList.elementAt(1);
		logger.debug(node.toHtml());
		extractText(node.toHtml());
	}
	
	public void extractText(String inputHtml) throws Exception {    
	    Parser parser = Parser.createParser(inputHtml, "GBK");
		TagNameFilter filter = new TagNameFilter("a");
		NodeList nodeList = parser.extractAllNodesThatMatch(filter);
		NodeIterator it = nodeList.elements();
		getConnection();
		while (it.hasMoreNodes()) {
			LinkTag node = (LinkTag) it.nextNode();
			String href = node.getLink();
			String title = node.getLinkText();
			logger.info("分析首页新闻【"+title+"】，链接地址【"+href+"】");
			try {
				if(!newsExist(title)) {
					insertDataBase(title, extractContent(href));
				} else {
					logger.info("新闻【"+title+"】数据库中已经存在，忽略进入下一个新闻分析！");
				}
			} catch (SQLException e) {
				logger.error("插入数据库新闻记录异常！" + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.info("分析新闻【"+title+"】，链接地址【"+href+"】失败，进入下一个新闻分析。");
				e.printStackTrace();
			}
		}
		closeConnection();
	}

	public String extractContent(String content) throws Exception {
		try {
			Parser myParser = new Parser(content);
			myParser.setEncoding("GBK");
			NodeList nodeList = myParser.extractAllNodesThatMatch(new NodeFilter() {
				public boolean accept(Node node) {
					return ((node instanceof Tag)
			                && !((Tag)node).isEndTag()
			                && ((Tag)node).getTagName().equals("DIV")
			                && ((Tag)node).getAttribute("class") != null
			                && ((Tag)node).getAttribute("class").equals("cs_content"));
				}
			});
			int size = nodeList.size();
			Node node = nodeList.elementAt(size - 1);
			content = node.toHtml();
			logger.debug("==========extractContent==============");
			logger.debug(content);
		} catch (Exception pe) {
			logger.error("分析新闻页面出现异常！" + pe.getMessage() + "原因可能出现于新闻页面不存在<div class=\"cs_content\"></div>标记。");
			throw pe;
		}
		return removeTagA(content);
	}
	
	/**
	 * 去除新闻中href包含cheshi.com的<a>标签
	 * @param content 分析html内容
	 * @return 分析处理后的html内容
	 */
	public String removeTagA(String content) throws ParserException {
		Parser myParser = new Parser(content);
		myParser.setEncoding("GBK");
		NodeList nodeList = myParser.extractAllNodesThatMatch(new TagNameFilter("a"));
		SimpleNodeIterator it = nodeList.elements();
		while (it.hasMoreNodes()) {
			LinkTag node = (LinkTag)it.nextNode();
			logger.info("移除新闻内容中包含的文字、图片的链接【"+node.toHtml()+"】。");
			if(node.getLink().indexOf("cheshi.com") > -1)
				content = content.replace(node.toHtml(), node.getStringText());
		}
		logger.debug("==========removeTagA==============");
		logger.debug(content);
		return downloadImages(content, "D:\\autodata\\upload\\intersite", SiteName + "upload/intersite");
	}

	public String downloadImages(String content, String uploadImgPath, String localhost) throws ParserException {
		File f = new File(uploadImgPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		Parser myParser = new Parser(content);
		myParser.setEncoding("GBK");
		NodeList nodeList = myParser.extractAllNodesThatMatch(new TagNameFilter("img"));
		SimpleNodeIterator it = nodeList.elements();
		while(it.hasMoreNodes()) {
			Tag tag = (Tag)it.nextNode();
			String src = tag.getAttribute("src");
			String filename = src.substring(src.lastIndexOf("/") + 1);
			InputStream is = null;
			FileOutputStream fos = null;
			try {
				URL url = new URL(src);
				is = url.openStream();
				int bytesRead = 0;
				byte[] buff = new byte[1024];
				fos = new FileOutputStream(uploadImgPath+"/"+filename);
				while((bytesRead = is.read(buff, 0, buff.length)) != -1){
					fos.write(buff, 0, bytesRead);
				}
				content = content.replace(src, localhost + "/" + filename);
			} catch(FileNotFoundException notFoundException) {
				notFoundException.printStackTrace();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			} finally {
				try {
					if(fos != null) fos.close();
					if(is != null) is.close();
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		logger.debug("=================downloadImages==================");
		logger.debug(content);
		return content;
	}
	
	public void getConnection() {
		try {
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			String strCon = "jdbc:microsoft:sqlserver://192.168.99.188:12580;databaseName=Project2009;SelectMethod=cursor";
			String strUserName = "sa";
			String strPWD = "qsyjcsxdl@@@web2009@@@";
			conn = DriverManager.getConnection(strCon, strUserName, strPWD);
		} catch (java.lang.ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			if(conn!= null && !conn.isClosed()) conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public void insertDataBase(String newsTitle, String newsContent) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO FumNews(NewsTitle, NewsContext, NewsState) values(?, ?, ?)");
			pstmt.setString(1, newsTitle);
			pstmt.setString(2, newsContent);
			pstmt.setInt(3, 1);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			throw e;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean newsExist(String title) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("SELECT top 1 NewsId from FumNews where NewsTitle = ?");
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch(SQLException e) {
			throw e;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		HtmlParser html = new HtmlParser();
//		设置代理链接网络
//		System.getProperties().put("proxySet", "true");
//		System.getProperties().put("proxyHost", "192.168.99.100");
//		System.getProperties().put("proxyPort", "80");
		URL url = html.getClass().getResource("log4j.properties");
		PropertyConfigurator.configure(url);
		logger = Logger.getLogger(HtmlParser.class);
		try	{
			html.indexNewsContent("http://www.cheshi.com/");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("分析网页遇到错误，原因："+e.getMessage());
		}
		logger.info("分析网页内容完成。");
	}
}
