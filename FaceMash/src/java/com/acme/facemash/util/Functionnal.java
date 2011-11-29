package com.acme.facemash.util;

public class Functionnal {

    public static class Tuple<A, B> {

        final public A _1;
        final public B _2;

        public Tuple(A _1, B _2) {
            this._1 = _1;
            this._2 = _2;
        }

        public Tuple<B, A> swap() {
            return new Tuple<B, A>(_2, _1);
        }

        @Override
        public String toString() {
            return "Tuple ( _1: " + _1 + ", _2: " + _2 + " )";
        }
    }

    public static enum Unit {

        INSTANCE
    }

    public static interface Function<T, R> {

        R apply(T t);
    }
}
