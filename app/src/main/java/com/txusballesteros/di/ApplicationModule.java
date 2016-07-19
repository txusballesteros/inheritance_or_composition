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
import com.txusballesteros.data.di.DataSourcesModule;
import com.txusballesteros.data.di.RepositoriesModule;
import com.txusballesteros.domain.executor.JobExecutor;
import com.txusballesteros.domain.executor.MainThreadExecutor;
import com.txusballesteros.domain.executor.PostExecutionThread;
import com.txusballesteros.domain.executor.ThreadExecutor;
import com.txusballesteros.instrumentation.di.InstrumentationModule;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = {
     InstrumentationModule.class,
     RepositoriesModule.class,
     DataSourcesModule.class
})
public class ApplicationModule {
  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  Application provideApplication() {
    return application;
  }

  @Provides @Singleton
  ThreadExecutor provideThreadExecutor(JobExecutor executor) {
    return executor;
  }

  @Provides @Singleton
  PostExecutionThread providePostExecutionThread(MainThreadExecutor executor) {
    return executor;
  }
}
