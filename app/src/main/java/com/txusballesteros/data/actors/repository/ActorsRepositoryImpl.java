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
package com.txusballesteros.data.actors.repository;

import com.txusballesteros.data.actors.datasource.ActorsDataSource;
import com.txusballesteros.data.model.ActorDataModel;
import com.txusballesteros.data.model.ActorDataModelMapper;
import com.txusballesteros.domain.model.Actor;
import com.txusballesteros.domain.repository.ActorsRepository;
import java.util.List;
import javax.inject.Inject;

public class ActorsRepositoryImpl implements ActorsRepository {
  private final ActorsDataSource dataSource;
  private final ActorDataModelMapper mapper;

  @Inject
  public ActorsRepositoryImpl(ActorsDataSource dataSource, ActorDataModelMapper mapper) {
    this.dataSource = dataSource;
    this.mapper = mapper;
  }

  @Override
  public List<Actor> getActors() {
    List<ActorDataModel> actors = dataSource.getActors();
    List<Actor> result = mapper.map(actors);
    return result;
  }

  @Override
  public Actor getActorById(long id) {
    ActorDataModel actor = dataSource.getActorById(id);
    Actor result = mapper.map(actor);
    return result;
  }
}
