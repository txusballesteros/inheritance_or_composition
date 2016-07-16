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
package com.txusballesteros.data.actors.datasource;

import com.txusballesteros.data.model.ActorDataModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class ActorsInMemoryDataSource implements ActorsDataSource {
  private Map<Long, ActorDataModel> dataSet;

  @Inject
  public ActorsInMemoryDataSource() {
    initializeAndFetchDataSet();
  }

  private void initializeAndFetchDataSet() {
    dataSet = new HashMap<>();
    persistActor(new ActorDataModel.Builder(1, "Emilia Clarke", "goo.gl/N3v3yA").build());
    persistActor(new ActorDataModel.Builder(2, "Kit Harington", "http://goo.gl/N3v3yA").build());
    persistActor(new ActorDataModel.Builder(3, "Peter Dinklage", "http://goo.gl/uZi901").build());
    persistActor(new ActorDataModel.Builder(4, "Lena Headey", "http://goo.gl/VbKwVi").build());
    persistActor(new ActorDataModel.Builder(5, "Maisie Williams", "http://goo.gl/IYsjeU").build());
    persistActor(new ActorDataModel.Builder(6, "Sophie Turner", "http://goo.gl/0CKs40").build());
    persistActor(new ActorDataModel.Builder(7, "Natalie Dormer", "http://goo.gl/ZZASjK").build());
  }

  private void persistActor(ActorDataModel actor) {
    dataSet.put(actor.getId(), actor);
  }

  @Override
  public List<ActorDataModel> getActors() {
    return null;
  }

  @Override
  public ActorDataModel getActorById(long id) {
    ActorDataModel result = dataSet.get(id);
    return result;
  }
}
