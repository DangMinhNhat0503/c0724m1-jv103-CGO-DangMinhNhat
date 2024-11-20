package CaseStudy.controller;

import CaseStudy.entity.History;
import CaseStudy.service.HistoryService;
import CaseStudy.service.IHistory;

import java.util.List;

public class HistoryController {
    private static IHistory historyService = new HistoryService();

    public static History getHistoryByID(int searchIdHistory) {
        History histories = historyService.getHistoryById(searchIdHistory);
        return histories;
    }
}
