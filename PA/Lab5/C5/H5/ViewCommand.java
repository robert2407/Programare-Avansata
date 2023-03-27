package org.example;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command {
    private Document document;

    public ViewCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() throws IOException {
        Desktop.getDesktop().open(new File(document.getLocation()));
    }
}
