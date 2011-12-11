package com.acme.facemash.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;

/**
 * Util class for functionnal programming style.
 * 
 * @author mathieuancelin
 */
public class Functionnal {
    
    final static None<Object> none = new None<Object>();

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
    
    public static interface Monad<T> {

        <R> Option<R> map(Function<T, R> function);

    }

    public static abstract class Option<T> implements Iterable<T>, Monad<T>, Serializable {
        
        public abstract boolean isDefined();

        public abstract boolean isEmpty();
        
        public abstract T get();

        public Option<T> orElse(T value) {
            return isEmpty() ? Option.maybe(value) : this;
        }
        
        public T getOrElse(T value) {
            return isEmpty() ? value : get();
        }

        public T getOrElse(Function<Unit, T> function) {
            return isEmpty() ? function.apply(Unit.INSTANCE) : get();
        }

        public T getOrNull() {
            return isEmpty() ? null : get();
        }
        
        public Option<T> filter(Function<T, Boolean> predicate) {
            if (isDefined()) {
                if (predicate.apply(get())) {
                    return this;
                } else {
                    return Option.none();
                }
            }
            return Option.none();
        }
        
        public Option<T> filterNot(Function<T, Boolean> predicate) {
            if (isDefined()) {
                if (!predicate.apply(get())) {
                    return this;
                } else {
                    return Option.none();
                }
            }
            return Option.none();
        }

        @Override
        public <R> Option<R> map(Function<T, R> function) {
            if (isDefined()) {
                return Option.maybe(function.apply(get()));
            }
            return Option.none();
        }

        public static <T> None<T> none() {
            return (None<T>) (Object) none;
        }

        public static <T> Some<T> some(T value) {
            return new Some<T>(value);
        }

        public static <T> Maybe<T> maybe(T value) {
            return new Maybe<T>(value);
        }
    }

    public static class None<T> extends Option<T> {

        @Override
        public boolean isDefined() {
            return false;
        }

        @Override
        public T get() {
            throw new IllegalStateException("No value");
        }

        @Override
        public Iterator<T> iterator() {
            return Collections.<T>emptyList().iterator();
        }

        @Override
        public String toString() {
            return "None";
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }

    public static class Some<T> extends Option<T> {

        final T value;

        public Some(T value) {
            this.value = value;
        }

        @Override
        public boolean isDefined() {
            return true;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public Iterator<T> iterator() {
            return Collections.singletonList(value).iterator();
        }

        @Override
        public String toString() {
            return "Some ( " + value + " )";
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
    
    public static class Maybe<T> extends Option<T> {

        private final T input;

        public Maybe(T input) {
            this.input = input;
        }

        @Override
        public boolean isDefined() {
            return !(input == null);
        }

        @Override
        public T get() {
            return input;
        }

        @Override
        public Iterator<T> iterator() {
            if (input == null) {
                return Collections.<T>emptyList().iterator();
            } else {
                return Collections.singletonList(input).iterator();
            }
        }

        @Override
        public String toString() {
            return "Maybe ( " + input + " )";
        }

        @Override
        public boolean isEmpty() {
            return !isDefined();
        }
    }
}
