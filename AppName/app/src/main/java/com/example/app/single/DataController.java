package com.example.app.single;

/**
 * Created by p_billylu on 2018/7/31.
 */

public class DataController {

    private DataController(){

    }

    private static class DataControllerSingleton{
        private  static DataController instance = new DataController();
    }

    public static DataController getInstance(){
        return DataControllerSingleton.instance;
    }


}
