package CaseStudy.repository;

import CaseStudy.entity.History;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {
    private static final String LINK = "src/CaseStudy/data/CustomerHistoryData.csv";
    private static List<History> histories = new ArrayList<>();


    public List<History> getAll() {
        File file = new File(LINK);
        History history;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(",");
                history = new History(Integer.parseInt(temp[0]), temp[1], temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]), Integer.parseInt(temp[5]));
                //// id, date, productName, quantity, unitPrice, customerTurnover
                histories.add(history);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");

        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        }
        return histories;
    }
}
