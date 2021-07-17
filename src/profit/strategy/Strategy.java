/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profit.strategy;

import profit.TradeManager;

/**
 *
 * @author USER
 */
public interface Strategy {
    
    void run(TradeManager app);
}
