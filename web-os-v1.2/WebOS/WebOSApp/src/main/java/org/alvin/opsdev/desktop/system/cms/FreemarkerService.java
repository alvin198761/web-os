package org.alvin.opsdev.desktop.system.cms;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 静态生产工具
 */
@Service
public class FreemarkerService {


	@Autowired
	private freemarker.template.Configuration freeMarkerConfig;

	/**
	 * 通过freemark 把内容生成html
	 */
	public void parseToHtml(String templateName, Map<String, String> params, String fileName) throws IOException, TemplateException {
		Path outputFile = Paths.get(fileName);
		if (!Files.exists(outputFile.getParent())) {
			Files.createDirectory(outputFile.getParent());
		}
		Template template = freeMarkerConfig.getTemplate(templateName);
		try (Writer wr = Files.newBufferedWriter(outputFile)) {
			template.process(params, wr);
			wr.flush();
		}

	}

}
