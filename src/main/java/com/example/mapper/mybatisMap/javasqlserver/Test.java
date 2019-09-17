package com.example.mapper.mybatisMap.javasqlserver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub
        PersonDao p = new PersonDao();

        //add
        Person person0 = new Person();
        person0.setId(1);
        person0.setName("小明");
        person0.setAge(20);
        p.addPerson(person0);

        //update
        Person person1 = new Person();
        person1.setId(1);
        person1.setName("陈伟霆");
        person1.setAge(35);
        p.updatePerson(person1);

        //delete
        int id = 1;
        System.out.println(p.SearchOne(id).getName());


        //search
        List<Person> people = new ArrayList<Person>();
        people = p.Search();
        for(Person person : people){
            String str = person.getId()+","+person.getName()+","+person.getAge();
            System.out.println(str);
        }



    }

}
