package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thiagoisaias on 5/9/16.
 */
public class Node {

    String current;
    Map<String,Node> next = new HashMap<String,Node>();
}
