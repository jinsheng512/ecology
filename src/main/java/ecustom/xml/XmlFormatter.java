package ecustom.xml;

import java.io.StringWriter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XmlFormatter {

	public static String formatXml(String xmlStr) {
		try {
			Document document = null;
			document = DocumentHelper.parseText(xmlStr);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			StringWriter writer = new StringWriter();
			XMLWriter xmlWriter = new XMLWriter(writer, format);
			xmlWriter.write(document);
			xmlWriter.close();
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlStr;
	}
}
