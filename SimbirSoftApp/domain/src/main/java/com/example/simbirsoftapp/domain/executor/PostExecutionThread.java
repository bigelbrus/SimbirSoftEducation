package com.example.simbirsoftapp.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {
  Scheduler getScheduler();
}
