/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclereservationsytem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hatem
 */
public class feedBack implements comment{
   private Customer c ;
   private String commentBody;
   private String commentType;
   database db;

      public feedBack(){}
    public feedBack(Customer c, String commentBody, String commentType) {
        this.c = c;
        this.commentBody = commentBody;
        this.commentType = commentType;
        db = new database();
    }
    public void addComment(){
    db.addCommentToDb(c,commentBody,commentType);
    
    }
    
    public void requestComplaint(){
       List<String> myList = new ArrayList<>();
    myList= db.getComplaintByCustID(c.getCustID());
    commentBody=myList.get(0);
    commentType=myList.get(1);
    c.setCustID(Integer.parseInt(myList.get(3)));
    }

    public void setC(Customer c) {
        this.c = c;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public Customer getC() {
        return c;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public String getCommentType() {
        return commentType;
    }

    @Override
    public String toString() {
        return "feedBack{" + "c=" + c + ", commentBody=" + commentBody + ", commentType=" + commentType + '}';
    }
   
}
