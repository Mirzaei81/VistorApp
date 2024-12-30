package org.visitor.Service.presenter.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "groups_info")
public class Groups implements Serializable {
    @PrimaryKey
    public  int id ;
    @ColumnInfo()
    public  String name;
}
