package org.visitor.Service.presenter.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserResponse implements Serializable
{
    public User UserObject;
    public ArrayList<UserConfig> serverDetail;
}
