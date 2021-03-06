/*
 * MVP Android Copyright (C) 2016 Fatih.
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
package org.fs.util;

import io.reactivex.functions.Predicate;
import java.util.Collection;
import java.util.List;
import org.fs.exception.AndroidException;

public final class Collections {

  private Collections() {
    throw new AndroidException("no sugar for ya");
  }

  public static <T> List<T> filter(List<T> target, Predicate<T> predicate) {
    if(target == null || target.isEmpty()) throw new AndroidException("target is empty or null");
    if(predicate == null) throw new AndroidException("predicate is null, can't apply filter");
    return Objects.filter(target, predicate);
  }

  public static <T> int indexOfSubList(List<T> source, Collection<?> search) {
    return java.util.Collections.indexOfSubList(source, (List<?>) search);
  }

  public static <T> boolean isNullOrEmpty(Collection<T> collection) {
    return collection == null || collection.isEmpty();
  }
}
