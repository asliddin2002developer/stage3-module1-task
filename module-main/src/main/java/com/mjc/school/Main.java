package com.mjc.school;

import com.mjc.school.repository.datasource.DataSource;


import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        DataSource database = DataSource.getInstance();
        database.readFile("content.txt");

    }
}
