package org.example.command;

import org.example.model.Sweets;

import java.io.IOException;

public interface Command {
    void execute();
    String getDescription();
}
