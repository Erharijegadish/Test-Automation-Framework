package com.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

    public static Iterator<User> readCSVFile(String fileName) {
        File csvFile = new File(System.getProperty("user.dir") + "//testData//" + fileName);
        FileReader fileReader;
        CSVReader csvReader;
        String[] line;
        List<User> userList;
        User userData;
        try {
            fileReader = new FileReader(csvFile);
            csvReader = new CSVReader(fileReader);
            csvReader.readNext(); //To skip the header of the csv file
            userList = new ArrayList<User>();
            while((line = csvReader.readNext()) != null) {
                userData = new User(line[0], line[1]);
                userList.add(userData);
            }

        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
        return userList.iterator();
    }
}