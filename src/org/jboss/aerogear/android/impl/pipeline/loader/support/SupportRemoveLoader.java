/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.android.impl.pipeline.loader.support;

import android.content.Context;
import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.pipeline.PipeHandler;

/**
 * This class performs a remove operation on behalf of a Pipe using the Loader
 * infrastructure.
 *
 * As a Loader it will only call the remove once regardless of how many times it
 * is called unless reset() is called.
 *
 */
public class SupportRemoveLoader<T> extends AbstractSupportPipeLoader<T> {

    private final PipeHandler<T> runner;
    private final String id;
    private boolean isFinished = false;

    public SupportRemoveLoader(Context context, Callback<T> callback, PipeHandler<T> runner, String id) {
        super(context, callback);
        this.runner = runner;
        this.id = id;
    }

    @Override
    public T loadInBackground() {
        try {
            runner.onRemove(id);
            isFinished = true;
        } catch (Exception e) {
            super.exception = e;
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        if (isFinished) {
            deliverResult(null);
        } else {
            forceLoad();
        }
    }
}
