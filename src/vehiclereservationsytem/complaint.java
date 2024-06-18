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
public class complaint implements comment {
    private Customer c ;
    int complaintId;
   private String commentBody;
   private String commentType;
   private String complaintResponse;
   private database db;
   
   
   public complaint(){
     db= new database();
   }
   
    public complaint(Customer c){
        this.c=c;
     db= new database();
   }
     public complaint(Customer c, String commentBody, String commentType) {
        this.c = c;
        this.commentBody = commentBody;
        this.commentType = commentType;
        complaintResponse="";
        db= new database();
     }
     
     
     public void addComment(){
     db.addCommentToDb(c,commentBody,commentType);
     
     }
     
  @Override
public void requestComplaint() {
    List<String> myList = db.getComplaintByCustID(c.getCustID());
    if (!myList.isEmpty()) {
        commentBody = myList.get(0);
        commentType = myList.get(1);
        complaintResponse = myList.get(2);
        c.setCustID(Integer.parseInt(myList.get(3)));
    } else {
        commentBody = "";
        commentType = "";
        complaintResponse = "";
    }
}


    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
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

    public String getComplaintResponse() {
        return complaintResponse;
    }

    public int getComplaintId() {
        return complaintId;
    }
    

    @Override
    public String toString() {
        return "complaint{" + "c=" + c + ", commentBody=" + commentBody + ", commentType=" + commentType + ", complaintResponse=" + complaintResponse + '}';
    }

    
}
