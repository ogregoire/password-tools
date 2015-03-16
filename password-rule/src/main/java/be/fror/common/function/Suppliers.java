/*
 * Copyright 2015 Olivier Grégoire <fror@users.noreply.github.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.fror.common.function;

import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 *
 * @author Olivier Grégoire &lt;fror@users.noreply.github.com&gt;
 */
public final class Suppliers {

  private Suppliers() {
  }

  public static <T> Supplier<T> memoize(Supplier<T> delegate) {
    return (delegate instanceof MemoizingSupplier)
        ? delegate
        : new MemoizingSupplier<>(Preconditions.checkNotNull(delegate));
  }

  static class MemoizingSupplier<T> implements Supplier<T>, Serializable {

    private static final long serialVersionUID = 0;
    final Supplier<T> delegate;
    transient volatile boolean initialized;

    transient T value;

    MemoizingSupplier(Supplier<T> delegate) {
      this.delegate = delegate;
    }

    @Override
    public T get() {
      if (!this.initialized) {
        synchronized (this) {
          if (!this.initialized) {
            T t = this.delegate.get();
            this.value = t;
            this.initialized = true;
            return t;
          }
        }
      }
      return this.value;
    }
  }
}