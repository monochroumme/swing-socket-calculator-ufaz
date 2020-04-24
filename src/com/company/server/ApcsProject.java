package com.company.src;

import java.util.List;
import java.util.Locale;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class ApcsProject {

    public ApcsProject() {}

    public abstract void execute();

    protected final void printLine(Object... arg0) {
        pL(arg0);
    }

    protected final void printEmptyLines(long times) {
        pLs(times);
    }

    protected final void print(Object... arg0) {
        p(arg0);
    }

    protected final void printFormatted(String form, Object... obj) {
        pF(form, obj);
    }

    protected final void printFormatted(Locale loc, String form, Object... obj) {
        pF(loc, form, obj);
    }

    protected final void remove(List<?> e, Object obj) {
        e.remove(obj);
    }

    protected final void remove(List<?> e, int index) {
        e.remove(index);
    }

    protected final void add(List e, Object obj) {
        e.add(obj);
    }

    protected final void add(List e, int index, Object obj) {
        e.add(index, obj);
    }

    protected final Object get(List<?> e, int index) {
        return e.get(index);
    }

    protected final void set(List e, int index, Object obj) {
        e.set(index, obj);
    }

    public void discard() {
        try {
            this.finalize();
        } catch (Throwable t) {}
    }

    public static final void pL(Object... arg0) {
        if (arg0.length == 0) {
            System.out.println();
        }
        for (Object obj : arg0) {
            System.out.println(obj);
        }
    }

    public static final void pLs(long times) {
        for (long i = 0; i < times; i++) {
            pL();
        }
    }

    public static final void p(Object... arg0) {
        for (Object obj : arg0) {
            System.out.print(obj);
        }
    }

    protected final void pF(String form, Object... obj) {
        System.out.printf(form, obj);
    }

    protected final void pF(Locale loc, String form, Object... obj) {
        System.out.printf(loc, form, obj);
    }
}
