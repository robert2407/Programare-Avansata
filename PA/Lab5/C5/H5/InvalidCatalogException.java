package org.example;

import static com.sun.tools.attach.VirtualMachine.list;
import static sun.nio.ch.IOUtil.load;

public class InvalidCatalogException extends Exception {
    public InvalidCatalogException(Exception exception) {
        super("Invalid file", exception);
    }
    public void errorCommand(String command) throws InvalidCatalogException {
        switch (command) {
            case "load":
                load();
                break;
            case "list":
                list();
                break;
            default:
                System.out.println(command + " error.");
        }
    }

}