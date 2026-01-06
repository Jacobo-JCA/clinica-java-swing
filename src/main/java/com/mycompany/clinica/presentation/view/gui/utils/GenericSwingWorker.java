package com.mycompany.clinica.presentation.view.gui.utils;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import javax.swing.SwingWorker;

public class GenericSwingWorker<T> extends SwingWorker<T, Void> {
    private final Callable<T> tarea;
    private final Consumer<T> mostrarDatos;
    private final Consumer<Exception> error;

    public GenericSwingWorker(Callable<T> tarea, Consumer<T> mostrarDatos, Consumer<Exception> error) {
        this.tarea = tarea;
        this.mostrarDatos = mostrarDatos;
        this.error = error;
    }

    @Override
    protected T doInBackground() throws Exception {
        return tarea.call();
    }

    @Override
    protected void done() {
        try {
            T result = get();
            mostrarDatos.accept(result);
        } catch (Exception e) {
            error.accept(e);
        }
    }
}
