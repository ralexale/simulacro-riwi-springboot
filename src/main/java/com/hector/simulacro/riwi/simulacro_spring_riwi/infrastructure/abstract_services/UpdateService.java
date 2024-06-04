package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services;

public interface UpdateService<Request, Response, Id> {

    Response update(Request request, Id id);
}
