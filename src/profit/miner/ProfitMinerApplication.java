/****************************************************************************
** Copyright (c) 2001-2014
**
** This file is part of the QuickFIX FIX Engine
**
** This file may be distributed under the terms of the quickfixengine.org
** license as defined by quickfixengine.org and appearing in the file
** LICENSE included in the packaging of this file.
**
** This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
** WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
**
** See http://www.quickfixengine.org/LICENSE for licensing information.
**
** Contact ask@quickfixengine.org if any conditions of this licensing are
** not clear to you.
**
****************************************************************************/

package profit.miner;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import quickfix.*;
import profit.miner.Order;
import quickfix.field.*;
import javax.swing.SwingUtilities;
import quickfix.fix44.MassQuote;

public class ProfitMinerApplication extends MessageCracker implements Application {
    
    private static Side FIX_BUY = new Side(Side.BUY);
    private static Side FIX_SELL = new Side(Side.SELL);
    
    private static OrdType FIX_MARKET = new OrdType(OrdType.MARKET);
    private static OrdType FIX_LIMIT = new OrdType(OrdType.LIMIT);
    private static OrdType FIX_STOP = new OrdType(OrdType.STOP);

    private static TimeInForce TIF_DAY = new TimeInForce(TimeInForce.DAY);
    private static TimeInForce TIF_GTC = new TimeInForce(TimeInForce.GOOD_TILL_CANCEL);
    private static TimeInForce TIF_GTX = new TimeInForce(TimeInForce.GOOD_TILL_CROSSING);
    private static TimeInForce TIF_IOC = new TimeInForce(TimeInForce.IMMEDIATE_OR_CANCEL);
    private static TimeInForce TIF_OPG = new TimeInForce(TimeInForce.AT_THE_OPENING);

    
    static {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    
   public ProfitMinerApplication() {
        
    }

   private OrdType getFIXType(Order order){
       OrderType type = order.getType();
       if(type == OrderType.BUY ||type == OrderType.SELL){
           return FIX_MARKET;
       }else if(type == OrderType.BUY_LIMIT ||type == OrderType.SELL_LIMIT){
           return FIX_LIMIT;
       }else if(type == OrderType.BUY_STOP ||type == OrderType.SELL_STOP){
           return FIX_STOP;
       }
       
       return null;
   }
   
   private Side getFIXSide(Order order){
       OrderType type = order.getType();
       if(type == OrderType.BUY ||type == OrderType.BUY_LIMIT ||type == OrderType.BUY_STOP){
           return FIX_BUY;
       }else  if(type == OrderType.SELL ||type == OrderType.SELL_LIMIT ||type == OrderType.SELL_STOP){
           return FIX_SELL;
       }
       return null;
   }
   
    public  TimeInForce getFIXTimeInForce(Order order) {
         OrderTIF tif = order.getTIF();
        if(tif == OrderTIF.DAY){
            return TIF_DAY;
        }else if(tif == OrderTIF.GTC){
            return TIF_GTC;
        }else if(tif == OrderTIF.GTX){
            return TIF_GTX;
        }else if(tif == OrderTIF.IOC){
            return TIF_IOC;
        }else if(tif == OrderTIF.OPG){
            return TIF_OPG;
        }
        
        return null;
    }
    
    public void onCreate(SessionID sessionID) {
    
    }
    public void onLogon(SessionID sessionID) {
        
    }
    public void onLogout(SessionID sessionID) {
        
    }

    public void toAdmin(quickfix.Message message, SessionID sessionID) {

        try {
            Message.Header header = message.getHeader();
            
            if(header.getString(MsgType.FIELD).equals(MsgType.LOGON)){
                String username = null, password = null;
                if(sessionID.getSenderCompID().equals("Q019")){//price session
                    username = "primexm_fixclient8_q";
                    password = "LXsZnw2PSQfZdumv";
                }else if(sessionID.getSenderCompID().equals("T019")){//trading session
                    username = "primexm_fixclient8_t";
                    password = "KJ6w8whJmNv9oBaC";
                }
                
                message.setBoolean(ResetSeqNumFlag.FIELD, true);
                message.setString(Username.FIELD, username);
                message.setString(Password.FIELD, password);
            }
            
        } catch (FieldNotFound ex) {
            Logger.getLogger(ProfitMinerApplication.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
    }

    public void toApp(quickfix.Message message,SessionID sessionID) throws DoNotSend {
        
        
    }

    public void fromAdmin(quickfix.Message message,SessionID sessionID)throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        
    }

    public void fromApp(quickfix.Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        crack(message, sessionID);
    }
    
    @Handler
    public void massQuoteAcknowledgement(quickfix.fix44.MassQuoteAcknowledgement massQuoteAck, SessionID sessionID) throws FieldNotFound {
        
        //reply mass quote acknowledgement
        String beginString = sessionID.getBeginString();
        if(beginString.equals("FIX.4.4")){
            replyMassQuoteAck44(massQuoteAck, sessionID);
        }
        
        
    }

    @Handler
    public void massQuote(quickfix.fix44.MassQuote massQuote, SessionID sessionID) throws FieldNotFound {
        DoubleField bid = massQuote.getField(new DoubleField(188));
        DoubleField ask = massQuote.getField(new DoubleField(190));
        
        
    }
    
    private void updateUI(Message message, SessionID sessionID){
        try {
            SwingUtilities.invokeLater(new doFromApp(message, sessionID));
        } catch(Exception e) {}
    }
    
    public class doFromApp implements Runnable {
        private quickfix.Message message;
        private SessionID sessionID;

        public doFromApp(quickfix.Message message, SessionID sessionID) {
            this.message = message;
            this.sessionID = sessionID;
        }

        public void run() {
            try {
                MsgType msgType = new MsgType();
                if(message.getHeader().getField(msgType).valueEquals("8"))
                    executionReport(message, sessionID);
                if(message.getHeader().getField(msgType).valueEquals("9"))
                    cancelReject(message, sessionID);
                
                //more goes here
                
            } catch(FieldNotFound e) {
                System.out.println(e);
            }
        }
    }

    private void executionReport(Message message, SessionID sessionID) throws FieldNotFound {

        
    }

    private void cancelReject(Message message, SessionID sessionID)  throws FieldNotFound {

    }


    private boolean send(quickfix.Message message, SessionID sessionID) {
        try {
            return Session.sendToTarget(message, sessionID);
        } catch(SessionNotFound e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean sendOrder(Order order) throws IllegalArgumentException {
        
        quickfix.fix44.NewOrderSingle newOrderSingle = new quickfix.fix44.NewOrderSingle
            (new ClOrdID(Integer.toString(order.getID())),
             getFIXSide(order),
             new TransactTime(),
             getFIXType(order));
        newOrderSingle.set(new OrderQty(order.getLots()));
        newOrderSingle.set(new HandlInst('1'));
        newOrderSingle.set(new Symbol(order.getSymbol()));

        return send(populateOrder(order, newOrderSingle), order.getSessionID());
        
    }

    public quickfix.Message populateOrder(Order order, quickfix.Message newOrderSingle) {

        OrderType type = order.getType();

        if(type == OrderType.BUY_LIMIT || type == OrderType.SELL_LIMIT){//set open price
            newOrderSingle.setField(new Price(order.getLimit()));
        }
        
        if(type == OrderType.BUY_STOP || type == OrderType.SELL_STOP){//set close price
            newOrderSingle.setField(new Price(order.getStop()));
        }

        if(order.getTarget() != Order.NO_PRICE){//set target
             newOrderSingle.setField(new StopPx(order.getTarget()));//come back
        }
        
        if(order.getStoploss() != Order.NO_PRICE){//set stoploss
             newOrderSingle.setField(new StopPx(order.getStoploss()));//come back
        }
        
        newOrderSingle.setField(getFIXTimeInForce(order));
        
        return newOrderSingle;
    }
    
    public void replyMassQuoteAck44(quickfix.fix44.MassQuoteAcknowledgement massQuoteAck, SessionID sessionID) throws FieldNotFound {
        
        quickfix.fix44.MassQuoteAcknowledgement massQuoteAckReply =
            new quickfix.fix44.MassQuoteAcknowledgement();
        
        massQuoteAckReply.set(massQuoteAck.get(new QuoteID()));
        
        send(massQuoteAckReply, sessionID);
    }
    

    public void cancel(Order order) {
        String beginString = order.getSessionID().getBeginString();
        if(beginString.equals("FIX.4.4")){
            cancel44(order);
        }
    }
     
    
    public void cancel44(Order order) {
        
    }
    
    public void replace(Order order, Order newOrder) {
        String beginString = order.getSessionID().getBeginString();
        if(beginString.equals("FIX.4.4")){
            replace44(order, newOrder);
        }
    }




    
    public void replace44(Order order, Order newOrder) {
        
    }

    
    Message populateCancelReplace(Order order, Order newOrder,
                                  quickfix.Message message) {

        if(order.getLots() != newOrder.getLots())
            message.setField(new OrderQty(newOrder.getLots()));
        if(order.getLimit() != newOrder.getLimit())
            message.setField(new Price(newOrder.getLimit()));
        return message;
    }

}
