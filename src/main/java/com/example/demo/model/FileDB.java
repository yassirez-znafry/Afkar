package com.example.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
    @Table(name = "files")
    public class FileDB {
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        private String id;
        @GeneratedValue(strategy = IDENTITY)
        private Long candidatId;

        private String name;

        private String type;

        @Lob
        private byte[] data;



        public FileDB() {
        }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCandidatId() {
        return candidatId;
    }



    public FileDB(String name, String type, byte[] data) {
            this.name = name;
            this.type = type;
            this.data = data;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }

    }

