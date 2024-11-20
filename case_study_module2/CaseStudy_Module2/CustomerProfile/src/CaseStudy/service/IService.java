package CaseStudy.service;

import CaseStudy.entity.History;

import java.util.List;

public interface IService <T> {
    List<T> getAll();

    T getHistoryById(int T);

}
