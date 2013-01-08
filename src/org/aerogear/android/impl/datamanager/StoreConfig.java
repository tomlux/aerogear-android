/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aerogear.android.impl.datamanager;

import android.content.Context;
import com.google.gson.GsonBuilder;
import org.aerogear.android.datamanager.IdGenerator;
import org.aerogear.android.datamanager.StoreType;

public final class StoreConfig {
    private Context context;
    private Class klass;
    private StoreType type = StoreTypes.MEMORY;
    private GsonBuilder builder = new GsonBuilder();
    private IdGenerator idGenerator = new DefaultIdGenerator();

    public StoreConfig() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Class getKlass() {
        return klass;
    }

    public void setKlass(Class klass) {
        this.klass = klass;
    }

    public StoreType getType() {
        return type;
    }

    public void setType(StoreType type) {
        this.type = type;
    }

    public GsonBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(GsonBuilder builder) {
        this.builder = builder;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

}
