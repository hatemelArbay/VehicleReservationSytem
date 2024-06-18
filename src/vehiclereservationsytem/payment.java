/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclereservationsytem;

/**
 *
 * @author hatem
 */
    public class payment {
        private int paymentID ; 
        private String cardHolderNum;
        private String cardholderName; 
        private String paymentDate;
      Customer c ;  
        report r ; 
        database db= new database ();

        public payment(){
        r= new report();
       paymentID= db.getLatestPaymentId();

        }
          public payment(double rentReportPrice , int rentedDays){
               paymentID= db.getLatestPaymentId();
               this.cardholderName="";
               this.cardHolderNum="";
               this.paymentDate="";
              paymentID= db.getLatestPaymentId();
        r= new report(rentReportPrice,rentedDays);
       

        }
    public payment( String cardHolderNum, String cardholderName, report r,Customer c) {
        this.cardHolderNum = cardHolderNum;
        this.cardholderName = cardholderName;
        paymentID=db.getLatestPaymentId();
        this.c=c;
        this.r = r;
        r= new report();
    }

    

    public void makePayment(String suppName1, String suppName2){
       r.consumeSupply(suppName1,suppName2);
    }
    
    
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setCardHolderNum(String cardHolderNum) {
        this.cardHolderNum = cardHolderNum;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

   

    public void setR(report r) {
        this.r = r;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public String getCardHolderNum() {
        return cardHolderNum;
    }

    public String getCardholderName() {
        return cardholderName;
    }


    public report getR() {
        return r;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "payment{" + "cardHolderNum=" + cardHolderNum + ", cardholderName=" + cardholderName + ", paymentDate=" + paymentDate + " r=" + r + '}';
    }

 
    
    
    
    
    
}
