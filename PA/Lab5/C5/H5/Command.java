package org.example;

import java.io.IOException;

public interface Command {
    void execute() throws InvalidCatalogException, IOException;
}