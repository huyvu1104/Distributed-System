package client_server;

import helper.NumberSorter;
import java.util.Arrays;


public class Sorter implements NumberSorter {

    public Sorter() {
    }

    @Override
    public Integer[] sort(Integer[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] < input[j]) {
                    Integer temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }
        return input;
    }

}
