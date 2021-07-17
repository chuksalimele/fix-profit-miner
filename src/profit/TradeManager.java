/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profit;

import profit.miner.Order;
import profit.miner.OrderType;
import profit.miner.ProfitMinerApplication;
import quickfix.SessionID;

/**
 *
 * @author USER
 */
final public class TradeManager {
    private static volatile TradeManager INSTANCE = null;
    private ProfitMinerApplication app;
    private SessionID sessionID;
    private String lastError;
    
    
    
    private TradeManager(){
        
    }
    
    public static TradeManager getInstance(){
        TradeManager instance = TradeManager.INSTANCE;
        if(instance == null){
            synchronized(TradeManager.class){
                instance = TradeManager.INSTANCE;
                if(instance == null){
                    TradeManager.INSTANCE = instance = new TradeManager();
                }
            }
        }
        return instance;
    }
    
    public int buy(String symbol, double lot, double tp_price, double stop_price){
        return sendOrder(symbol, OrderType.BUY, lot, Order.NO_PRICE, tp_price, stop_price);
    }
    
    public int sell(String symbol, double lot, double tp_price, double stop_price){
        return sendOrder(symbol, OrderType.SELL, lot,  Order.NO_PRICE, tp_price, stop_price);
    }
    
    public int buyLimit(String symbol, double lot, double open, double tp_price, double stop_price){
        return sendOrder(symbol, OrderType.BUY_LIMIT, lot, open, tp_price, stop_price);
    }
    
    public int buyStop(String symbol, double lot, double open, double tp_price, double stop_price){
        return sendOrder(symbol, OrderType.BUY_STOP, lot, open, tp_price, stop_price);
    }
    
    public int sellLimit(String symbol, double lot, double open, double tp_price, double stop_price){
        return sendOrder(symbol, OrderType.SELL_LIMIT, lot, open, tp_price, stop_price);
    }
    
    public int sellStop(String symbol, double lot, double open, double tp_price, double stop_price){
        return sendOrder(symbol, OrderType.SELL_STOP, lot, open, tp_price, stop_price);
    }
    
    private int sendOrder(String symbol, OrderType type, double lot, double open, double tp_price, double stop_price){
        Order order = new Order();
        order.setSymbol(symbol);
        order.setLots(lot);
        order.setTarget(tp_price);
        order.setStoploss(stop_price);
        
        if(null != type)switch (type) {
            case BUY:
            case SELL:
                order.setOpen(open);
                break;
            case BUY_LIMIT:
            case SELL_LIMIT:
                order.setLimit(open);
                break;
            case BUY_STOP:
            case SELL_STOP:
                order.setStop(open);
                break;
            default:
                break;
        }
        
        if(app.sendOrder(order)){
            return order.getID();
        }
        
        return -1;
    }
    
    public int deletePendingOrder(int ticket){
        
        return -1;
    }
    
    public int closeOrder(int ticket){
        
        return -1;
    }
    
    public int modifyOrder(int ticket, double tp_price, double stop_price){
        
        return -1;
    }
    
    public Order selectOrder(int ticket){
        
        return null;
    }
}
