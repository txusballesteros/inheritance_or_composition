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
package com.txusballesteros.labs.presentation;

import android.support.annotation.NonNull;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class AbsPresenter<VIEW extends Presenter.View> implements Presenter<VIEW> {
  private boolean isViewAttached;
  private VIEW view;

  public final boolean isViewAttached() {
    return isViewAttached;
  }

  @Override
  public final VIEW getView() {
    return this.view;
  }

  @Override
  public void onAttach(@NonNull VIEW view) {
    this.view = view;
    this.isViewAttached = true;
    onPresenterReady();
  }


  @Override
  public void onDetach() {
    this.view = buildDynamicProxyForView();
    this.isViewAttached = false;
    onPresenterDestroy();
  }

  protected void onPresenterDestroy() {
  }

  protected void onPresenterReady() { }

  private VIEW buildDynamicProxyForView() {
    final Class<?> viewClass = getViewInterfaceClass();
    InvocationHandler emptyHandler = new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
      }
    };
    ClassLoader classLoader = viewClass.getClassLoader();
    Class[] interfaces = new Class[1];
    interfaces[0] = viewClass;
    return  (VIEW) Proxy.newProxyInstance(classLoader, interfaces, emptyHandler);
  }

  private Class<?> getViewInterfaceClass() {
    Class<?> interfaceClass = null;
    Class<?>[] interfaces = this.view.getClass().getInterfaces();
    for (int i = 0; i < interfaces.length; i++) {
      Class<?> interfaceCandidate = interfaces[i];
      if (Presenter.View.class.isAssignableFrom(interfaceCandidate)) {
        interfaceClass = interfaceCandidate;
      }
    }
    return interfaceClass;
  }
}
