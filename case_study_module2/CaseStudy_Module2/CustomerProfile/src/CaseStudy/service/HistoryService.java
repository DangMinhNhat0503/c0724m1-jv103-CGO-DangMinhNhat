package CaseStudy.service;

import CaseStudy.entity.Customer;
import CaseStudy.entity.History;
import CaseStudy.repository.CustomerRepository;
import CaseStudy.repository.HistoryRepository;

import java.util.List;

public class HistoryService implements IHistory{
    private static HistoryRepository historyRepository = new HistoryRepository();

    @Override
    public List<History> getAll() {
        List<History> historiesList = historyRepository.getAll();
        return historiesList;
    }

    @Override
    public History getHistoryById(int searchIdHistory) {
        List<History> histories = historyRepository.getAll();
        History temp = null;
        for (int i = 0; i < histories.size(); i++) {
             if (histories.get(i).getId() == searchIdHistory) {
                 temp = histories.get(i);
             }
        }
        return temp;
    }
}
