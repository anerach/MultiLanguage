package me.arno.multilanguage.schedules;

import java.net.URL;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;

import me.arno.multilanguage.MultiLanguage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Updates implements Runnable {

	public void run() {
		Logger log = MultiLanguage.plugin.log;
		try {
        	URL url = new URL("http://dev.bukkit.org/server-mods/block-log/files.rss");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.openConnection().getInputStream());
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("item");
            Node firstNode = nodes.item(0);
            if (firstNode.getNodeType() == 1) {
                Element firstElement = (Element) firstNode;
                NodeList firstElementTagName = firstElement.getElementsByTagName("title");
                Element firstNameElement = (Element) firstElementTagName.item(0);
                NodeList firstNodes = firstNameElement.getChildNodes();
                MultiLanguage.plugin.newVersion = firstNodes.item(0).getNodeValue().replace("BlockLog", "").trim();
            }
			
            MultiLanguage.plugin.doubleCurrentVersion = Double.valueOf(MultiLanguage.plugin.currentVersion.replaceFirst("\\.", ""));
            MultiLanguage.plugin.doubleNewVersion = Double.valueOf(MultiLanguage.plugin.newVersion.replaceFirst("\\.", ""));
			
			if(MultiLanguage.plugin.doubleNewVersion > MultiLanguage.plugin.doubleCurrentVersion) {
				log.warning("BlockLog v" + MultiLanguage.plugin.newVersion + " is released! You're using BlockLog v" + MultiLanguage.plugin.currentVersion);
				log.warning("Update BlockLog at http://dev.bukkit.org/server-mods/block-log/");
			}
        } catch (Exception e) {
        	// Nothing
        }
	}
}
