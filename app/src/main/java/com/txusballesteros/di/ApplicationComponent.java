/*
 * Copyright Txus Ballesteros 2016 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.di;

import android.app.Application;
import com.txusballesteros.AndroidApplication;
import com.txusballesteros.data.di.DataSourcesProvider;
import com.txusballesteros.data.di.RepositoriesProvider;
import com.txusballesteros.domain.executor.PostExecutionThread;
import com.txusballesteros.domain.executor.ThreadExecutor;
import com.txusballesteros.instrumentation.di.InstrumentationProvider;
import com.txusballesteros.navigation.Navigator;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent extends RepositoriesProvider,
                                              MappersProvider,
                                              InstrumentationProvider,
                                              DataSourcesProvider {
  void inject(AndroidApplication androidApplication);

  Application getApplication();
  ThreadExecutor getThreadExecutor();
  PostExecutionThread getPostExecutionThread();
  Navigator getNavigator();
}
