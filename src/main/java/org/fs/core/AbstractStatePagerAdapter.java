/*
 * Copyright (C) 2016 Fatih.
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
package org.fs.core;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public abstract class AbstractStatePagerAdapter<D> extends FragmentStatePagerAdapter {

    /**
     * we might want to access it in child class
     */
    protected List<D> dataSet = null;

    public AbstractStatePagerAdapter(FragmentManager fragmentManager, List<D> dataSet) {
      super(fragmentManager);
      this.dataSet = dataSet;
    }

    protected abstract String   getClassTag();
    protected abstract boolean  isLogEnabled();
    protected abstract Fragment onBind(int position, D element);

    protected final void log(final String str) {
      log(Log.DEBUG, str);
    }

    protected final void log(Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter prtWriter = new PrintWriter(strWriter);
      e.printStackTrace(prtWriter);
      log(Log.ERROR, strWriter.toString());
    }

    protected final void log(final int lv, final String str) {
      if(isLogEnabled()) {
        Log.println(lv, getClassTag(), str);
      }
    }

    @Override public final Fragment getItem(int position) {
      return onBind(position, getItemAtIndex(position));
    }

    @Override public final int getCount() {
      return dataSet == null
          ? 0
          : dataSet.size();
    }

    protected final D getItemAtIndex(int index) {
      int limit = dataSet.size();
      if (index < 0 || index >= limit || limit == 0)
        return null;
      return dataSet.get(index);
    }
}
