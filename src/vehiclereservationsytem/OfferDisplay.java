/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiclereservationsytem;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author ahmed
 */
public class OfferDisplay extends SwingWorker<Void, Void> {
database db = new database();
    private JPanel offerPanel;

    public OfferDisplay(JPanel offerPanel) {
        this.offerPanel = offerPanel;
    }

    @Override
    protected Void doInBackground() throws Exception {
        db.displayOffersLoop();
        return null;
    }

    @Override
    protected void done() {
        // Handle any cleanup or UI updates after the task is finished
    }
}