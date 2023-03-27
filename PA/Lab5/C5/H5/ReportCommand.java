package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    private Catalog catalog;
    private String path;

    public ReportCommand(Catalog catalog, String path) {
        this.catalog = catalog;
        this.path = path;
    }

    @Override
    public void execute() throws IOException {
        Configuration config = new Configuration(Configuration.VERSION_2_3_30);
        config.setClassForTemplateLoading(this.getClass(), "/templates");

        Map<String, Object> data = new HashMap<>();
        data.put("catalog", catalog);
        System.out.println(catalog);

        Template temp = config.getTemplate("teste.html");

        File file = new File(path);
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));

        try {
            temp.process(data, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
