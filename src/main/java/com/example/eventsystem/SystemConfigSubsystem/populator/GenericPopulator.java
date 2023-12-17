package com.example.eventsystem.SystemConfigSubsystem.populator;

import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;

import java.util.List;

public abstract class GenericPopulator<Source, Target> {
    protected abstract Target populate(Source source);

    public List<Target> populateAll(List<Source> source) {
        return source.stream().map(this::populate).toList();
    }
}
