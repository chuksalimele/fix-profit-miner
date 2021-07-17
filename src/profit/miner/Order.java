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

import quickfix.SessionID;

public class Order implements Cloneable {
    public static final double NO_PRICE = -1;
    private SessionID sessionID = null;
    private String symbol = null;
    private double lots = 0;
    private double open = NO_PRICE;
    private int executed = 0;
    private OrderType type = null;
    private OrderTIF tif = OrderTIF.GTC;
    private double stop = NO_PRICE;
    private double limit = NO_PRICE;
    private double target = NO_PRICE;
    private double stoploss = NO_PRICE;
    private boolean rejected = false;
    private boolean canceled = false;
    private boolean isNew = true;
    private String message = null;
    private int ID = -1;
    private static int nextID = 1;

    public Order() {
        ID = generateID();
    }
    
    public Order(int ID) {
        this.ID = ID;
    }

    public Object clone() {
        try {
            Order order = (Order)super.clone();
            order.setID(order.generateID());
            return order;
        } catch(CloneNotSupportedException e) {}
        return null;
    }

    public int generateID() {
        return nextID++;
    }
    public SessionID getSessionID() {
        return sessionID;
    }
    public void setSessionID(SessionID sessionID) {
        this.sessionID = sessionID;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public double getLots() {
        return lots;
    }
    public void setLots(double lots) {
        this.lots = lots;
    }
    public double getOpen() {
        return open;
    }
    public void setOpen(double open) {
        this.open = open;
    }
    public int getExecuted() {
        return executed;
    }
    public void setExecuted(int executed) {
        this.executed = executed;
    }
    
    public OrderType getType() {
        return type;
    }
    public void setType(OrderType type) {
        this.type = type;
    }
    public OrderTIF getTIF() {
        return tif;
    }
    public void setTIF(OrderTIF tif) {
        this.tif = tif;
    }
    public double getLimit() {
        return limit;
    }
    public void setLimit(double limit) {
        this.limit = limit;
    }
    public void setLimit(String limit) {
        if(limit == "" || limit == null) {
            this.limit = NO_PRICE;
        } else {
            this.limit = Double.parseDouble(limit);
        }
    }
    public double getStoploss() {
        return stoploss;
    }
    public void setStoploss(double stoploss) {
        this.stoploss = stoploss;
    }
    public void setStoploss(String stoploss) {
        if(stoploss == "" || stoploss == null) {
            this.stoploss = NO_PRICE;
        } else {
            this.stoploss = Double.parseDouble(stoploss);
        }
    }
    
    public double getStop() {
        return stop;
    }
    
    public void setStop(double stop) {
        this.stop = stop;
    }
    
    public void setStop(String stop) {
        if(stop == "" || stop == null) {
            this.stop = NO_PRICE;
        } else {
            this.stop = Double.parseDouble(stop);
        }
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }
    
    public void setTarget(String target) {
        if(target == "" || target == null) {
            this.target = NO_PRICE;
        } else {
            this.target = Double.parseDouble(target);
        }
    }
    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }
    public boolean getRejected() {
        return rejected;
    }
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
    public boolean getCanceled() {
        return canceled;
    }
    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }
    public boolean isNew() {
        return isNew;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
    
}
